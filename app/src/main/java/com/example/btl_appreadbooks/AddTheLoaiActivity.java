package com.example.btl_appreadbooks;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btl_appreadbooks.QuanLy.QuanLy;
import com.itextpdf.text.pdf.qrcode.ByteArray;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddTheLoaiActivity extends AppCompatActivity {
    Button  btn_chonanh, btn_add, btn_chupanh;
    ImageView iv_review, btn_close;
    EditText et_tentheloai;

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
        setContentView(R.layout.activity_add_the_loai);
        anhxa();

        Bundle bundle = getIntent().getExtras();
        try {
            if(bundle.getInt("id")>0)
            {
                Cursor cursor = MainActivity.database.GetData("SELECT * FROM TheLoai WHERE PK_MaTL = "+bundle.getInt("id"));
                while (cursor.moveToNext()){
                    et_tentheloai.setText(cursor.getString(1));
                    //chuyen byte[] -> bitmap
                    byte[] hinhanh = cursor.getBlob(2);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
                    iv_review.setImageBitmap(bitmap);
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
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_tentheloai.getText().toString().matches("")){
                    Toast.makeText(AddTheLoaiActivity.this, "Không đc để tên trống", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(iv_review.getDrawable()==null){
                    Toast.makeText(AddTheLoaiActivity.this, "Không đc để ảnh trống", Toast.LENGTH_SHORT).show();
                    return;
                }

                //chuyển data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable)iv_review.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhanh = byteArray.toByteArray();

                if(CHECK==1){
                    MainActivity.database.Update_TheLoai(
                            bundle.getInt("id"),
                            et_tentheloai.getText().toString().trim(),
                            hinhanh
                    );
                }else{
                    MainActivity.database.Insert_TheLoai(
                            et_tentheloai.getText().toString().trim(),
                            hinhanh
                    );
                }

                Toast.makeText(AddTheLoaiActivity.this, "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddTheLoaiActivity.this, QuanLyTheLoaiActivity.class));
                finish();
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

    private void anhxa(){
        btn_close = findViewById(R.id.btn_close);
        btn_add = findViewById(R.id.btn_add);
        btn_chonanh = findViewById(R.id.btn_chonanh);
        btn_chupanh = findViewById(R.id.btn_chupanh);
        iv_review = findViewById(R.id.iv_review);
        et_tentheloai = findViewById(R.id.et_tentheloai);
    }
}