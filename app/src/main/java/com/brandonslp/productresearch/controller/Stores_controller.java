package com.brandonslp.productresearch.controller;

import android.content.Context;

import com.brandonslp.productresearch.data.DatabaseHelper;
import com.brandonslp.productresearch.model.Store;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by brand on 6/05/2017.
 */

public class Stores_controller {
    private final DatabaseHelper helper;
    private Dao<Store, Integer> storeDao;

    public Stores_controller(Context context) throws SQLException {
        this.helper = DatabaseHelper.getHelper(context);
        this.storeDao = helper.getDao(Store.class);
    }

    public boolean addStore(Store store){
        try {
            return storeDao.createOrUpdate(store).isCreated();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int update(Store store){
        try {
            return storeDao.update(store);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int destroy(Store store){
        try {
            return storeDao.delete(store);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<Store> getAll(){
        try {
            return storeDao.queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Store getById(int id){
        try {
            return storeDao.queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
