package com.brandonslp.productresearch.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brandonslp.productresearch.R;
import com.brandonslp.productresearch.controller.Products_controller;
import com.brandonslp.productresearch.controller.Stores_controller;
import com.brandonslp.productresearch.model.Product;

import org.parceler.Parcels;

import java.sql.SQLException;

public class AddProductActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mPrice;
    private FloatingActionButton fab;
    private Product mProduct;
    private Stores_controller storesController;
    private Products_controller productsController;
    private boolean mCreateMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        mName = (EditText) findViewById(R.id.edit_name_product);
        mPrice = (EditText) findViewById(R.id.edit_price);

        try {
            storesController = new Stores_controller(this);
            productsController = new Products_controller(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (getIntent().hasExtra("id_product_extra")) {
            initEdit();
        } else {
            initAdd();
        }

    }

    private void initEdit(){
        final int id = getIntent().getIntExtra("id_product_extra", -1);
        final int position = getIntent().getIntExtra("position_extra", -1);
        mProduct = productsController.getById(id);
        mName.setText(mProduct.getName());
        mPrice.setText(String.valueOf(mProduct.getPrice()));
        setTitle(mProduct.getName());
        mCreateMenu = true;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mName.getText().toString().trim().equals("") &&
                        !mPrice.getText().toString().trim().equals("")) {
                    mProduct.setName(mName.getText().toString());
                    mProduct.setPrice(Double.parseDouble(mPrice.getText().toString()));
                    productsController.update(mProduct);
                    Intent data = new Intent();
                    data.putExtra("position_extra", position);
                    data.putExtra("id_product_extra", id);
                    setResult(300, data);
                    onBackPressed();
                } else {
                    Toast.makeText(AddProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initAdd() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mName.getText().toString().trim().equals("") &&
                        !mPrice.getText().toString().trim().equals("")) {
                    Intent data = new Intent();
                    data.putExtra("name_extra", mName.getText().toString());
                    data.putExtra("price_extra", Double.parseDouble(mPrice.getText().toString()));
                    setResult(200, data);
                    onBackPressed();
                } else {
                    Toast.makeText(AddProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (mCreateMenu)
            getMenuInflater().inflate(R.menu.product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_delete) {
            Intent data = new Intent();
            productsController.destroy(mProduct);
            data.putExtra("position_extra", getIntent().getIntExtra("position_extra", -1));
            setResult(400, data);
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
