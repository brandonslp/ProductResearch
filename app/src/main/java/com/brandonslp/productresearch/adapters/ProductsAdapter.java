package com.brandonslp.productresearch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.brandonslp.productresearch.R;
import com.brandonslp.productresearch.model.Product;
import com.brandonslp.productresearch.presentation.StoreProductsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by antonio on 7/05/17.
 */
public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private List<Product> mProducts;

    public ProductsAdapter(List<Product> products) {
        mProducts = products;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_product, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder viewHolder, int position) {
        Product product = mProducts.get(position);

        viewHolder.nameTextView.setText(product.getName());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void addProduct(Product product) {
        mProducts.add(product);
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.item_product_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}