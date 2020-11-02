package com.example.internproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity{

    public static final String TAG = "MyTag";
    StringRequest stringRequest; // Assume this exists.
    RequestQueue requestQueue; // Assume this exists.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onProd(View view){
        Toast.makeText(MainActivity.this,"Going to Product activity",Toast.LENGTH_SHORT).show();
        Intent productIntent = new Intent (MainActivity.this, Product.class);
        startActivity(productIntent);
    }

    public void onOver(View view){
        Toast.makeText(MainActivity.this,"Product Overview",Toast.LENGTH_SHORT).show();
        Intent pOverIntent = new Intent (MainActivity.this, ProductOverview.class);
        startActivity(pOverIntent);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(TAG);
        }
    }
}
