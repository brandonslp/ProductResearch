package com.brandonslp.productresearch.model;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by brand on 6/05/2017.
 */

public class Product {
    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "price")
    private double price;
    @DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = "store_id")
    private Store store;

    public Product() {
    }

    public Product(String name, double price, Store store) {
        this.name = name;
        this.price = price;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
