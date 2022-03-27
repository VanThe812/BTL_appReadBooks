package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.btl_appreadbooks.books.Books;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class AddBookActivity extends AppCompatActivity {
    EditText et_tensach, et_tacgia, et_giothieu, et_chitiet;
    Button btn_add;
    ContactDBHelper contactDBHelper;

    private String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/ProgramerWorld.pdf";
    private File file = new File(stringFilePath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        et_tensach = findViewById(R.id.et_tensach);
        et_tacgia = findViewById(R.id.et_tacgia);
        et_giothieu = findViewById(R.id.et_gioithieu);
        et_chitiet = findViewById(R.id.et_chitiet);
        btn_add = findViewById(R.id.btn_add);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Random rd=new Random();
//                int x = rd.nextInt((99999-10000+1)+10000);
//                contactDBHelper = new ContactDBHelper(AddBookActivity.this);
//
//                contactDBHelper.insContact(new Books(R.drawable.img, x, et_tensach.getText().toString(), et_giothieu.getText().toString(), et_tacgia.getText().toString()));
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
                    e.printStackTrace();
                }

                pdfDocument.close();

            }
        });

    }
}