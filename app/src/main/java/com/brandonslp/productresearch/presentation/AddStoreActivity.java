package com.brandonslp.productresearch.presentation;

import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.brandonslp.productresearch.R;

public class AddStoreActivity extends AppCompatActivity {
    private EditText mName, mAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_store);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mName = (EditText) findViewById(R.id.edit_name);
        mAddress = (EditText) findViewById(R.id.edit_address);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mName.getText().toString().trim().equals("") &&
                        !mAddress.getText().toString().trim().equals("")) {
                    Intent data = new Intent();
                    data.putExtra("name_extra", mName.getText().toString());
                    data.putExtra("address_extra", mAddress.getText().toString());
                    setResult(200, data);
                    onBackPressed();
                } else {
                    Toast.makeText(AddStoreActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
