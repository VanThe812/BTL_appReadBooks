package com.example.btl_appreadbooks.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.btl_appreadbooks.Category_Books.Category_Books;
import com.example.btl_appreadbooks.Category_Books.Category_BooksTheLoai;
import com.example.btl_appreadbooks.Category_Books.Category_BooksTheLoaiAdapter;
import com.example.btl_appreadbooks.ChiTietTheLoaiActivity;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;
import java.util.List;

public class PhoBienFragment extends Fragment {

    private RecyclerView rcv_category_books;
    private Category_BooksTheLoaiAdapter category_booksTheLoaiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phobien, container, false);

        rcv_category_books = view.findViewById(R.id.rcv_chitiet_theoai);
        category_booksTheLoaiAdapter = new Category_BooksTheLoaiAdapter(inflater.getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(inflater.getContext(), RecyclerView.VERTICAL, false);
        rcv_category_books.setLayoutManager(linearLayoutManager);

        //thêm đg biên cho các rcv
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(inflater.getContext(), DividerItemDecoration.VERTICAL);
        rcv_category_books.addItemDecoration(itemDecoration);

        category_booksTheLoaiAdapter.setData(getListCategoryBooks());
        rcv_category_books.setAdapter(category_booksTheLoaiAdapter);

        return view;
    }
    private List<Category_BooksTheLoai> getListCategoryBooks() {
        List<Category_BooksTheLoai> listCategory = new ArrayList<>();

        List<Books> listBook = new ArrayList<>();
        Cursor cursor2 = MainActivity.database.GetData("SELECT PK_MaSach, TenSach, HinhAnh_S FROM Sach ");
        int dem = 1;
        while (cursor2.moveToNext()){
            listBook.add(new Books(cursor2.getInt(0), cursor2.getString(1), cursor2.getBlob(2)));
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
