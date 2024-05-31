package com.example.newsnow.db;

import com.example.newsnow.db.NewsContract;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class NewsDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "news.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + NewsContract.NewsEntry.TABLE_NAME + " (" +
                    NewsContract.NewsEntry._ID + " INTEGER PRIMARY KEY," +
                    NewsContract.NewsEntry.COLUMN_NAME_SOURCE + " TEXT," +  // Store serialized Source object as JSON
                    NewsContract.NewsEntry.COLUMN_NAME_AUTHOR + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_TITLE + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_URL + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT + " TEXT," +
                    NewsContract.NewsEntry.COLUMN_NAME_CONTENT + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + NewsContract.NewsEntry.TABLE_NAME;

    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
