package com.example.btl_appreadbooks.books;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.ChiTietSachActivity;
import com.example.btl_appreadbooks.R;

import java.util.List;

public class BooksTheloaiAdapter extends RecyclerView.Adapter<BooksTheloaiAdapter.BookTheLoaiViewholder>{
    private List<Books> mBooks;
    private Context mContext;
    public void setData(Context context, List<Books> list){
        this.mContext = context;
        this.mBooks = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookTheLoaiViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet_theloai, parent, false);

        return new BookTheLoaiViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookTheLoaiViewholder holder, int position) {
        final Books book = mBooks.get(position);
        if(book == null){
            return;
        }
        holder.imgBook.setImageResource(book.getResourceId());
        holder.tv_title.setText(book.getTitle());

        holder.layout_item_chitiet_theloai.setOnClickListener(new View.OnClickListener() {
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

    public class BookTheLoaiViewholder extends RecyclerView.ViewHolder{
        private RelativeLayout layout_item_chitiet_theloai;
        private ImageView imgBook;
        private TextView tv_title;

        public BookTheLoaiViewholder(@NonNull View itemView) {
            super(itemView);

            layout_item_chitiet_theloai = itemView.findViewById(R.id.layout_item_chitiet_theloai);
            imgBook = itemView.findViewById(R.id.img_chitiettheloai);
            tv_title = itemView.findViewById(R.id.tv_chitiettheloai);


        }
    }
}
