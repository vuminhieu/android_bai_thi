package com.example.de_thi_on_tap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.de_thi_on_tap.model.Taxi_MaDe;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME = "_NgaySinh";
    private static final String TABLE_NAME = "Taxi_MaDe";
    private static final String ID = "Ma";
    private static final String SOXE = "SoXe";
    private static final String QUANGDUONG = "QuangDuong";
    private static final String DONGIA = "DonGia";
    private static final String KHUYENMAI = "KhuyenMai";
    private static int VERSION = 1;

    private String SQLQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            SOXE + " TEXT, " +
            QUANGDUONG + " DOUBLE, " +
            DONGIA + " DOUBLE, " +
            KHUYENMAI + " INT)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    // get tat ca cac gia tri cua taxi ra listview
    public List<Taxi_MaDe> getAllTaxi() {
        List<Taxi_MaDe> taxi_maDeList = new ArrayList<>();
        String AddingTaxi = "INSERT INTO Taxi_MaDe VALUES (null, '" + "17ABC XYZ" + "', '" + 200 + "', '" + 300000 + "', '" + 2 + "')";

        String selectQuery = "SELECT * FROM " + TABLE_NAME + " ORDER BY " + SOXE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Taxi_MaDe taxi_maDe = new Taxi_MaDe();
                taxi_maDe.setMa(cursor.getInt(0));
                taxi_maDe.setSoXe(cursor.getString(1));
                taxi_maDe.setQuangDuong(cursor.getDouble(2));
                taxi_maDe.setDonGia(cursor.getDouble(3));
                taxi_maDe.setPhanTramKhuyenMai(cursor.getInt(4));
                taxi_maDeList.add(taxi_maDe);
            }
            while (cursor.moveToNext());
        }
        db.close();
        return taxi_maDeList;
    }

    public int updateTaxi(Taxi_MaDe taxi_maDe) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SOXE, taxi_maDe.getSoXe());
        contentValues.put(QUANGDUONG, taxi_maDe.getQuangDuong());
        contentValues.put(DONGIA, taxi_maDe.getDonGia());
        contentValues.put(KHUYENMAI, taxi_maDe.getPhanTramKhuyenMai());
        String where = ID + " = " + taxi_maDe.getMa();
        return db.update(TABLE_NAME, contentValues, where, null);
    }

    public int Delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = ID + " = " + id;
        return db.delete(TABLE_NAME, where, null);
    }



}
