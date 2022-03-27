package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSachActivity extends AppCompatActivity {
    TextView tv_chitiet, tv_xemthem, tv_tensach, tv_tentg;
    ImageButton btn_quaylai;
    Button btn_docngay;

    private RecyclerView rcv_category_books;
    private Category_BooksAdapter category_booksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_sach);

        tv_chitiet = findViewById(R.id.tv_chitiet);
        tv_xemthem = findViewById(R.id.tv_xemthem);
        tv_tensach = findViewById(R.id.tv_tensach);
        tv_tentg = findViewById(R.id.tv_tentg);

        btn_quaylai = findViewById(R.id.btn_quaylai);
        btn_docngay = findViewById(R.id.btn_docngay);
        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
            return;
        Books books = (Books) bundle.get("obj_book");
        //xu ly du lieu nhan ve
        //String text = "Park Seo Joon đã xác nhận tham gia dự án bom tấn nằm trong vũ trụ điện ảnh Marvel (MCU) là Captain Marvel 2: The Marvels Trong một cuộc phỏng vấn với The Guardian, Park Seo Joon đã nói về cảm nghĩ của bản thân khi được tham gia MCU: Khi lần đầu tiên tôi nghe được thông tin MCU muốn tôi gia nhập vũ trụ của họ, tôi không thể tin đó là thật. Thật sự là tôi cảm thấy khó tin. Tôi cố gắng thật chú ý với những câu hỏi liên quan đến Marvel Park Seo Joon cũng tỏ ra kín tiếng và cẩn thận với những câu hỏi liên quan đến Marvel. “Đây không phải điều gì ngạc nhiên, bởi các diễn viên của Marvel đều phải giữ bí mật tuyệt đối về tình tiết phim mới";
        tv_tensach.setText(books.getTitle());
        tv_tentg.setText(books.getTacgia());
        tv_chitiet.setText(books.getChitiet());

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
//                startActivity(new Intent(ChiTietSachActivity.this, DocSachActivity.class)
//                        .putExtra("path", R.drawable.BTL));
            }
        });
    }
    private List<Category_Books> getListCategoryBooks(){
        List<Category_Books> listCategory = new ArrayList<>();
        String text = "Park Seo Joon đã xác nhận tham gia dự án bom tấn nằm trong vũ trụ điện ảnh Marvel (MCU) là Captain Marvel 2: The Marvels Trong một cuộc phỏng vấn với The Guardian, Park Seo Joon đã nói về cảm nghĩ của bản thân khi được tham gia MCU: Khi lần đầu tiên tôi nghe được thông tin MCU muốn tôi gia nhập vũ trụ của họ, tôi không thể tin đó là thật. Thật sự là tôi cảm thấy khó tin. Tôi cố gắng thật chú ý với những câu hỏi liên quan đến Marvel Park Seo Joon cũng tỏ ra kín tiếng và cẩn thận với những câu hỏi liên quan đến Marvel. “Đây không phải điều gì ngạc nhiên, bởi các diễn viên của Marvel đều phải giữ bí mật tuyệt đối về tình tiết phim mới";

        List<Books> listBook = new ArrayList<>();
        listBook.add(new Books(R.drawable.img, 1, "1_89.000 ₫", text, "1Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 2, "2_89.000 ₫", text, "2Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 3, "3_89.000 ₫", text, "3Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 4, "4_89.000 ₫", text, "4Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 5, "5_89.000 ₫", text, "5Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 6, "6_89.000 ₫", text, "6Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 7, "7_89.000 ₫", text, "7Nguyen Nhat Anh"));
        listBook.add(new Books(R.drawable.img, 8, "8_89.000 ₫", text, "8Nguyen Nhat Anh"));

        listCategory.add(new Category_Books("Có thể bạn quan tâm", listBook));




        return listCategory;
    }
}