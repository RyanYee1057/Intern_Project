package com.example.internproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProductOverview extends AppCompatActivity {

    String url = "http://pos.api.itmansolution.com/product/getAllProduct";
    String url1 = "http://pos.api.itmansolution.com/product/getProductByCategory/1";
    String url2 = "http://pos.api.itmansolution.com/product/getProductByCategory/2";
    String url3 = "http://pos.api.itmansolution.com/product/getProductByCategory/3";
    String url4= "http://pos.api.itmansolution.com/product/getProductByCategory/4";

    TextView name, price, id, stock, desProduct, detail;
    ImageView image;
    String numA, numC1, numC2, numC3, numC4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_overview);

        Intent intent = getIntent();
        numA = intent.getStringExtra("x");
        numC1 = intent.getStringExtra("numC1");
        numC2 = intent.getStringExtra("numC2");
        numC3 = intent.getStringExtra("numC3");
        numC4 = intent.getStringExtra("numC4");

        if(numA != null){
            System.out.println("all product");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                showProd(jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        }else if(numC1 != null){
            System.out.println("C1 product");

            JsonObjectRequest obj1Request = new JsonObjectRequest
                    (Request.Method.GET, url1, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                showProdC1(jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(obj1Request);

        }else if(numC2 != null){
            System.out.println("C2 product");

            JsonObjectRequest obj2Request = new JsonObjectRequest
                    (Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                showProdC2(jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(obj2Request);

        }else if(numC3 != null) {
            System.out.println("C3 product");

            JsonObjectRequest obj2Request = new JsonObjectRequest
                    (Request.Method.GET, url3, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                showProdC3(jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(obj2Request);

        }else if(numC4 != null) {
            System.out.println("C4 product");

            JsonObjectRequest obj2Request = new JsonObjectRequest
                    (Request.Method.GET, url4, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {

                            try {
                                JSONArray jsonArray = response.getJSONArray("data");
                                showProdC4(jsonArray);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
            MySingleton.getInstance(this).addToRequestQueue(obj2Request);
        }
        else{
            System.out.println("Nothing");
        }

        id = (TextView) findViewById(R.id.productID);
        name = (TextView) findViewById(R.id.productName);
        price = (TextView) findViewById(R.id.productPrice);
        image = (ImageView) findViewById(R.id.productPic);
        stock = (TextView) findViewById(R.id.quanlityStock);
        desProduct = (TextView) findViewById(R.id.id_description);
        detail = (TextView) findViewById(R.id.id_detail);
    }

    private void showProd(JSONArray jsonArray) throws JSONException {

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String product_id = jsonObject.getString("product_id");
            String category_id = jsonObject.getString("category_id");
            String product_name = jsonObject.getString("product_name");
            String selling_price = jsonObject.getString("selling_price");
            String description = jsonObject.getString("description");
            String ingredients = jsonObject.getString("ingredients");
            String stock_qty = jsonObject.getString("stock_qty");
            String category_name = jsonObject.getString("category_name");
            String product_pic = jsonObject.getString("img_url");
            //Toast.makeText(ProductOverview.this, product_pic, Toast.LENGTH_LONG).show();

            if (product_id.contains(numA)) {
                id.setText(product_id);
                name.setText(product_name);
                price.setText("RM " + selling_price);
                Glide.with(ProductOverview.this).load(product_pic).override(200, 200).into(image);
                desProduct.setText(description);
                detail.setText(ingredients);
                stock.setText(stock_qty);
            }
        }
    }

    private void showProdC1(JSONArray jsonArray) throws JSONException {

        JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt(numC1));
        String product_id = jsonObject.getString("product_id");
        String category_id = jsonObject.getString("category_id");
        String product_name = jsonObject.getString("product_name");
        String selling_price = jsonObject.getString("selling_price");
        String description = jsonObject.getString("description");
        String ingredients = jsonObject.getString("ingredients");
        String stock_qty = jsonObject.getString("stock_qty");
        String category_name = jsonObject.getString("category_name");
        String product_pic = jsonObject.getString("img_url");

        if (category_id.contains("1")) {
            id.setText(product_id);
            name.setText(product_name);
            price.setText("RM " + selling_price);
            Glide.with(ProductOverview.this).load(product_pic).override(200, 200).into(image);
            desProduct.setText(description);
            detail.setText(ingredients);
            stock.setText(stock_qty);
        }
    }

    private void showProdC2(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt(numC2));
        String product_id = jsonObject.getString("product_id");
        String category_id = jsonObject.getString("category_id");
        String product_name = jsonObject.getString("product_name");
        String selling_price = jsonObject.getString("selling_price");
        String description = jsonObject.getString("description");
        String ingredients = jsonObject.getString("ingredients");
        String stock_qty = jsonObject.getString("stock_qty");
        String category_name = jsonObject.getString("category_name");
        String product_pic = jsonObject.getString("img_url");

        if (category_id.contains("2")) {
            id.setText(product_id);
            name.setText(product_name);
            price.setText("RM " + selling_price);
            Glide.with(ProductOverview.this).load(product_pic).override(200, 200).into(image);
            desProduct.setText(description);
            detail.setText(ingredients);
            stock.setText(stock_qty);
        }
    }

    private void showProdC3(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt(numC3));
        String product_id = jsonObject.getString("product_id");
        String category_id = jsonObject.getString("category_id");
        String product_name = jsonObject.getString("product_name");
        String selling_price = jsonObject.getString("selling_price");
        String description = jsonObject.getString("description");
        String ingredients = jsonObject.getString("ingredients");
        String stock_qty = jsonObject.getString("stock_qty");
        String category_name = jsonObject.getString("category_name");
        String product_pic = jsonObject.getString("img_url");

        if (category_id.contains("3")) {
            id.setText(product_id);
            name.setText(product_name);
            price.setText("RM " + selling_price);
            Glide.with(ProductOverview.this).load(product_pic).override(200, 200).into(image);
            desProduct.setText(description);
            detail.setText(ingredients);
            stock.setText(stock_qty);
        }
    }

    private void showProdC4(JSONArray jsonArray) throws JSONException {
        JSONObject jsonObject = jsonArray.getJSONObject(Integer.parseInt(numC4));
        String product_id = jsonObject.getString("product_id");
        String category_id = jsonObject.getString("category_id");
        String product_name = jsonObject.getString("product_name");
        String selling_price = jsonObject.getString("selling_price");
        String description = jsonObject.getString("description");
        String ingredients = jsonObject.getString("ingredients");
        String stock_qty = jsonObject.getString("stock_qty");
        String category_name = jsonObject.getString("category_name");
        String product_pic = jsonObject.getString("img_url");

        if (category_id.contains("4")) {
            id.setText(product_id);
            name.setText(product_name);
            price.setText("RM " + selling_price);
            Glide.with(ProductOverview.this).load(product_pic).override(200, 200).into(image);
            desProduct.setText(description);
            detail.setText(ingredients);
            stock.setText(stock_qty);
        }
    }
}
