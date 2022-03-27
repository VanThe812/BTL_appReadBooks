package com.example.btl_appreadbooks.fragmentMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksAdapter;
import com.example.btl_appreadbooks.Category_TheLoai.Category_Theloai;
import com.example.btl_appreadbooks.Category_TheLoai.Category_TheloaiAdapter;
import com.example.btl_appreadbooks.R;
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

//        for (Books books : lsBooks) {
//            listBook.add(new Books(books.getResourceId(), books.getMasach(), books.getTitle(), books.getChitiet(), books.getTacgia()));
//        }
        for (int i = 0; i<12; i++){

            listTheLoai.add(new TheLoai(i, "TÌNH CẢM LÃNG MẠN", R.drawable.tocoratnhieufan));
            if(i==2||i==5||i==8||i==11 ){
                listCategoryTheloai.add(new Category_Theloai(listTheLoai));
                listTheLoai = new ArrayList<>();
            }
        }



        return listCategoryTheloai;
    }
}
