package com.example.btl_appreadbooks.fragmentMenu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.btl_appreadbooks.DangNhapActivity;
import com.example.btl_appreadbooks.DocSachActivity;
import com.example.btl_appreadbooks.MainActivity;
import com.example.btl_appreadbooks.QuanLySachActivity;
import com.example.btl_appreadbooks.QuanLyTheLoaiActivity;
import com.example.btl_appreadbooks.R;
import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MenuUserFragment extends Fragment {

    private FloatingActionButton btn_qlTheloai, btn_qlSach, btn_dangnhap;
    private TextView tv_qlTheLoai, tv_qlSach, tv_dangnhap;

    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Tentk = "tenTK";
    public static final String Pass = "pass";
    public static final String Quyen = "quyen";
    SharedPreferences sharedpreferences;
    private int status_login = 0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment_user, container, false);
        androidx.appcompat.app.ActionBar actionBar =  ((AppCompatActivity)getActivity()).getSupportActionBar();
//
//        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.action_bar_bg));
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.title_layout_caidat);

        btn_qlTheloai = view.findViewById(R.id.btn_qltheloai);
        btn_qlSach = view.findViewById(R.id.btn_qlsach);
        btn_dangnhap = view.findViewById(R.id.btn_danhnhap);
        tv_qlTheLoai = view.findViewById(R.id.tv_qltheloai);
        tv_qlSach = view.findViewById(R.id.tv_qlsach);
        tv_dangnhap = view.findViewById(R.id.tv_dangnhap);

        btn_qlSach.setVisibility(View.INVISIBLE);
        tv_qlSach.setVisibility(View.INVISIBLE);
        btn_qlTheloai.setVisibility(View.INVISIBLE);
        tv_qlTheLoai.setVisibility(View.INVISIBLE);


        SharedPreferences sharedpreferences = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        Toast.makeText(getContext(), ""+sharedpreferences.getString("tenTK", ""), Toast.LENGTH_SHORT).show();
        if(sharedpreferences.getString("tenTK", null)!=""){
            status_login = 1;
            tv_dangnhap.setText("Đăng xuất");
        }
        if(sharedpreferences.getInt("quyen", 0)!=1){
            btn_qlSach.setVisibility(View.INVISIBLE);
            tv_qlSach.setVisibility(View.INVISIBLE);
            btn_qlTheloai.setVisibility(View.INVISIBLE);
            tv_qlTheLoai.setVisibility(View.INVISIBLE);

        }else{
            btn_qlSach.setVisibility(View.VISIBLE);
            tv_qlSach.setVisibility(View.VISIBLE);
            btn_qlTheloai.setVisibility(View.VISIBLE);
            tv_qlTheLoai.setVisibility(View.VISIBLE);
        }


        btn_qlTheloai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLyTheLoaiActivity.class);
                startActivity(intent);
            }
        });
        tv_qlTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLyTheLoaiActivity.class);
                startActivity(intent);
            }
        });

        btn_qlSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLySachActivity.class);
                startActivity(intent);
            }
        });
        tv_qlSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), QuanLySachActivity.class);
                startActivity(intent);
            }
        });
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), DangNhapActivity.class);
//                startActivity(intent);
                openFeedbackDialog(Gravity.CENTER);
            }
        });
        tv_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(status_login==0){
                    openFeedbackDialog(Gravity.CENTER);
                }else{
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.clear();
                    editor.commit();
                    btn_qlSach.setVisibility(View.INVISIBLE);
                    tv_qlSach.setVisibility(View.INVISIBLE);
                    btn_qlTheloai.setVisibility(View.INVISIBLE);
                    tv_qlTheLoai.setVisibility(View.INVISIBLE);

                    tv_dangnhap.setText("Đăng nhập");
                    status_login=0;
                }

            }
        });

        return view;
    }
    private void openFeedbackDialog(int gavity){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_dangnhap);
        dialog.show();
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gavity;
        window.setAttributes(windowAttributes);

        if(Gravity.BOTTOM == gavity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        Button btn_no = dialog.findViewById(R.id.btn_no);
        Button btn_dangnhap = dialog.findViewById(R.id.btn_dangnhap);
        TextView tv_quenmk = dialog.findViewById(R.id.tv_quenmk);
        TextView tv_dangky = dialog.findViewById(R.id.tv_dangky);

        EditText et_taikhoan = dialog.findViewById(R.id.et_taikhoan);
        EditText et_pass = dialog.findViewById(R.id.et_matkhau);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Login", Toast.LENGTH_SHORT).show();
                if(et_taikhoan.getText().length()<=0){
                    Toast.makeText(getContext(), "Tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_pass.getText().length()<=0){
                    Toast.makeText(getContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor cursor = MainActivity.database.GetData("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = '"+et_taikhoan.getText().toString().trim()+"' AND MatKhau = '"+et_pass.getText().toString().trim()+"'");

                SharedPreferences.Editor editor = sharedpreferences.edit();
                boolean check = false;
                while (cursor.moveToNext()){
                    editor.putString(Tentk, cursor.getString(1));
                    editor.putString(Pass, cursor.getString(2));
                    editor.putInt(Quyen, cursor.getInt(3));
                    check = true;
                    if(cursor.getInt(3)!=1){
                        btn_qlSach.setVisibility(View.INVISIBLE);
                        tv_qlSach.setVisibility(View.INVISIBLE);
                        btn_qlTheloai.setVisibility(View.INVISIBLE);
                        tv_qlTheLoai.setVisibility(View.INVISIBLE);
                    }else{
                        btn_qlSach.setVisibility(View.VISIBLE);
                        tv_qlSach.setVisibility(View.VISIBLE);
                        btn_qlTheloai.setVisibility(View.VISIBLE);
                        tv_qlTheLoai.setVisibility(View.VISIBLE);
                    }
                    tv_dangnhap.setText("Đăng xuất");
                    status_login=1;
                }

                if(!check){
                    Toast.makeText(getContext(), "Tài khoản hoặc mật khẩu sai. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                editor.commit();
                dialog.dismiss();


            }
        });
        tv_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                openFeedbackDialog_dangky();
            }
        });
    }
    private void openFeedbackDialog_dangky(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_dangky);
        dialog.show();
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAttributes);

        EditText et_taikhoan = dialog.findViewById(R.id.et_taikhoan);
        EditText et_pass = dialog.findViewById(R.id.et_matkhau);
        EditText et_repass = dialog.findViewById(R.id.et_nhaplaimatkhau);
        Button btn_no = dialog.findViewById(R.id.btn_no);
        Button btn_dangky = dialog.findViewById(R.id.btn_dangky);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_taikhoan.getText().length()<=0){
                    Toast.makeText(getContext(), "Tài khoản không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_pass.getText().length()<=0){
                    Toast.makeText(getContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(et_repass.getText().length()<=0){
                    Toast.makeText(getContext(), "Mật khẩu không được để trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!et_repass.getText().toString().trim().equals(et_pass.getText().toString().trim())){
                    Toast.makeText(getContext(), "Nhạp lại mạt khẩu không giống", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor checkTrung = MainActivity.database.GetData("SELECT * FROM TaiKhoan WHERE TenTaiKhoan = '"+et_taikhoan.getText().toString().trim()+"'");
                while (checkTrung.moveToNext()){
                    Toast.makeText(getContext(), "Tên tài khoản đã tồn tại. Vui lòng thử lại", Toast.LENGTH_SHORT).show();
                    return;
                }
                MainActivity.database.Insert_TaiKhoan(
                        et_taikhoan.getText().toString().trim(),
                        et_pass.getText().toString().trim(),
                        0
                );
                Toast.makeText(getContext(), "Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                openFeedbackDialog(Gravity.CENTER);
            }
        });
    }
}
