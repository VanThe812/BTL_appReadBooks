package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl_appreadbooks.QuanLy.QuanLy;
import com.example.btl_appreadbooks.QuanLy.QuanLyAdapter;
import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoai;
import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoaiAdapter;

import java.util.ArrayList;
import java.util.List;

public class QuanLySachActivity extends AppCompatActivity {
    private RecyclerView rcv_qlsach;
    private ImageView btnAdd, btn_close;
    private QuanLyAdapter quanLyAdapter;
    private Spinner spinner_theloai;
    private Select_TheLoaiAdapter select_theLoaiAdapter;
    int matl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(sharedpreferences.getInt("quyen", 0)!=1){
            finish();
        }

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_quan_ly_sach);

        btnAdd = findViewById(R.id.btnAdd);
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
                Intent intent = new Intent(QuanLySachActivity.this, AddBookActivity.class);
                startActivity(intent);
                finish();
            }
        });
        rcv_qlsach = findViewById(R.id.rcv_qlsach);
        quanLyAdapter = new QuanLyAdapter(this);
//
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_qlsach.setLayoutManager(linearLayoutManager);

        spinner_theloai = findViewById(R.id.spinner_theloai);
        select_theLoaiAdapter = new Select_TheLoaiAdapter(this, R.layout.item_selected_theloai, getListTheLoai());
        spinner_theloai.setAdapter(select_theLoaiAdapter);
        spinner_theloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                matl = select_theLoaiAdapter.getItem(i).getId();
                loadData();
//                Toast.makeText(QuanLySachActivity.this, ""+select_theLoaiAdapter.getItem(i).getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    public void loadData(){
        quanLyAdapter.setData(getListQuanLy());
        rcv_qlsach.setAdapter(quanLyAdapter);
    }
    public List<QuanLy> getListQuanLy() {
        List<QuanLy> list = new ArrayList<>();
        //get data
        Cursor cursor = MainActivity.database.GetData("SELECT PK_MaSach, TenSach, HinhAnh_S FROM Sach WHERE FK_MaTL = "+matl);

        while (cursor.moveToNext()){
            list.add(new QuanLy(cursor.getInt(0), cursor.getBlob(2), cursor.getString(1)));
        }
        return list;

    }
    private List<Select_TheLoai> getListTheLoai() {
        List<Select_TheLoai> list = new ArrayList<>();
        Cursor cursor = MainActivity.database.GetData("SELECT PK_MaTL, TenLoai FROM TheLoai");
        while (cursor.moveToNext()){
            list.add(new Select_TheLoai(cursor.getInt(0), cursor.getString(1)));
        }
        return list;
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int position = -1;
        try {
            position = ((QuanLyAdapter) rcv_qlsach.getAdapter()).getPosition();
        } catch (Exception e) {
//            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()) {
            case R.id.update:
                Toast.makeText(this, "Update", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(QuanLySachActivity.this, AddBookActivity.class);
                intent.putExtra("id", position);
                startActivity(intent);

                finish();
                break;
            case R.id.delete:
                // do your stuff
                Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
                MainActivity.database.Delete_Sach(position);
                loadData();
                break;
        }
        return super.onContextItemSelected(item);
    }
}