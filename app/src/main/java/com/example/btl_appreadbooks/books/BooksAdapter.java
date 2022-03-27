package com.example.btl_appreadbooks.books;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.ChiTietSachActivity;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.R;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewholder> {

    private List<Books> mBooks;
    private Context mContext;
    public void setData(Context context, List<Books> list){
        this.mContext = context;
        this.mBooks = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);

        return new BookViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewholder holder, int position) {
        final Books book = mBooks.get(position);
        if(book == null){
            return;
        }
        holder.imgBook.setImageResource(book.getResourceId());
        holder.tv_title.setText(book.getTitle());

        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickGotoChiTiet(book);
            }
        });

    }
    private void onClickGotoChiTiet(Books books) {
        Intent intent = new Intent(mContext, ChiTietSachActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_book", books);

        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mBooks!=null){
            return mBooks.size();
        }
        return 0;
    }

    public class BookViewholder extends RecyclerView.ViewHolder{
        private RelativeLayout layout_item;
        private ImageView imgBook;
        private TextView tv_title;

        public BookViewholder(@NonNull View itemView) {
            super(itemView);

            layout_item = itemView.findViewById(R.id.layout_item);
            imgBook = itemView.findViewById(R.id.img_book);
            tv_title = itemView.findViewById(R.id.tv_title);


        }
    }
}
