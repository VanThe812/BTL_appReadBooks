package com.example.btl_appreadbooks.QuanLy;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.AddTheLoaiActivity;
import com.example.btl_appreadbooks.ChiTietSachActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.search.Search;

import java.util.List;

public class QuanLyAdapter extends RecyclerView.Adapter<QuanLyAdapter.QuanLyViewholder>{
    private List<QuanLy> mQuanLy;
    private Context mContext;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    public void setData(List<QuanLy> list){
        this.mQuanLy = list;
        notifyDataSetChanged();
    }
    public QuanLyAdapter(Context mContext) {
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public QuanLyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quanly, parent, false);
        return new QuanLyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuanLyViewholder holder, int position) {
        final QuanLy quanLy = mQuanLy.get(position);
        if(quanLy == null){
            return;
        }
        holder.tv_tieude_chitiet.setText(quanLy.getTitle());
        holder.tv_tieude_chitiet.setTextSize(24);

        //chuyen byte[] -> bitmap
        byte[] hinhanh = quanLy.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
        holder.iv_anh_quanly.setImageBitmap(bitmap);

        holder.layout_quanly.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(quanLy.getId());
                return false;
            }
        });

    }
    private void onClickGotoAdd(QuanLy quanLy) {
        Intent intent = new Intent(mContext, AddTheLoaiActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("obj_theloai", quanLy);

        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }
    @Override
    public int getItemCount() {
        if(mQuanLy!=null){
            return mQuanLy.size();
        }
        return 0;
    }

    public class QuanLyViewholder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private LinearLayout layout_quanly;
        private TextView tv_tieude_chitiet;
        private ImageView iv_anh_quanly;

        public QuanLyViewholder(@NonNull View itemView) {
            super(itemView);

            layout_quanly = itemView.findViewById(R.id.layout_quanly);
            tv_tieude_chitiet = itemView.findViewById(R.id.tv_tieude_chitiet);
            iv_anh_quanly = itemView.findViewById(R.id.iv_anh_quanly);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(Menu.NONE, R.id.update, Menu.NONE, "Update");
            contextMenu.add(Menu.NONE, R.id.delete, Menu.NONE, "Delete");
        }
    }
}
