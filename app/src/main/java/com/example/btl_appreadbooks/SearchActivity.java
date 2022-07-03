package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoai;
import com.example.btl_appreadbooks.search.Search;
import com.example.btl_appreadbooks.search.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    private ImageView btn_xoa;
    private EditText et_noidung_search;
    private TextView tv_huy;
    private RecyclerView rcv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search);

        rcv_search = findViewById(R.id.rcv_search);
        SearchAdapter searchAdapter = new SearchAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_search.setLayoutManager(linearLayoutManager);

        searchAdapter.setData(getListSach(""));
        rcv_search.setAdapter(searchAdapter);

        btn_xoa = findViewById(R.id.btn_xoa);
        et_noidung_search = findViewById(R.id.et_noidung_search);
        btn_xoa.setVisibility(View.INVISIBLE);
        et_noidung_search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(et_noidung_search.getText().toString().matches("")){
                    btn_xoa.setVisibility(View.INVISIBLE);
                    searchAdapter.setData(getListSach(""));
                    rcv_search.setAdapter(searchAdapter);
                }else{
                    btn_xoa.setVisibility(View.VISIBLE);
                    searchAdapter.setData(getListSach(et_noidung_search.getText().toString().trim()));
                    rcv_search.setAdapter(searchAdapter);
                }
                return false;
            }
        });

        btn_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_noidung_search.setText("");
                searchAdapter.setData(getListSach(""));
                rcv_search.setAdapter(searchAdapter);
            }
        });
        tv_huy = findViewById(R.id.tv_huy);
        tv_huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private List<Search> getListSach(String tensach) {
        List<Search> list = new ArrayList<>();
        if(tensach==""){
            for (int i=0; i<10; i++){
                list.add(new Search(1, false, ""));
            }
        }else{
            Cursor cursor = MainActivity.database.GetData("SELECT PK_MaSach, TenSach FROM Sach WHERE TenSach LIKE '%"+tensach+"%'");
            while (cursor.moveToNext()){
                list.add(new Search(cursor.getInt(0), true, cursor.getString(1)));
            }
        }

        return list;
    }
}