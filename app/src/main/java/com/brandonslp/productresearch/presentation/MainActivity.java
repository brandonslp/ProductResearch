package com.brandonslp.productresearch.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.brandonslp.productresearch.R;
import com.brandonslp.productresearch.adapters.StoresAdapter;
import com.brandonslp.productresearch.controller.Products_controller;
import com.brandonslp.productresearch.controller.Stores_controller;
import com.brandonslp.productresearch.model.Product;
import com.brandonslp.productresearch.model.Store;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Stores_controller storesController;
    private Products_controller productsController;

    private RecyclerView recyclerView;
    private StoresAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            storesController = new Stores_controller(this);
            productsController = new Products_controller(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Fill list
        List<Store> stores = storesController.getAll();
        adapter = new StoresAdapter(stores);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_stores);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivityForResult(new Intent(MainActivity.this, AddStoreActivity.class), 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 200) {
            Store store = new Store(
                    data.getExtras().getString("name_extra"),
                    data.getExtras().getString("address_extra"),
                    null);
            if (storesController.addStore(store)) {
                adapter.addStore(store);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void test() {
            //addStore();
            //addProduct();
            show();
    }

    private void addStore() {
        Store store = new Store("Ara",
                                "Calle 123",null);
        Log.v("Brandon-lp","add store -> "+storesController.addStore(store));
    }

    private void addProduct(){
        Product p;
        for (int i = 0; i < 5;i++){
            p = new Product("Producto "+ i, 1000*i, storesController.getById(0));
            Log.v("Brandon-lp","add Product -> "+productsController.addProduct(p));
        }
    }

    private void show(){
        Log.v("Brandon-lp","Store -> "+storesController.getById(1));
        for (Product p: storesController.getById(1).getProducts()) {
            Log.v("Brandon-lp","Producto -> "+ p.getId() +" Name -> "+p.getName() + " Store -> " + p.getStore().getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
