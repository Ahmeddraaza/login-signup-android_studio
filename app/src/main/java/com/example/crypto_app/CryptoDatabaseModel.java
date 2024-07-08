package com.example.crypto_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CryptoDatabaseModel extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CryptoMarket.db";

    public CryptoDatabaseModel(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cryptomarket(" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "symbol TEXT, " +
                "price REAL, " +
                "percentChange24h REAL, " +
                "turnover REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cryptomarket");
        onCreate(db);
    }

    public boolean insertCryptoData(int id, String name, String symbol, double price, double percentChange24h, double turnover) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("id", id);
        cv.put("name", name);
        cv.put("symbol", symbol);
        cv.put("price", price);
        cv.put("percentChange24h", percentChange24h);
        cv.put("turnover", turnover);

        long result = db.insert("cryptomarket", null, cv);
        return result != -1;
    }

    public ArrayList<CryptoModel> getAllCryptoData() {
        ArrayList<CryptoModel> cryptoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cryptomarket", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String symbol = cursor.getString(2);
                double price = cursor.getDouble(3);
                double percentChange24h = cursor.getDouble(4);
                double turnover = cursor.getDouble(5);

                CryptoModel crypto = new CryptoModel(id, name, symbol, price, percentChange24h, turnover);
                cryptoList.add(crypto);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return cryptoList;
    }

    public boolean checkIfDataExists(int id)
    {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM cryptomarket WHERE id = ?", new String[]{String.valueOf(id)});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
