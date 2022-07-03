package com.example.btl_appreadbooks.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.ChiTietSachActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.Books;
import com.example.btl_appreadbooks.books.BooksAdapter;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewholder>{
    private List<Search> mSearches;
    private Context mContext;
    public void setData(List<Search> list){
        this.mSearches = list;
        notifyDataSetChanged();
    }

    public SearchAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public SearchViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);

        return new SearchViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewholder holder, int position) {
        final Search search = mSearches.get(position);
        if(search == null){
            return;
        }
        holder.tv_tensach.setText(search.getTitleSach());
        if(search.isStatus()){
            holder.layout_item_search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickGotoChiTiet(search);
                }
            });
        }

    }
    private void onClickGotoChiTiet(Search search) {
        Intent intent = new Intent(mContext, ChiTietSachActivity.class);


        intent.putExtra("id", search.getIdsach());
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mSearches!=null){
            return mSearches.size();
        }
        return 0;
    }

    public class SearchViewholder extends RecyclerView.ViewHolder{
        private LinearLayout layout_item_search;
        private TextView tv_tensach;

        public SearchViewholder(@NonNull View itemView) {
            super(itemView);

            layout_item_search = itemView.findViewById(R.id.layout_item_search);
            tv_tensach = itemView.findViewById(R.id.tv_tensach);


        }
    }
}
