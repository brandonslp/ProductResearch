package com.brandonslp.productresearch.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

/**
 * Created by brand on 6/05/2017.
 */

public class Store {

    @DatabaseField(columnName = "id", generatedId = true)
    private int id;
    @DatabaseField(columnName = "name")
    private int name;
    @DatabaseField(columnName = "address")
    private int address;
    @ForeignCollectionField
    private ForeignCollection<Product> products;

    public Store() {
    }

    public Store(int name, int address, ForeignCollection<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public ForeignCollection<Product> getProducts() {
        return products;
    }

    public void setProducts(ForeignCollection<Product> products) {
        this.products = products;
    }
}
