package com.example.internproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//https://www.simplifiedcoding.net/android-custom-gridview-images/
//https://www.swipetips.com/android-json-parse-images-and-texts-tutorial/

public class MainActivity extends AppCompatActivity{

    public static final String TAG = "MyTag";
    StringRequest stringRequest; // Assume this exists.
    RequestQueue requestQueue; // Assume this exists.
    CustomAdapter adapter;

    final ArrayList<productModel> productList = new ArrayList<>();
    String url = "http://pos.api.itmansolution.com/product/getAllProduct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView) findViewById(R.id.grid);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            for(int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String product_id = jsonObject.getString("product_id");
                                String category_id = jsonObject.getString("category_id");
                                String product_name = jsonObject.getString("product_name");
                                String selling_price = jsonObject.getString("selling_price");
                                String category_name = jsonObject.getString("category_name");
                                //Toast.makeText(MainActivity.this, "Check", Toast.LENGTH_LONG).show();

                                productModel p = new productModel();
                                p.setP_id(product_id);
                                p.setC_id(category_id);
                                p.setPrice(selling_price);
                                p.setP_name(product_name);
                                p.setC_name(category_name);
                                productList.add(p);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        adapter = new CustomAdapter(MainActivity.this, productList);
                        gridView.setAdapter(adapter);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void onProd(View view){
        Toast.makeText(MainActivity.this,"Going to Product activity",Toast.LENGTH_SHORT).show();
        Intent productIntent = new Intent (MainActivity.this, Product.class);
        startActivity(productIntent);
    }

    public void onOver(View view){
        Toast.makeText(MainActivity.this,"HI",Toast.LENGTH_SHORT).show();
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
