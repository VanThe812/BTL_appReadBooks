package com.example.btl_appreadbooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.books.Books;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

//    private TabLayout mTabLayout;
//    private ViewPager2 mViewPager;
//    private MyViewPagerAdapter myViewPagerAdapter;

    private BottomNavigationView bottomNavigationView;
    private ViewPager mViewPagerMenu;
    private ViewPagerMenuAdapter viewPagerMenuAdapter;
    private Button btn_search;

    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
//        actionBar.setDisplayShowCustomEnabled(true);

//        mTabLayout = findViewById(R.id.tab_layout);
//        mViewPager = findViewById(R.id.view_pager);
//
//        myViewPagerAdapter = new MyViewPagerAdapter(this);
//        mViewPager.setAdapter(myViewPagerAdapter);
//        mViewPager.setUserInputEnabled(false);
//        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
//            switch (position){
//                case 0: tab.setText("Home");
//
//                    break;
//                case 1: tab.setText("Phổ biến");break;
//                case 2: tab.setText("Mới nhất");break;
//            }
//        }).attach();

        database = new Database(this, "DocSach.sqlite", null, 1);//tạo mới 1 database
        database.QueryData("CREATE TABLE IF NOT EXISTS TheLoai(PK_MaTL INTEGER PRIMARY KEY AUTOINCREMENT, TenLoai varchar(150), HinhAnh_TL  BLOB)");
        database.QueryData("CREATE TABLE IF NOT EXISTS Sach(PK_MaSach INTEGER PRIMARY KEY AUTOINCREMENT, TenSach varchar(250), GioiThieu TEXT, TacGia varchar(100), FK_MaTL INTEGER, HinhAnh_S BLOB)");
        database.QueryData("CREATE TABLE IF NOT EXISTS TaiKhoan(PK_TaiKhoan INTEGER PRIMARY KEY AUTOINCREMENT, TenTaiKhoan varchar(250), MatKhau varchar(250), Quyen INTEGER)");
//        database.QueryData("INSERT INTO TaiKhoan VALUES(null, admin, 123456, 1)");
//        database.Insert_TaiKhoan("admin", "123456", 1);


        bottomNavigationView = findViewById(R.id.bottom_nav);
        mViewPagerMenu = findViewById(R.id.view_pager_menu);

        viewPagerMenuAdapter = new ViewPagerMenuAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPagerMenu.setAdapter(viewPagerMenuAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        mViewPagerMenu.setCurrentItem(0);
//                        Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_category:
                        mViewPagerMenu.setCurrentItem(1);
//                        Toast.makeText(MainActivity.this, "Thể loại", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_user:
                        mViewPagerMenu.setCurrentItem(2);
//                        Toast.makeText(MainActivity.this, "Cá nhân", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

//        //danhmuc
//        rcv_category_books = findViewById(R.id.rcv_category);
//        category_booksAdapter = new Category_BooksAdapter(this);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        rcv_category_books.setLayoutManager(linearLayoutManager);
//
//        category_booksAdapter.setData(getListCategoryBooks());
//        rcv_category_books.setAdapter(category_booksAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void setUpViewPager(){

    }

}