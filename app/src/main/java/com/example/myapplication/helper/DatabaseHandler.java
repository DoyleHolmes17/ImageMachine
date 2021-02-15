package com.example.myapplication.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.model.MachineDataDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "machinedb";


    private static final String TABLE_MACHINE = "machine_detail";


    private static final String KEY_ID = "id_machine";
    private static final String KEY_NAME = "name_machine";
    private static final String KEY_TYPE = "type_machine";
    private static final String KEY_QR = "qr_number";
    private static final String KEY_IMAGE = "uri_image";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_MACHINE + "("
                + KEY_ID + " TEXT UNIQUE," + KEY_NAME + " TEXT,"
                + KEY_TYPE + " TEXT," + KEY_QR + " INTEGER," + KEY_IMAGE + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MACHINE);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new Kasir
    void addMachine(MachineDataDetail machineDataDetail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, UUID.randomUUID().toString().replace("-", ""));
        values.put(KEY_NAME, machineDataDetail.getName());
        values.put(KEY_TYPE, machineDataDetail.getType());
        values.put(KEY_QR, machineDataDetail.getQrnumber());
//        values.put(KEY_IMAGE, machineDataDetail.get());
//        // Inserting Row
//        db.insert(TABLE_KASIR, null, values);
        db.close(); // Closing database connection
    }

    // Getting single kasir
//    MachineDataDetail getMachine(String id) {
//        Cursor cursor;
//        MachineDataDetail machineDataDetail;
//
//        SQLiteDatabase db = this.getReadableDatabase();

//        cursor = db.query(TABLE_MACHINE, new String[]{KEY_ID,
//                        KEY_NAME, KEY_PRICE}, KEY_ID + "=?",
//                new String[]{id}, null, null, null);
//        cursor.moveToFirst();
//
//        if (cursor != null && cursor.moveToFirst()) {
//            kasir = new Kasir(cursor.getString(0),
//                    cursor.getString(1), Integer.parseInt(cursor.getString(2)));
//            cursor.close();
//
//        } else {
//            kasir = new Kasir("barcode kosong", "tidak ditemukan", 0);
//            cursor.close();
//        }
//
//        // return kasir
//        return machineDataDetail;
//
//    }

//    // Getting All Kasirs
//    public List<Kasir> getAllKasirs() {
//        List<Kasir> kasirList = new ArrayList<Kasir>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_KASIR;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        // looping through all rows and adding to list
//        if (cursor.moveToFirst() && cursor.moveToFirst()) {
//            do {
//                Kasir kasir = new Kasir();
//                kasir.setIdBarcode(cursor.getString(0));
//                kasir.setNamaProduk(cursor.getString(1));
//                kasir.setHargaProduk(Integer.parseInt(cursor.getString(2)));
//                // Adding kasir to list
//                kasirList.add(kasir);
//            } while (cursor.moveToNext());
//            cursor.close();
//        } else {
//            Kasir kasir = new Kasir();
//            kasir.setIdBarcode("barcode kosong");
//            kasir.setNamaProduk("barang kosong");
//            kasir.setHargaProduk(0);
//            // Adding kasir to list
//            kasirList.add(kasir);
//        }
//
//        // return Kasir list
//        return kasirList;
//    }

//    // Getting All Carts
//    public List<Kasir> getBarcode() {
//        List<Kasir> kasirList = new ArrayList<Kasir>();
//        // Select All Query
//        String selectQuery = "SELECT idBarcode,namaProduk FROM " + TABLE_KASIR;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            do {
//                Kasir kasir = new Kasir();
//                kasir.setIdBarcode(cursor.getString(0));
//                // Adding kasir to list
//                kasirList.add(kasir);
//                kasir.setNamaProduk(cursor.getString(1));
//                kasirList.add(kasir);
//            } while (cursor.moveToNext());
//            cursor.close();
//        } else {
//            Kasir kasir = new Kasir();
//            kasir.setIdBarcode("barcode kosong");
//            kasir.setNamaProduk("barang kosong");
//            // Adding kasir to list
//            kasirList.add(kasir);
//        }
//
//
//        // return Cart list
//        return kasirList;
//    }

//    // Updating single Kasir
//    public int updateKasir(Kasir kasir) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(KEY_ID, kasir.getIdBarcode());
//        values.put(KEY_NAME, kasir.getNamaProduk());
//        values.put(KEY_PRICE, kasir.getHargaProduk());
//
//        // updating row
//        return db.update(TABLE_KASIR, values, KEY_ID + " = ?",
//                new String[]{String.valueOf(kasir.getIdBarcode())});
//    }

//    // Deleting single Kasir
//    public void deleteKasir(Kasir kasir) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_KASIR, KEY_ID + " = ?",
//                new String[]{String.valueOf(kasir.getIdBarcode())});
//        db.close();
//    }

//    // Getting Cart Count
//    public int getCartCount() {
//        String countQuery = "SELECT  * FROM " + TABLE_CART;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.rawQuery(countQuery, null);
//        cursor.close();
//
//        // return count
//        return cursor.getCount();
//    }
//
//    public void deleteAll() {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CART, null, null);
//        db.close();
//    }
//
//    public void tambahData() {
//
//        addKasir(new Kasir("1", "test", 1000));
//        addKasir(new Kasir("2", "test1", 1000));
//        addKasir(new Kasir("3", "test2", 1000));
//        addKasir(new Kasir("4", "test3", 1000));
//        addCart(new Cart("1", "test", 1000, 1));
//        addCart(new Cart("2", "test1", 2000, 3));
//        addCart(new Cart("3", "test2", 3000, 1));
//        addCart(new Cart("4", "test3", 4000, 2));
//        addCart(new Cart("5", "test4", 5000, 1));
//    }

}
