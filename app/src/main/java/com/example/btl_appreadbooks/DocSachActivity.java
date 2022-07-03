package com.example.btl_appreadbooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import java.io.File;

public class DocSachActivity extends AppCompatActivity {
    private TextView tv_view, tv_tensach;
    private ImageButton btn_chinhsua, btn_close;
    private LinearLayout layout_docsach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_doc_sach);

        tv_view = findViewById(R.id.tv_view);
        tv_tensach = findViewById(R.id.tv_huy);
        btn_chinhsua = findViewById(R.id.btn_chinhsua);
        btn_close = findViewById(R.id.btn_close);
        layout_docsach = findViewById(R.id.layout_docsach);
        btn_chinhsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                openDialog_docsach();
                openFeedbackDialog(Gravity.BOTTOM);
            }
        });
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle bundle = getIntent().getExtras();

        if(bundle == null)
            return;
        String stringFilePath = Environment.getExternalStorageDirectory().getPath() + "/Download/"+bundle.getString("tensach")+".pdf";
        File file = new File(stringFilePath);
        tv_tensach.setText(bundle.getString("tensach"));
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

    private void openFeedbackDialog(int gavity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_docsach);
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
        ImageButton btn_day = dialog.findViewById(R.id.btn_day);
        ImageButton btn_night = dialog.findViewById(R.id.btn_night);
        LinearLayout layout_dialog_docsach = dialog.findViewById(R.id.layout_dialog_docsach);

        TextView tv_serif = dialog.findViewById(R.id.tv_serif);
        TextView tv_serif_thin = dialog.findViewById(R.id.tv_serif_thin);
        TextView serif_light = dialog.findViewById(R.id.serif_light);
        TextView serif_condensed = dialog.findViewById(R.id.serif_condensed);
        TextView tv_giam = dialog.findViewById(R.id.tv_giam);
        TextView tv_tang = dialog.findViewById(R.id.tv_tang);
        SeekBar sb_cochu  = dialog.findViewById(R.id.sb_cochu);



        btn_night.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DocSachActivity.this, "Thay đổi", Toast.LENGTH_SHORT).show();
                layout_docsach.setBackgroundColor(Color.BLACK);
                tv_view.setTextColor(Color.WHITE);
                tv_tensach.setTextColor(Color.WHITE);
                btn_close.setBackgroundColor(Color.BLACK);
                btn_close.setImageResource(R.drawable.baseline_keyboard_arrow_left_white_36);
                btn_chinhsua.setBackgroundColor(Color.BLACK);
                btn_chinhsua.setImageResource(R.drawable.baseline_edit_note_white_18);

//                btn_day.setImageResource(R.drawable.baseline_light_mode_white_18);
//                btn_night.setImageResource(R.drawable.sharp_nightlight_round_white_18);
//                layout_dialog_docsach.setBackgroundColor(bg_black_corner);
            }
        });
        btn_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout_docsach.setBackgroundColor(Color.WHITE);
                tv_view.setTextColor(Color.BLACK);
                tv_tensach.setTextColor(Color.BLACK);
                btn_close.setBackgroundColor(Color.WHITE);
                btn_close.setImageResource(R.drawable.baseline_keyboard_arrow_left_black_36);
                btn_chinhsua.setBackgroundColor(Color.WHITE);
                btn_chinhsua.setImageResource(R.drawable.baseline_edit_note_black_18);

//                btn_day.setImageResource(R.drawable.baseline_light_mode_black_18);
//                btn_night.setImageResource(R.drawable.sharp_nightlight_round_black_18);
//                layout_dialog_docsach.setBackgroundColor(R.drawable.bg_white_corner);
            }
        });
        tv_serif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/roboto_medium.ttf");
                tv_view.setTypeface(face);
            }
        });
        tv_serif_thin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/roboto_black.ttf");
                tv_view.setTypeface(face);
            }
        });
        serif_light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/roboto_boldItalic.ttf");
                tv_view.setTypeface(face);
            }
        });
        serif_condensed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Typeface face = Typeface.createFromAsset(getAssets(),
                        "fonts/roboto_regular.ttf");
                tv_view.setTypeface(face);
            }
        });
        tv_tang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb_cochu.setProgress(sb_cochu.getProgress()+1);
                Toast.makeText(DocSachActivity.this, ""+sb_cochu.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
        tv_giam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sb_cochu.setProgress(sb_cochu.getProgress()-1);
                Toast.makeText(DocSachActivity.this, ""+sb_cochu.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
        sb_cochu.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(sb_cochu.getProgress()==1){
                    tv_view.setTextSize(15);
                }
                if(sb_cochu.getProgress()==2){
                    tv_view.setTextSize(20);
                }
                if(sb_cochu.getProgress()==3){
                    tv_view.setTextSize(25);
                }
                if(sb_cochu.getProgress()==4){
                    tv_view.setTextSize(30);
                }
                if(sb_cochu.getProgress()==5){
                    tv_view.setTextSize(35);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}