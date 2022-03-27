package com.example.btl_appreadbooks.Category_Books;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.BooksAdapter;
import com.example.btl_appreadbooks.books.BooksTheloaiAdapter;

import java.util.List;

public class Category_BooksTheLoaiAdapter extends RecyclerView.Adapter<Category_BooksTheLoaiAdapter.CategoryBooksTheLoaiViewHolder> {

    private Context mContext;
    private List<Category_BooksTheLoai> mListCategory;

    public Category_BooksTheLoaiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Category_BooksTheLoai> list){
        this.mListCategory = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryBooksTheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category_books_theloai, parent, false);
        return new CategoryBooksTheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryBooksTheLoaiViewHolder holder, int position) {
        Category_BooksTheLoai category = mListCategory.get(position);
        if(category==null){
            return;
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvBook.setLayoutManager(linearLayoutManager);

        BooksTheloaiAdapter booksTheloaiAdapter = new BooksTheloaiAdapter();
        booksTheloaiAdapter.setData(mContext, category.getBooks());
        holder.rcvBook.setAdapter(booksTheloaiAdapter);
    }

    @Override
    public int getItemCount() {
        if(mListCategory!=null){
            return mListCategory.size();
        }
        return 0;
    }

    public class CategoryBooksTheLoaiViewHolder extends RecyclerView.ViewHolder{


        private RecyclerView rcvBook;

        public CategoryBooksTheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);

            rcvBook = itemView.findViewById(R.id.rcv_theloai_chitiet);
        }
    }
}
