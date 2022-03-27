package com.example.btl_appreadbooks.fragment;

import android.app.AppComponentFactory;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.ContactDBHelper;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.Books;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private RecyclerView rcv_category_books;
    private Category_BooksAdapter category_booksAdapter;

    private ViewFlipper viewFlipper;
    private ContactDBHelper contactDBHelper;
    ArrayList<Books> lsContact = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewFlipper = view.findViewById(R.id.viewPlipper);
        ActionviewFlipper(inflater.getContext());

        contactDBHelper = new ContactDBHelper(inflater.getContext());
        lsContact = contactDBHelper.getAllBooks();

        rcv_category_books = view.findViewById(R.id.rcv_category);
        category_booksAdapter = new Category_BooksAdapter(inflater.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        rcv_category_books.setLayoutManager(linearLayoutManager);

        //thêm đg biên cho các rcv
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(inflater.getContext(), DividerItemDecoration.VERTICAL);
        rcv_category_books.addItemDecoration(itemDecoration);

        category_booksAdapter.setData(getListCategoryBooks(lsContact));
        rcv_category_books.setAdapter(category_booksAdapter);

        return view;
    }


    private List<Category_Books> getListCategoryBooks(ArrayList<Books> lsBooks){

        List<Category_Books> listCategory = new ArrayList<>();
        String text = "Park Seo Joon đã xác nhận tham gia dự án bom tấn nằm trong vũ trụ điện ảnh Marvel (MCU) là Captain Marvel 2: The Marvels Trong một cuộc phỏng vấn với The Guardian, Park Seo Joon đã nói về cảm nghĩ của bản thân khi được tham gia MCU: Khi lần đầu tiên tôi nghe được thông tin MCU muốn tôi gia nhập vũ trụ của họ, tôi không thể tin đó là thật. Thật sự là tôi cảm thấy khó tin. Tôi cố gắng thật chú ý với những câu hỏi liên quan đến Marvel Park Seo Joon cũng tỏ ra kín tiếng và cẩn thận với những câu hỏi liên quan đến Marvel. “Đây không phải điều gì ngạc nhiên, bởi các diễn viên của Marvel đều phải giữ bí mật tuyệt đối về tình tiết phim mới";

        List<Books> listBook = new ArrayList<>();

        for (Books books : lsBooks) {
            listBook.add(new Books(books.getResourceId(), books.getMasach(), books.getTitle(), books.getChitiet(), books.getTacgia()));
        }
        listBook.add(new Books(R.drawable.img, 1, "1_89.000 ₫", text, "1Nguyen Nhat Anh"));


        listCategory.add(new Category_Books("Kỹ Năng Mềm", listBook));
        listCategory.add(new Category_Books("Tâm Lý Học", listBook));
        listCategory.add(new Category_Books("Kinh Tế - Tài Chính", listBook));
        listCategory.add(new Category_Books("Văn Học Nước Ngoài", listBook));



        return listCategory;
    }
    private void ActionviewFlipper(Context mcoContext) {
        ArrayList<String> mangquangcao =new ArrayList<>();
        mangquangcao.add("https://toplist.vn/images/800px/tippi-hoang-da-37140.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/tippi-hoang-da-alain-degre-sylvie-robert-503864.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/chuyen-con-meo-day-hai-au-bay-37178.jpg");
        mangquangcao.add("https://toplist.vn/images/800px/tippi-hoang-da-37140.jpg");

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
