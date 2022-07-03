package com.example.btl_appreadbooks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoai;
import com.example.btl_appreadbooks.Selected_TheLoai.Select_TheLoaiAdapter;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {
    EditText et_tensach, et_tacgia, et_giothieu, et_chitiet;
    Button btn_chonanh, btn_add, btn_chupanh, btn_chonpdf;
    ImageView iv_review, btn_close;
    int matl;

    private Spinner spinner_theloai;
    private Select_TheLoaiAdapter select_theLoaiAdapter;

    int REQUEST_CODE_CAMERA = 123;
    int REQUEST_CODE_FODER = 465;
    int CHECK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedpreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        if(sharedpreferences.getInt("quyen", 0)!=1){
            finish();
        }

        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_add_book);

        et_tensach = findViewById(R.id.et_tensach);
        et_tacgia = findViewById(R.id.et_tacgia);
        et_giothieu = findViewById(R.id.et_gioithieu);
        et_chitiet = findViewById(R.id.et_chitiet);
        btn_add = findViewById(R.id.btn_add);
        btn_chonanh = findViewById(R.id.btn_chonanh);
        btn_chupanh = findViewById(R.id.btn_chupanh);
        btn_close = findViewById(R.id.btn_close);
        iv_review = findViewById(R.id.iv_review);
//        btn_chonpdf = findViewById(R.id.btn_chonpdf);
        spinner_theloai = findViewById(R.id.spinner_theloai);
        select_theLoaiAdapter = new Select_TheLoaiAdapter(this, R.layout.item_selected_theloai, getListTheLoai());
        spinner_theloai.setAdapter(select_theLoaiAdapter);
        spinner_theloai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(AddBookActivity.this, ""+select_theLoaiAdapter.getItem(i).getId(), Toast.LENGTH_SHORT).show();
                matl = select_theLoaiAdapter.getItem(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Bundle bundle = getIntent().getExtras();
        try {
            if(bundle.getInt("id")>0)
            {
                Cursor cursor = MainActivity.database.GetData("SELECT * FROM Sach WHERE PK_MaSach = "+bundle.getInt("id"));
                while (cursor.moveToNext()){
                    et_tensach.setText(cursor.getString(1));
                    et_giothieu.setText(cursor.getString(2));
                    et_tacgia.setText(cursor.getString(3));
                    //chuyen byte[] -> bitmap
                    byte[] hinhanh = cursor.getBlob(5);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                    iv_review.setImageBitmap(bitmap);

                    String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/"+cursor.getString(1)+".pdf";
                    File file = new File(stringFilePath);

                    try{
                        PdfReader pdfReader = new PdfReader(file.getPath());
                        String stringParse = PdfTextExtractor.getTextFromPage(pdfReader, 1).trim();
                        pdfReader.close();
                        et_chitiet.setText(stringParse);
                    }catch (Exception e){
                        e.printStackTrace();
                        et_chitiet.setText("Error in Reading");
                    }
//                    spinner_theloai.set
                }
                btn_add.setText("Cập nhật");
                CHECK = 1;

            }
        }catch (Exception e){

        }

        btn_chupanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
            }
        });
        btn_chonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FODER);
            }
        });
//        btn_chonpdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(Intent.ACTION_PICK);
////                intent.setType("application/pdf");
////                startActivityForResult(intent, REQUEST_CODE_FODER);
//            }
//        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddBookActivity.this, QuanLySachActivity.class);
                startActivity(intent);
                finish();
            }
        });




        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_tensach.getText().toString().matches("")){
                    Toast.makeText(AddBookActivity.this, "Không đc để tên sách trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_tacgia.getText().toString().matches("")){
                    Toast.makeText(AddBookActivity.this, "Không đc để tên tác giả trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_giothieu.getText().toString().matches("")){
                    Toast.makeText(AddBookActivity.this, "Không đc để phần giới thiệu trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (et_chitiet.getText().toString().matches("")){
                    Toast.makeText(AddBookActivity.this, "Không đc để phần nội dung trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(iv_review.getDrawable()==null){
                    Toast.makeText(AddBookActivity.this, "Không đc để ảnh trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                //chuyển data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable)iv_review.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhanh = byteArray.toByteArray();

                if(CHECK==1){
//                    MainActivity.database.Update_Sach(
//                            bundle.getInt("id"),
//                            et_tensach.getText().toString().trim(),
//                            et_giothieu.getText().toString().trim(),
//                            et_tacgia.getText().toString().trim(),
//                            matl,
//                            hinhanh
//                    );
                }else{
                    MainActivity.database.Insert_Sach(
                            et_tensach.getText().toString().trim(),
                            et_giothieu.getText().toString().trim(),
                            et_tacgia.getText().toString().trim(),
                            matl,
                            hinhanh
                    );
                }

                String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/"+et_tensach.getText().toString().trim()+".pdf";
                File file = new File(stringFilePath);

                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                Paint paint = new Paint();
                String stringPdf = et_chitiet.getText().toString();

                int x = 10, y = 25;

                for (String line:stringPdf.split("\n")){
                    page.getCanvas().drawText(line, x, y, paint);

                    y += paint.descent() - paint.ascent();
                }
                pdfDocument.finishPage(page);

                try{
                    pdfDocument.writeTo(new FileOutputStream(file));
                }catch (Exception e) {
                    Toast.makeText(AddBookActivity.this, "loi", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                pdfDocument.close();
                Toast.makeText(AddBookActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddBookActivity.this, QuanLySachActivity.class);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            iv_review.setImageBitmap(bitmap);
        }
        if(requestCode == REQUEST_CODE_FODER && resultCode == RESULT_OK && data!=null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                iv_review.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private List<Select_TheLoai> getListTheLoai() {
        List<Select_TheLoai> list = new ArrayList<>();
        Cursor cursor = MainActivity.database.GetData("SELECT PK_MaTL, TenLoai FROM TheLoai");
        while (cursor.moveToNext()){
            list.add(new Select_TheLoai(cursor.getInt(0), cursor.getString(1)));
        }
        return list;
    }
}