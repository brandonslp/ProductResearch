package com.brandonslp.productresearch.controller;

import android.content.Context;

import com.brandonslp.productresearch.data.DatabaseHelper;
import com.brandonslp.productresearch.model.Product;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by brand on 6/05/2017.
 */

public class Products_controller {
    private final DatabaseHelper helper;
    private Dao<Product, Integer> productDao;

    public Products_controller(Context context) throws SQLException {
        this.helper =  DatabaseHelper.getHelper(context);
        this.productDao = this.helper.getDao(Product.class);
    }

    public boolean addProduct(Product product){
        try {
            return productDao.createOrUpdate(product).isCreated();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int update(Product product){
        try {
            return productDao.update(product);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int destroy(Product product){
        try {
            return productDao.delete(product);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Product> getAll(){
        try {
            return productDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product getById(int id){
        try {
            return productDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
