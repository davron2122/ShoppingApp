package com.example.shoppingapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.shoppingapp.base.BaseActivity;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DB_NAME = "SHOPPING_APP";

    public static Integer DB_VERSION;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
