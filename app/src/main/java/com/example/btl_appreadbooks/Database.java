package com.example.btl_appreadbooks;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public void QueryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }
    public Cursor GetData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    public void Insert_TheLoai(String tentheloai, byte[] hinhanh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO TheLoai VALUES(null, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, tentheloai);
        sqLiteStatement.bindBlob(2, hinhanh);

        sqLiteStatement.executeInsert();
    }
    public void Update_TheLoai(int id, String tentheloai, byte[] hinhanh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE TheLoai set TenLoai = ?, HinhAnh_TL = ? WHERE PK_MaTL="+id;
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, tentheloai);
        sqLiteStatement.bindBlob(2, hinhanh);

        sqLiteStatement.executeInsert();
    }
    public void Delete_TheLoai(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM TheLoai WHERE PK_MaTL="+id;
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.executeInsert();
    }

    public void Insert_Sach(String tensach, String gioithieu, String tacgia, int MaTL, byte[] hinhanh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO Sach VALUES(null, ?, ?, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, tensach);
        sqLiteStatement.bindString(2, gioithieu);
        sqLiteStatement.bindString(3, tacgia);
        sqLiteStatement.bindLong(4, MaTL);
        sqLiteStatement.bindBlob(5, hinhanh);

        sqLiteStatement.executeInsert();
    }
    public void Update_Sach(int id, String tensach, String gioithieu, String tacgia, int matl, byte[] hinhanh){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE Sach set TenSach = ?, GioiThieu = ?, TacGia = ?, FK_MaTL = ?, HinhAnh_S = ? WHERE PK_MaSach="+id;
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, tensach);
        sqLiteStatement.bindString(2, gioithieu);
        sqLiteStatement.bindString(3, tacgia);
        sqLiteStatement.bindLong(4, matl);
        sqLiteStatement.bindBlob(5, hinhanh);

        sqLiteStatement.executeInsert();
    }
    public void Delete_Sach(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM Sach WHERE PK_MaSach="+id;
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.executeInsert();
    }
    public void Insert_TaiKhoan(String tentk, String pass, int quyen){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO TaiKhoan VALUES(null, ?, ?, ?)";
        SQLiteStatement sqLiteStatement = database.compileStatement(sql);
        sqLiteStatement.clearBindings();

        sqLiteStatement.bindString(1, tentk);
        sqLiteStatement.bindString(2, pass);
        sqLiteStatement.bindLong(3, quyen);

        sqLiteStatement.executeInsert();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
