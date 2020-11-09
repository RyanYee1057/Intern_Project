package com.example.internproject;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class cAdapter extends BaseAdapter {
    Context context;
    ArrayList<productModel> categoryList;

    public cAdapter(Context context, ArrayList<productModel> product) {
        this.context = context;
        this.categoryList = product;
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.category_title, null);

        TextView category_name = view.findViewById(R.id.ca_name);
        ImageView category_pic = (ImageView) view.findViewById(R.id.c_pic);

        category_name.setText(categoryList.get(position).getC_name());

        Glide.with(context).load(categoryList.get(position).getC_pic()).override(120, 120).into(category_pic);

        return view;
    }
}
