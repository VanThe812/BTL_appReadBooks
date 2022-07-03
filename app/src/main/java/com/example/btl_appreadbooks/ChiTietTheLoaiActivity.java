package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.btl_appreadbooks.Category_Books.Category_BooksTheLoai;
import com.example.btl_appreadbooks.Category_Books.Category_BooksTheLoaiAdapter;
import com.example.btl_appreadbooks.QuanLy.QuanLy;
import com.example.btl_appreadbooks.TheLoai.TheLoai;
import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;
import java.util.List;

public class ChiTietTheLoaiActivity extends AppCompatActivity {

    private RecyclerView rcv_category_books;
    private Category_BooksTheLoaiAdapter category_booksTheLoaiAdapter;

    private ImageButton btn_close;
    private TextView tv_title_chitet_theloai;
    int matl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_chi_tiet_the_loai);
        tv_title_chitet_theloai = findViewById(R.id.tv_huy);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null)
            return;
        matl = bundle.getInt("id");

        tv_title_chitet_theloai.setText(bundle.getString("tentheloai"));

        btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        rcv_category_books = findViewById(R.id.rcv_chitiet_theoai);
        category_booksTheLoaiAdapter = new Category_BooksTheLoaiAdapter(ChiTietTheLoaiActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ChiTietTheLoaiActivity.this, RecyclerView.VERTICAL, false);
        rcv_category_books.setLayoutManager(linearLayoutManager);

        //thêm đg biên cho các rcv
//        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(ChiTietTheLoaiActivity.this, DividerItemDecoration.VERTICAL);
//        rcv_category_books.addItemDecoration(itemDecoration);

        category_booksTheLoaiAdapter.setData(getListCategoryBooks());
        rcv_category_books.setAdapter(category_booksTheLoaiAdapter);

    }


    private List<Category_BooksTheLoai> getListCategoryBooks() {
        List<Category_BooksTheLoai> listCategory = new ArrayList<>();

        List<Books> listBook = new ArrayList<>();

        Cursor cursor = MainActivity.database.GetData("SELECT PK_MaSach, TenSach, HinhAnh_S FROM Sach WHERE FK_MaTL = "+matl);
        int dem = 1;
        while (cursor.moveToNext()){
            listBook.add(new Books(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2)));
            if(dem %3 == 0){
                listCategory.add(new Category_BooksTheLoai(listBook));
                listBook = new ArrayList<>();
            }
            dem++;
        }
        if(listBook!=null){
            listCategory.add(new Category_BooksTheLoai(listBook));
        }


        return listCategory;

    }

}