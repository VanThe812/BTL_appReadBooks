package com.example.btl_appreadbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_appreadbooks.QuanLy.QuanLy;
import com.example.btl_appreadbooks.QuanLy.QuanLyAdapter;
import com.example.btl_appreadbooks.search.Search;
import com.example.btl_appreadbooks.search.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuanLyTheLoaiActivity extends AppCompatActivity {
    private RecyclerView rcv_qltheloai;
    private ImageView btnAdd, btn_close;
    private QuanLyAdapter quanLyAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(sharedpreferences.getInt("quyen", 0)!=1){
            finish();
        }


        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_quan_ly_the_loai);
        btnAdd = findViewById(R.id.btnAdd_tl);
        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanLyTheLoaiActivity.this, AddTheLoaiActivity.class);
                startActivity(intent);
            }
        });

            
        rcv_qltheloai = findViewById(R.id.rcv_qltheloai);
        quanLyAdapter = new QuanLyAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qltheloai.setLayoutManager(linearLayoutManager);
        loadData();

    }
    public void loadData(){
        quanLyAdapter.setData(getListQuanLy());
        rcv_qltheloai.setAdapter(quanLyAdapter);
    }
    public List<QuanLy> getListQuanLy() {
        List<QuanLy> list = new ArrayList<>();
        //get data
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM TheLoai");
        byte[] hinhanh = new byte[0];
        while (cursor.moveToNext()){
            list.add(new QuanLy(cursor.getInt(0), cursor.getBlob(2), cursor.getString(1)));
        }
        return list;

    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = ((QuanLyAdapter) rcv_qltheloai.getAdapter()).getPosition();
        } catch (Exception e) {
//            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuanLyTheLoaiActivity.this, AddTheLoaiActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);

                finish();
                break;
            case R.id.delete:
                // do your stuff
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                MainActivity.database.Delete_TheLoai(position);
                loadData();
                break;
        }
        return super.onContextItemSelected(item);
    }


}