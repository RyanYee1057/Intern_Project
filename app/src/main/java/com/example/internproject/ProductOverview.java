package com.example.internproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductOverview extends AppCompatActivity {
    TextView name, price, id, stock, description, detail;
    ImageView image;
    Spinner q;
    int stockQ, i, num, gotQuan, productQuantity, addQ;
    double pri;
    String shopID, productID, userID, shoppingCart_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_overview);

        id = (TextView) findViewById(R.id.productOverviewID);
        name = (TextView) findViewById(R.id.productOverviewName);
        price = (TextView) findViewById(R.id.productOverviewPrice);
        image = (ImageView) findViewById(R.id.productOverviewImage);
        stock = (TextView) findViewById(R.id.quanlityStock);
        description = (TextView) findViewById(R.id.id_description);
        detail = (TextView) findViewById(R.id.id_detail);
        q = (Spinner) findViewById(R.id.quantity);
        String productid = getIntent().getStringExtra("id");//when get specify the type

    }


}
