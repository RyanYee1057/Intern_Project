package com.example.internproject;

import java.util.ArrayList;

public class productModel extends ArrayList<productModel> {
    String product_id, category_id, selling_price;
    String product_name, category_name, product_pic, picture_name, category_pic;
    String description, ingredients, quantity;

    public productModel(String p_name, String c_name, String p_id, String c_id, String price, String p_pic){
        this.product_name = p_name;
        this.category_name = c_name;
        this.product_id = p_id;
        this.category_id = c_id;
        this.selling_price = price;
        this.product_pic = p_pic;
    }

    public productModel(String p_name, String c_name, String p_id, String c_id, String price, String p_pic,
                        String pic_name, String c_pic, String desc, String in, String q) {
        this.product_name = p_name;
        this.category_name = c_name;
        this.product_id = p_id;
        this.category_id = c_id;
        this.selling_price = price;
        this.product_pic = p_pic;
        this.picture_name = pic_name;
        this.category_pic = c_pic;
        this.description = desc;
        this.ingredients = in;
        this.quantity = q;
    }

    public productModel() {
    }

    public String getP_id(){
        return product_id;
    }

    public void setP_id(String product_id){
        this.product_id = product_id;
    }

    public String getC_id(){
        return category_id;
    }

    public void setC_id(String category_id){
        this.category_id  = category_id;
    }

    public String getPrice(){
        return selling_price;
    }

    public void setPrice(String selling_price){
        this.selling_price = selling_price;
    }

    public String getP_name(){
        return product_name;
    }

    public void setP_name(String product_name){
        this.product_name = product_name;
    }

    public String getC_name(){
        return category_name;
    }

    public void setC_name(String category_name){
        this.category_name = category_name;
    }

    public void setP_pic(String product_pic){
        this.product_pic = product_pic;
    }

    public String getP_pic(){ return product_pic;}

    public void setPic_name(String picture_name){
        this.picture_name = picture_name;
    }

    public String getPic_name(){ return picture_name;}

    public void setC_pic(String category_pic){
        this.category_pic = category_pic;
    }

    public String getC_pic(){ return category_pic;}

    public void setDesc(String description){ this.description = description; }

    public String getDesc(){ return description;}

    public void setIngredients(String ingredients){
        this.ingredients = ingredients;
    }

    public String getIngredients(){ return ingredients;}

    public void setQuantity(String quantity){ this.quantity = quantity; }

    public String getQuantity(){ return quantity;}
}
