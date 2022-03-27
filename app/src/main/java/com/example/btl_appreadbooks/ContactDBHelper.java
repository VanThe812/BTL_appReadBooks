package com.example.btl_appreadbooks;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.btl_appreadbooks.books.Books;

import java.util.ArrayList;

public class ContactDBHelper extends SQLiteOpenHelper {
    public static final String DB_Name = "books.db";
    public static final int DB_VERSION = 1;
    public static final String TB_NAME_BOOK = "tbl_books";
    public static final String ID_BOOK = "id_book";
    public static final String ID_TAINGUYEN = "id_tainguyen";
    public static final String TIEUDE_BOOK = "tieude_book";
    public static final String TOMTAT_BOOK = "tomtat_book";
    public static final String TACGIA = "tacgia";
    public static final String FK_MATHELOAI = "fk_matheloai";

    public static final String TB_NAME_THELOAI = "tbl_theloai";
    public static final String ID_THELOAI = "id_theloai";
    public static final String TENTHELOAI = "tentheloai";
    public static final String ID_TAINGUYEN_TL = "id_tainguyentl";

    public Context context;

    public ContactDBHelper(@Nullable Context context) {
        super(context, DB_Name, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="Create table " + TB_NAME_BOOK + "( " + ID_BOOK +" integer primary key, " + ID_TAINGUYEN +" integer " +
                ", " + TIEUDE_BOOK +" text, " + TOMTAT_BOOK +" text, " + TACGIA +" TEXT ); ";
        String sql2 = "Create table " + TB_NAME_THELOAI + "( " + ID_THELOAI +" integer primary key, " + TENTHELOAI +" text " +
                ", " + ID_TAINGUYEN_TL +" integer ); ";
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sql2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP table if exists " + TB_NAME_BOOK ;
        sqLiteDatabase.execSQL(sql);
        String sql2 = "DROP table if exists " + TB_NAME_THELOAI ;
        sqLiteDatabase.execSQL(sql2);
        onCreate(sqLiteDatabase);
    }
    public void insContact(Books contact){

        ContentValues contentValues = new ContentValues();
        //put du lieu can insert vao doi tuong contentValues
        contentValues.put(ID_BOOK, contact.getMasach());
        contentValues.put(ID_TAINGUYEN, contact.getResourceId());
        contentValues.put(TIEUDE_BOOK, contact.getTitle());
        contentValues.put(TOMTAT_BOOK, contact.getChitiet());
        contentValues.put(TACGIA, contact.getTacgia());
        //lay ra sqlitedatabase de thuc ghi du lieu
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TB_NAME_BOOK,null,contentValues);
        if(result!=-1)
            Toast.makeText(context, "Insert success!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Insert fail !", Toast.LENGTH_LONG).show();
    }
    public ArrayList<Books> getAllBooks(){
        ArrayList<Books> result = new ArrayList<>();
        //lay ra sqliteDatabase thuc hien doc du lieu
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TB_NAME_BOOK+" ;", null);
        while (cursor.moveToNext()){
            Books books = new Books();
            books.setMasach(cursor.getInt(0));
            books.setResourceId(cursor.getInt(1));
            books.setTitle(cursor.getString(2));
            books.setChitiet(cursor.getString(3));
            books.setTacgia(cursor.getString(4));
            result.add(books);
        }
        return result;
    }
//    public void updContact(GhichuContact contact, String name){
//        ContentValues contentUpdate = new ContentValues();
//        //put du lieu can cap nhat
//        contentUpdate.put(TIEUDE, contact.getTieude());
//        contentUpdate.put(CHITIET, contact.getChitiet());
//        contentUpdate.put(THOIGIAN, contact.getThoigian());
//        //lay ra sqlite_database de ghi du lieu vao
//        SQLiteDatabase db = getWritableDatabase();
//        int resutl = db.update(TB_NAME,contentUpdate,
//                TIEUDE +" like '%" + name +"%'",null );
//        if(resutl>0){
//            Toast.makeText(context, "Update success!", Toast.LENGTH_LONG).show();
//        }
//        else
//            Toast.makeText(context, "Update Fail!", Toast.LENGTH_LONG).show();
//
//
//    }
//
//
//    public void delContact(String name){
//        SQLiteDatabase db = getWritableDatabase();
//        int res = db.delete(TB_NAME, TIEUDE + " = ?" , new String[]{name});
//        if(res > 0){
//            Toast.makeText(context, "Delete success!", Toast.LENGTH_LONG).show();
//        }
//        else
//            Toast.makeText(context, "Delete Fail!", Toast.LENGTH_LONG).show();
//    }

    public void insTheloai(Books contact){

        ContentValues contentValues = new ContentValues();
        //put du lieu can insert vao doi tuong contentValues
        contentValues.put(ID_BOOK, contact.getMasach());
        contentValues.put(ID_TAINGUYEN, contact.getResourceId());
        contentValues.put(TIEUDE_BOOK, contact.getTitle());
        contentValues.put(TOMTAT_BOOK, contact.getChitiet());
        contentValues.put(TACGIA, contact.getTacgia());
        //lay ra sqlitedatabase de thuc ghi du lieu
        SQLiteDatabase db = getWritableDatabase();
        long result = db.insert(TB_NAME_BOOK,null,contentValues);
        if(result!=-1)
            Toast.makeText(context, "Insert success!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Insert fail !", Toast.LENGTH_LONG).show();
    }
}
