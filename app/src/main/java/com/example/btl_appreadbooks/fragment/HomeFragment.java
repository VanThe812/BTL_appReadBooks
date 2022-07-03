package com.example.btl_appreadbooks.fragment;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.ContactDBHelper;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.QuanLy.QuanLy;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.SearchActivity;
import com.example.btl_appreadbooks.books.Books;
import com.example.btl_appreadbooks.slide.Photo;
import com.example.btl_appreadbooks.slide.PhotoAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class HomeFragment extends Fragment {

    private RecyclerView rcv_category_books;
    private Category_BooksAdapter category_booksAdapter;

    private ViewFlipper viewFlipper;
    private ContactDBHelper contactDBHelper;
    ArrayList<Books> lsContact = new ArrayList<>();

    private ViewPager2 mViewpager2;
    private CircleIndicator3 mCirleIndicator3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        viewFlipper = view.findViewById(R.id.viewPlipper);
//        ActionviewFlipper(inflater.getContext());

//        contactDBHelper = new ContactDBHelper(inflater.getContext());
//        lsContact = contactDBHelper.getAllBooks();

        rcv_category_books = view.findViewById(R.id.rcv_category);
        category_booksAdapter = new Category_BooksAdapter(inflater.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        rcv_category_books.setLayoutManager(linearLayoutManager);

        //thêm đg biên cho các rcv
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(inflater.getContext(), DividerItemDecoration.VERTICAL);
        rcv_category_books.addItemDecoration(itemDecoration);

        category_booksAdapter.setData(getListCategoryBooks());
        rcv_category_books.setAdapter(category_booksAdapter);

        //slide
        mViewpager2 = view.findViewById(R.id.view_pager2);
        mCirleIndicator3 = view.findViewById(R.id.circleindicator3);

        PhotoAdapter photoAdapter = new PhotoAdapter(getActivity(), getListPhoto());
        mViewpager2.setAdapter(photoAdapter);
        mCirleIndicator3.setViewPager(mViewpager2);

        return view;
    }
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.slide_sach1));
        list.add(new Photo(R.drawable.slide_sach2));
        list.add(new Photo(R.drawable.slide_sach3));
        list.add(new Photo(R.drawable.slide_sach4));


        return list;
    }

    private List<Category_Books> getListCategoryBooks(){

        List<Category_Books> listCategory = new ArrayList<>();
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM TheLoai");
        while (cursor.moveToNext()){
            List<Books> listBook = new ArrayList<>();
            Cursor cursor2 = MainActivity.database.GetData("SELECT PK_MaSach, TenSach, HinhAnh_S FROM Sach WHERE FK_MaTL = "+cursor.getInt(0));

            while (cursor2.moveToNext()){
                listBook.add(new Books(cursor2.getInt(0), cursor2.getString(1), cursor2.getBlob(2)));
            }
            if(!listBook.isEmpty()){
                listCategory.add(new Category_Books(cursor.getString(1), listBook));
            }

        }


        return listCategory;
    }
    private void ActionviewFlipper(Context mcoContext) {
        ArrayList<String> mangquangcao =new ArrayList<>();
        mangquangcao.add("drawable/slide_sach1.jpg");
        mangquangcao.add("drawable/slide_sach2.jpg");
        mangquangcao.add("drawable/slide_sach3.jpg");
        mangquangcao.add("drawable/slide_sach4.jpg");

        //thuc hien vong lap gan anh vao imgView
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView=new ImageView(mcoContext);
            //su dung ham thu vien picasso
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //phuong thuc chinh tam hinh vua voi khung quang cao
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            //them anh tu imgView vao ViewFliper
            viewFlipper.addView(imageView);
        }

        //thiet lap tu dong chay cho viewFliper
        viewFlipper.setFlipInterval(4000);

        viewFlipper.setAutoStart(true);

        //goi animation cho vao cho ra
        Animation annotation_slide_in= AnimationUtils.loadAnimation(mcoContext,R.anim.slide_in_right);
        Animation animation_slide_out=AnimationUtils.loadAnimation(mcoContext,R.anim.slide_out_right);

        //goi animation
        viewFlipper.setInAnimation(annotation_slide_in);
        viewFlipper.setInAnimation(animation_slide_out);


    }
}
