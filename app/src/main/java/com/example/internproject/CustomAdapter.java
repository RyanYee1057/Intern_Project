package com.example.internproject;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<productModel> productList, filterList;
    ItemFilter fi;

    public CustomAdapter(Context context, ArrayList<productModel> product) {
        this.context = context;
        this.productList = product;
        this.filterList = product;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list, null);

        TextView product_id = view.findViewById(R.id.p_id);
        TextView category_id = view.findViewById(R.id.c_id);
        TextView selling_price = view.findViewById(R.id.price);
        TextView product_name = view.findViewById(R.id.p_name);
        TextView category_name = view.findViewById(R.id.c_name);
        ImageView product_pic = (ImageView) view.findViewById(R.id.p_pic);

        product_id.setText(productList.get(position).getP_id());
        category_id.setText(productList.get(position).getC_id());
        selling_price.setText(productList.get(position).getPrice());
        product_name.setText(productList.get(position).getP_name());
        category_name.setText(productList.get(position).getC_name());
        //product_pic.setImage(productList.get(position).getP_pic());

        return view;
    }

    @Override
    public Filter getFilter() {
        if(fi == null){
            fi = new ItemFilter();
        }
        return fi;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if(constraint!=null && constraint.length()>0){
                constraint = constraint.toString().toLowerCase();
                ArrayList<productModel> done = new ArrayList<>();

                for(int i=0; i<filterList.size();i++){
                    if(filterList.get(i).getP_name().toLowerCase().contains(constraint)){
                        productModel p = new productModel(filterList.get(i).getP_name(), filterList.get(i).getP_id(),
                                filterList.get(i).getC_name(), filterList.get(i).getC_id(), (filterList.get(i).getPrice()));
                        done.add(p);
                    }
                }
                results.values = done;
                results.count = done.size();
            }
            else{
                results.values = filterList;
                results.count = filterList.size();
            }

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productList = (ArrayList<productModel>) results.values;
            notifyDataSetChanged();
        }
    }

}
