package com.example.internproject;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

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

    LinearLayout c1s, c2s, c3s, c4s, gs;
    GridView gridView, c1, c2, c3, c4;
    ListView cA;
    EditText search;
    CustomAdapter adapter, a1, a2, a3, a4;
    cAdapter aA;
    final ArrayList<productModel> productList = new ArrayList<>();
    final ArrayList<productModel> category1 = new ArrayList<>();
    final ArrayList<productModel> category2 = new ArrayList<>();
    final ArrayList<productModel> category3 = new ArrayList<>();
    final ArrayList<productModel> category4 = new ArrayList<>();
    final ArrayList<productModel> categoryList = new ArrayList<>();

    String url = "http://pos.api.itmansolution.com/product/getAllProduct";
    String c_url ="http://pos.api.itmansolution.com/product/getAllCategory";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);

        c1s =  findViewById(R.id.C1_screen);
        c2s = findViewById(R.id.C2_screen);
        c3s = findViewById(R.id.C3_screen);
        c4s = findViewById(R.id.C4_screen);
        gs = findViewById(R.id.grid_layout);

        c1 = findViewById(R.id.c1_grid);
        c2 = findViewById(R.id.c2_grid);
        c3 = findViewById(R.id.c3_grid);
        c4 = findViewById(R.id.c4_grid);
        gridView = findViewById(R.id.grid);
        cA = findViewById(R.id.cTitle_list);

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
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                for(int n = 0; n <= i; n++) {
                                    if (n == i) {
                                        Intent intent = new Intent(Product.this, ProductOverview.class);
                                        int num = i + 1;
                                        intent.putExtra("x", String.valueOf(num));
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                        a1 = new CustomAdapter(Product.this, category1);
                        c1.setAdapter(a1);
                        c1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                for(int n = 0; n <= i; n++) {
                                    if (n == i) {
                                        Intent intent = new Intent(Product.this, ProductOverview.class);
                                        intent.putExtra("numC1", String.valueOf(i));
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                        a2 = new CustomAdapter(Product.this, category2);
                        c2.setAdapter(a2);
                        c2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                for(int n = 0; n <= i; n++) {
                                    if (n == i) {
                                        Intent intent = new Intent(Product.this, ProductOverview.class);
                                        intent.putExtra("numC2", String.valueOf(i));
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                        a3 = new CustomAdapter(Product.this, category3);
                        c3.setAdapter(a3);
                        c3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                for(int n = 0; n <= i; n++) {
                                    if (n == i) {
                                        Intent intent = new Intent(Product.this, ProductOverview.class);
                                        intent.putExtra("numC3", String.valueOf(i));
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                        a4 = new CustomAdapter(Product.this, category4);
                        c4.setAdapter(a4);
                        c4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                for(int n = 0; n <= i; n++) {
                                    if (n == i) {
                                        Intent intent = new Intent(Product.this, ProductOverview.class);
                                        intent.putExtra("numC4", String.valueOf(i));
                                        startActivity(intent);
                                    }
                                }
                            }
                        });

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        JsonObjectRequest objRequest = new JsonObjectRequest
                (Request.Method.GET, c_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            showPic(jsonArray);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        aA = new cAdapter(Product.this, categoryList);
                        cA.setAdapter(aA);

                        cA.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                //first item in list view
                                if (i == 0){
                                    gs.setVisibility(View.GONE);
                                    c1s.setVisibility(View.VISIBLE);
                                    c2s.setVisibility(View.GONE);
                                    c3s.setVisibility(View.GONE);
                                    c4s.setVisibility(View.GONE);
                                }

                                if (i == 1){
                                    gs.setVisibility(View.GONE);
                                    c1s.setVisibility(View.GONE);
                                    c2s.setVisibility(View.VISIBLE);
                                    c3s.setVisibility(View.GONE);
                                    c4s.setVisibility(View.GONE);
                                }

                                if (i == 2){
                                    gs.setVisibility(View.GONE);
                                    c1s.setVisibility(View.GONE);
                                    c2s.setVisibility(View.GONE);
                                    c3s.setVisibility(View.VISIBLE);
                                    c4s.setVisibility(View.GONE);
                                }

                                if (i == 3){
                                    gs.setVisibility(View.GONE);
                                    c1s.setVisibility(View.GONE);
                                    c2s.setVisibility(View.GONE);
                                    c3s.setVisibility(View.GONE);
                                    c4s.setVisibility(View.VISIBLE);
                                }
                            }
                        });

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(objRequest);
    }

    public void onGrid(View view){
        gs.setVisibility(View.VISIBLE);
        c1s.setVisibility(View.GONE);
        c2s.setVisibility(View.GONE);
        c3s.setVisibility(View.GONE);
        c4s.setVisibility(View.GONE);
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
                //Toast.makeText(Product.this, product_pic, Toast.LENGTH_LONG).show();

                productModel p = new productModel();
                    p.setP_id(product_id);
                    p.setC_id(category_id);
                    p.setPrice(selling_price);
                    p.setP_name(product_name);
                    p.setC_name(category_name);
                    p.setP_pic(product_pic);
                    productList.add(p);

                productModel p01 = new productModel();
                if(category_id.contains("1")) {
                    p01.setP_id(product_id);
                    p01.setC_id(category_id);
                    p01.setPrice(selling_price);
                    p01.setP_name(product_name);
                    p01.setC_name(category_name);
                    p01.setP_pic(product_pic);
                    category1.add(p01);
                }

                productModel p02 = new productModel();
                if(category_id.contains("2")) {
                    p02.setP_id(product_id);
                    p02.setC_id(category_id);
                    p02.setPrice(selling_price);
                    p02.setP_name(product_name);
                    p02.setC_name(category_name);
                    p02.setP_pic(product_pic);
                    category2.add(p02);
                }

                productModel p03 = new productModel();
                if(category_id.contains("3")) {
                    p03.setP_id(product_id);
                    p03.setC_id(category_id);
                    p03.setPrice(selling_price);
                    p03.setP_name(product_name);
                    p03.setC_name(category_name);
                    p03.setP_pic(product_pic);
                    category3.add(p03);
                }

                productModel p04 = new productModel();
                if(category_id.contains("4")) {
                    p04.setP_id(product_id);
                    p04.setC_id(category_id);
                    p04.setPrice(selling_price);
                    p04.setP_name(product_name);
                    p04.setC_name(category_name);
                    p04.setP_pic(product_pic);
                    category4.add(p04);
                }
            }
    }

    private void showPic(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String category_id = jsonObject.getString("category_id");
            String category_name = jsonObject.getString("category_name");
            String picture_name = jsonObject.getString("img_name");
            String category_pic = jsonObject.getString("img_url");
            //Toast.makeText(Product.this, product_pic, Toast.LENGTH_LONG).show();

            productModel p = new productModel();
            p.setC_name(category_name);
            p.setC_id(category_id);
            p.setPic_name(picture_name);
            p.setC_pic(category_pic);
            categoryList.add(p);
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
