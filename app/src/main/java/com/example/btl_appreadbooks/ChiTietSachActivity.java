package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSachActivity extends AppCompatActivity {
    TextView tv_chitiet, tv_xemthem, tv_tensach, tv_tentg, tv_tim, tv_luotxem;
    ImageButton btn_quaylai;
    Button btn_docngay;
    ImageView img_anhsach, img_tim, img_luotxem;
    int matl, masach;
    String tensach;
    private RecyclerView rcv_category_books;
    private Category_BooksAdapter category_booksAdapter;
    private int status_tim = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_chi_tiet_sach);

        tv_chitiet = findViewById(R.id.tv_chitiet);
        tv_xemthem = findViewById(R.id.tv_xemthem);
        tv_tensach = findViewById(R.id.tv_tensach);
        tv_tentg = findViewById(R.id.tv_tentg);

        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_docngay = findViewById(R.id.btn_docngay);
        img_anhsach = findViewById(R.id.img_anhsach);
        img_tim = findViewById(R.id.img_tim);
        img_luotxem = findViewById(R.id.img_luotxem);



        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
            return;
        try {
            if(bundle.getInt("id")>0)
            {
                Cursor cursor = MainActivity.database.GetData("SELECT * FROM Sach WHERE PK_MaSach = "+bundle.getInt("id"));
                while (cursor.moveToNext()){
                    tv_tensach.setText(cursor.getString(1));
                    tv_chitiet.setText(cursor.getString(2));
                    tv_tentg.setText(cursor.getString(3));
                    masach = cursor.getInt(0);
                    matl = cursor.getInt(4);
                    tensach = cursor.getString(1);
                    //chuyen byte[] -> bitmap
                    byte[] hinhanh = cursor.getBlob(5);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                    img_anhsach.setImageBitmap(bitmap);


                }
            }
        }catch (Exception e){

        }


        rcv_category_books = findViewById(R.id.rcv_category);
        category_booksAdapter = new Category_BooksAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcv_category_books.setLayoutManager(linearLayoutManager);

        //thêm đg biên cho các rcv
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_category_books.addItemDecoration(itemDecoration);

        category_booksAdapter.setData(getListCategoryBooks());
        rcv_category_books.setAdapter(category_booksAdapter);

        tv_xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_chitiet.setMaxLines(50);
                tv_xemthem.setVisibility(View.GONE);
            }
        });
        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_docngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChiTietSachActivity.this, DocSachActivity.class)
                        .putExtra("tensach", tensach));
            }
        });

        img_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status_tim == 0){
                    img_tim.setImageResource(R.drawable.outline_favorite_black_18);
                    status_tim=1;

                }else{
                    img_tim.setImageResource(R.drawable.outline_favorite_border_black_18);
                    status_tim=0;
                }


            }
        });
    }
    private List<Category_Books> getListCategoryBooks(){
        List<Category_Books> listCategory = new ArrayList<>();


        List<Books> listBook = new ArrayList<>();
        Cursor cursor = MainActivity.database.GetData("SELECT PK_MaSach, TenSach, HinhAnh_S FROM Sach WHERE FK_MaTL = "+matl);

        while (cursor.moveToNext()){
            if(masach!=cursor.getInt(0))
                listBook.add(new Books(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2)));
        }
        if(!listBook.isEmpty()){
            listCategory.add(new Category_Books("Có thể bạn quan tâm", listBook));
        }






        return listCategory;
    }
}