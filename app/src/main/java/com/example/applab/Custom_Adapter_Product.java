package com.example.applab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Custom_Adapter_Product extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Product> productList;

    public Custom_Adapter_Product (Context context, int layout, List<Product> productList) {
        this.context = context;
        this.layout = layout;
        this.productList = productList;
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
    public long getItemId(int position) {
        return position;
    }

    // ViewHolder để tối ưu hiệu suất
    private class ViewHolder {
        ImageView imageViewProduct;
        TextView textViewProductName;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(layout, null);
            holder.imageViewProduct = convertView.findViewById(R.id.imageViewProduct);
            holder.textViewProductName = convertView.findViewById(R.id.textViewProductName);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        // Lấy sản phẩm tại vị trí hiện tại
        Product product = productList.get(position);

        // Gán dữ liệu
        holder.textViewProductName.setText(product.getName());
        holder.imageViewProduct.setImageResource(product.getImageResId());

        return convertView;
    }
}

