package com.example.btl_appreadbooks.Category_TheLoai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.TheLoai.TheLoaiAdapter;
import com.example.btl_appreadbooks.books.BooksAdapter;

import java.util.List;

public class Category_TheloaiAdapter extends RecyclerView.Adapter<Category_TheloaiAdapter.Category_TheloaiViewHolder>{

    private Context mContext;
    private List<Category_Theloai> mCategory_theloais;

    public Category_TheloaiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category_Theloai> list){
        this.mCategory_theloais = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Category_TheloaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_theloai, parent, false);
        return new Category_TheloaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Category_TheloaiViewHolder holder, int position) {
        Category_Theloai category_theloai = mCategory_theloais.get(position);
        if(category_theloai==null){
            return;
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvTheLoai.setLayoutManager(linearLayoutManager);

        TheLoaiAdapter theLoaiAdapter = new TheLoaiAdapter();
        theLoaiAdapter.setData(mContext, category_theloai.getmTheLoais());
        holder.rcvTheLoai.setAdapter(theLoaiAdapter);
    }

    @Override
    public int getItemCount() {
        if(mCategory_theloais!=null){
            return mCategory_theloais.size();
        }
        return 0;
    }

    public class Category_TheloaiViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rcvTheLoai;

        public Category_TheloaiViewHolder(@NonNull View itemView) {
            super(itemView);
            rcvTheLoai = itemView.findViewById(R.id.rcv_theloai);
        }
    }
}
