package com.brandonslp.productresearch.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.brandonslp.productresearch.R;
import com.brandonslp.productresearch.adapters.ProductsAdapter;
import com.brandonslp.productresearch.adapters.StoresAdapter;
import com.brandonslp.productresearch.controller.Products_controller;
import com.brandonslp.productresearch.controller.Stores_controller;
import com.brandonslp.productresearch.model.Product;
import com.brandonslp.productresearch.model.Store;
import com.j256.ormlite.dao.ForeignCollection;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreProductsActivity extends AppCompatActivity {

    private Stores_controller storesController;
    private Products_controller productsController;


    private RecyclerView recyclerView;
    private ProductsAdapter adapter;
    private Store mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_products);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_products);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        try {
            storesController = new Stores_controller(this);
            productsController = new Products_controller(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mStore = storesController.getById(getIntent().getExtras().getInt("store_id_extra"));
        // Fill list
        ForeignCollection<Product> products = storesController.getById(mStore.getId()).getProducts();
        ArrayList productList= new ArrayList(products);
        adapter = new ProductsAdapter(productList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(StoreProductsActivity.this, AddProductActivity.class), 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 200) {
            Product product = new Product(
                    data.getExtras().getString("name_extra"),
                    data.getExtras().getDouble("price_extra"),
                    mStore);
            if (productsController.addProduct(product)) {
                adapter.addProduct(product);
                adapter.notifyDataSetChanged();
            }
        } else if (resultCode == 300) {
            // Modified product
            Product product = productsController.getById(data.getExtras().getInt("id_product_extra"));
            adapter.addProduct(product, data.getExtras().getInt("position_extra"));
            adapter.notifyItemChanged(data.getExtras().getInt("position_extra"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
}
