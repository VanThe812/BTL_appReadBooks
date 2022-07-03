package com.example.btl_appreadbooks.TheLoai;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.ChiTietSachActivity;
import com.example.btl_appreadbooks.ChiTietTheLoaiActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.Books;
import com.example.btl_appreadbooks.books.BooksAdapter;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewholder>{

    private List<TheLoai> mTheLoais;
    private Context mContext;
    public void setData(Context context, List<TheLoai> list){
        this.mContext = context;
        this.mTheLoais = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheLoaiViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theloai, parent, false);

        return new TheLoaiAdapter.TheLoaiViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewholder holder, int position) {
        final TheLoai theLoai = mTheLoais.get(position);
        if(theLoai == null){
            return;
        }
        //chuyen byte[] -> bitmap
        byte[] hinhanh = theLoai.getMaanh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.imgtheloai.setImageBitmap(bitmap);
        holder.tv_tentheloai.setText(theLoai.getTentheloai());

        holder.layout_item_theloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onClickGotoChiTietTheLoai(theLoai);
            }
        });
    }
    private void onClickGotoChiTietTheLoai(TheLoai theloai) {
        Intent intent = new Intent(mContext, ChiTietTheLoaiActivity.class);

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("obj_theloai", theloai);

        intent.putExtra("id", theloai.getMatheloai());
        intent.putExtra("tentheloai", theloai.getTentheloai());
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mTheLoais!=null){
            return mTheLoais.size();
        }
        return 0;
    }

    public class TheLoaiViewholder extends RecyclerView.ViewHolder{
        private RelativeLayout layout_item_theloai;
        private ImageView imgtheloai;
        private TextView tv_tentheloai;

        public TheLoaiViewholder(@NonNull View itemView) {
            super(itemView);

            layout_item_theloai = itemView.findViewById(R.id.layout_item_theloai);
            imgtheloai = itemView.findViewById(R.id.img_theloai);
            tv_tentheloai = itemView.findViewById(R.id.tv_tentheloai);


        }
    }
}
