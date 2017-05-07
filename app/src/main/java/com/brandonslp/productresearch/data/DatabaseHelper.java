package com.brandonslp.productresearch.data;

import android.content.Context;

import com.brandonslp.productresearch.model.Product;
import com.brandonslp.productresearch.model.Store;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import nl.elastique.poetry.database.DatabaseConfiguration;

/**
 * Created by brand on 6/05/2017.
 */

public class DatabaseHelper extends nl.elastique.poetry.database.DatabaseHelper{

    private static final int DB_VERSION = 1;

    private static final DatabaseConfiguration CONFIGURATION = new DatabaseConfiguration(DB_VERSION, new Class<?>[]{
            Product.class, Store.class
        });

    public DatabaseHelper(Context context) {
        super(context, CONFIGURATION);
    }

    public static DatabaseHelper getHelper(Context context){
        return OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }
}
