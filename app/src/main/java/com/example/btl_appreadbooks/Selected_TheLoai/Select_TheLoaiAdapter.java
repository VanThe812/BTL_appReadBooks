package com.example.btl_appreadbooks.Selected_TheLoai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.search.Search;

import java.util.List;

public class Select_TheLoaiAdapter extends ArrayAdapter<Select_TheLoai> {
    public Select_TheLoaiAdapter(@NonNull Context context, int resource, @NonNull List<Select_TheLoai> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_selected_theloai, parent, false);
        TextView tv_selected_theloai = convertView.findViewById(R.id.tv_selected_theloai);

        Select_TheLoai select_theLoai = this.getItem(position);
        if(select_theLoai != null){
            tv_selected_theloai.setText(select_theLoai.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ds_theloai, parent, false);
        TextView tv_dstheloai = convertView.findViewById(R.id.tv_dstheloai);

        Select_TheLoai select_theLoai = this.getItem(position);
        if(select_theLoai != null){
            tv_dstheloai.setText(select_theLoai.getName());
        }
        return convertView;
    }
}
