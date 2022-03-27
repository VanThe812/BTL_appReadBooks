package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;

public class DocSachActivity extends AppCompatActivity {
    TextView tv_view;
    private ImageButton btn_chinhsua;
    private String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/sach_3.pdf";
    private File file = new File(stringFilePath);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_sach);

        tv_view = findViewById(R.id.tv_view);
        btn_chinhsua = findViewById(R.id.btn_chinhsua);
        btn_chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog_docsach();
            }
        });
        try{
            PdfReader pdfReader = new PdfReader(file.getPath());
            String stringParse = PdfTextExtractor.getTextFromPage(pdfReader, 1).trim();
            pdfReader.close();
            tv_view.setText(stringParse);
        }catch (Exception e){
            e.printStackTrace();
            tv_view.setText("Error in Reading");
        }
    }
    public void openDialog_docsach(){
        DocSachDialog docSachDialog = new DocSachDialog();
        docSachDialog.show(getSupportFragmentManager(),  "Đọc sách Dialog");

    }
}