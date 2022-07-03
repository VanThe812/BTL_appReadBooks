package com.example.btl_appreadbooks.fragmentMenu;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.Category_TheLoai.Category_Theloai;
import com.example.btl_appreadbooks.Category_TheLoai.Category_TheloaiAdapter;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoai;
import com.example.btl_appreadbooks.TheLoai.TheLoai;
import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;
import java.util.List;

public class MenuTheLoaiFragment extends Fragment {
    private RecyclerView rcv_category_theloai;
    private Category_TheloaiAdapter categoryTheloaiAdapter;

//    private ImageButton btn_close;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment_theloai, container, false);

//        btn_close = view.findViewById(R.id.btn_close);
//        btn_close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
        androidx.appcompat.app.ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();

//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.title_layout_theloai);

        rcv_category_theloai = view.findViewById(R.id.rcv_category_theloai);
        categoryTheloaiAdapter = new Category_TheloaiAdapter(inflater.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        rcv_category_theloai.setLayoutManager(linearLayoutManager);

        categoryTheloaiAdapter.setData(getListCategoryTheLoai());
        rcv_category_theloai.setAdapter(categoryTheloaiAdapter);

        return view;
    }

    private List<Category_Theloai> getListCategoryTheLoai() {
        List<Category_Theloai> listCategoryTheloai = new ArrayList<>();
        String text = "Park Seo Joon đã xác nhận tham gia dự án bom tấn nằm trong vũ trụ điện ảnh Marvel (MCU) là Captain Marvel 2: The Marvels Trong một cuộc phỏng vấn với The Guardian, Park Seo Joon đã nói về cảm nghĩ của bản thân khi được tham gia MCU: Khi lần đầu tiên tôi nghe được thông tin MCU muốn tôi gia nhập vũ trụ của họ, tôi không thể tin đó là thật. Thật sự là tôi cảm thấy khó tin. Tôi cố gắng thật chú ý với những câu hỏi liên quan đến Marvel Park Seo Joon cũng tỏ ra kín tiếng và cẩn thận với những câu hỏi liên quan đến Marvel. “Đây không phải điều gì ngạc nhiên, bởi các diễn viên của Marvel đều phải giữ bí mật tuyệt đối về tình tiết phim mới";

        List<TheLoai> listTheLoai = new ArrayList<>();
        Cursor cursor = MainActivity.database.GetData("SELECT * FROM TheLoai");
        int i = 0;
        while (cursor.moveToNext()){
            listTheLoai.add(new TheLoai(cursor.getInt(0), cursor.getString(1), cursor.getBlob(2)));
            if(i==2||i==5||i==8||i==11 ){
                listCategoryTheloai.add(new Category_Theloai(listTheLoai));
                listTheLoai = new ArrayList<>();
            }
            i++;
        }




        return listCategoryTheloai;
    }
}
