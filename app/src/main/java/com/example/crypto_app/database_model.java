package com.example.crypto_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database_model extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public database_model(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("CREATE TABLE allusers(" +
                "firstname TEXT, " +
                "lastname TEXT, " +
                "phonenumber TEXT, " +
                "accountbalance REAL, " +
                "age INTEGER, " +
                "email TEXT PRIMARY KEY, " +
                "password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS allusers");
        onCreate(db);
    }

    public boolean insertData(String firstname, String lastname, String phonenumber, int age, String email, double accountbalance, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("firstname", firstname);
        cv.put("lastname", lastname);
        cv.put("phonenumber", phonenumber);
        cv.put("age", age);
        cv.put("email", email);
        cv.put("accountbalance", accountbalance);
        cv.put("password", password);

        long result = MyDatabase.insert("allusers", null, cv);

        return result != -1;
    }

    public boolean checkEmail(String email) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }
}
