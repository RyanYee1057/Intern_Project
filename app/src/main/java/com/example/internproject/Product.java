package com.example.internproject;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product extends AppCompatActivity implements TextWatcher{

    LinearLayout L1, L2;
    GridView gridView;
    EditText search;
    CustomAdapter adapter;
    final ArrayList<productModel> productList = new ArrayList<>();

    public static final String TAG_IMAGE_URL = "img_url";
    String url = "http://pos.api.itmansolution.com/product/getAllProduct";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        L1 = (LinearLayout) findViewById(R.id.C1_screen);
        L2 = (LinearLayout) findViewById(R.id.C2_screen);
        gridView = (GridView) findViewById(R.id.grid);
        search = (EditText) findViewById(R.id.filterGrid);
        search.addTextChangedListener(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("data");
                            showGrid(jsonArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter = new CustomAdapter(Product.this, productList);
                        gridView.setAdapter(adapter);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    public void on1(View view){
        L1.setVisibility(View.VISIBLE);
        L2.setVisibility(View.GONE);
        gridView.setVisibility(View.GONE);
    }

    public void on2(View view){
        L1.setVisibility(View.GONE);
        L2.setVisibility(View.VISIBLE);
        gridView.setVisibility(View.GONE);
    }

    public void onGrid(View view){
        L1.setVisibility(View.GONE);
        L2.setVisibility(View.GONE);
        gridView.setVisibility(View.VISIBLE);
    }

    private void showGrid(JSONArray jsonArray) throws JSONException {

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String product_id = jsonObject.getString("product_id");
                String category_id = jsonObject.getString("category_id");
                String product_name = jsonObject.getString("product_name");
                String selling_price = jsonObject.getString("selling_price");
                String category_name = jsonObject.getString("category_name");
                String product_pic = jsonObject.getString("img_url");
                Toast.makeText(Product.this, product_pic, Toast.LENGTH_LONG).show();

                //images.add(jsonObject.getString("img url"));

                productModel p = new productModel();
                p.setP_id(product_id);
                p.setC_id(category_id);
                p.setPrice(selling_price);
                p.setP_name(product_name);
                p.setC_name(category_name);
                p.setP_pic(product_pic);
                productList.add(p);
            }

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
        this.adapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

}
