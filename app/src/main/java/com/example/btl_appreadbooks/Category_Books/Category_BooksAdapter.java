package com.example.btl_appreadbooks.Category_Books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.BooksAdapter;

import java.util.List;

public class Category_BooksAdapter extends RecyclerView.Adapter<Category_BooksAdapter.CategoryViewHolder>{

    private Context mContext;
    private List<Category_Books> mListCategory;

    public Category_BooksAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category_Books> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_books, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category_Books category = mListCategory.get(position);
        if(category==null){
            return;
        }
        holder.tvnameCategory.setText(category.getCategory());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        BooksAdapter bookAdapter = new BooksAdapter();
        bookAdapter.setData(mContext, category.getBooks());
        holder.rcvBook.setAdapter(bookAdapter);
    }

    @Override
    public int getItemCount() {
        if(mListCategory!=null){
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        private TextView tvnameCategory;
        private RecyclerView rcvBook;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            tvnameCategory = itemView.findViewById(R.id.tv_name_category);
            rcvBook = itemView.findViewById(R.id.rcv_book);
        }
    }
}
