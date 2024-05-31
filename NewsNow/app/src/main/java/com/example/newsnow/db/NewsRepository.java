package com.example.newsnow.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.newsnow.model.NewsHeadLines;
import com.example.newsnow.model.Source;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class NewsRepository {
    private NewsDbHelper dbHelper;
    private Gson gson;

    public NewsRepository(Context context) {
        dbHelper = new NewsDbHelper(context);
        gson = new Gson();
    }

    public long insertNewsHeadline(NewsHeadLines newsHeadLines) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsContract.NewsEntry.COLUMN_NAME_SOURCE, gson.toJson(newsHeadLines.getSource()));  // Serialize Source object
        values.put(NewsContract.NewsEntry.COLUMN_NAME_AUTHOR, newsHeadLines.getAuthor());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_TITLE, newsHeadLines.getTitle());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_DESCRIPTION, newsHeadLines.getDescription());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_URL, newsHeadLines.getUrl());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE, newsHeadLines.getUrlToImage());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT, newsHeadLines.getPublishedAt());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_CONTENT, newsHeadLines.getContent());
        return db.insert(NewsContract.NewsEntry.TABLE_NAME, null, values);
    }

    public List<NewsHeadLines> getAllNewsHeadlines() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                NewsContract.NewsEntry._ID,
                NewsContract.NewsEntry.COLUMN_NAME_SOURCE,
                NewsContract.NewsEntry.COLUMN_NAME_AUTHOR,
                NewsContract.NewsEntry.COLUMN_NAME_TITLE,
                NewsContract.NewsEntry.COLUMN_NAME_DESCRIPTION,
                NewsContract.NewsEntry.COLUMN_NAME_URL,
                NewsContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE,
                NewsContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT,
                NewsContract.NewsEntry.COLUMN_NAME_CONTENT
        };

        Cursor cursor = db.query(
                NewsContract.NewsEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        List<NewsHeadLines> newsHeadLinesList = new ArrayList<>();
        while (cursor.moveToNext()) {
            NewsHeadLines newsHeadLines = new NewsHeadLines();
            newsHeadLines.setSource(gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_SOURCE)), Source.class)); // Deserialize Source object
            newsHeadLines.setAuthor(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_AUTHOR)));
            newsHeadLines.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_TITLE)));
            newsHeadLines.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_DESCRIPTION)));
            newsHeadLines.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_URL)));
            newsHeadLines.setUrlToImage(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE)));
            newsHeadLines.setPublishedAt(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT)));
            newsHeadLines.setContent(cursor.getString(cursor.getColumnIndexOrThrow(NewsContract.NewsEntry.COLUMN_NAME_CONTENT)));
            newsHeadLinesList.add(newsHeadLines);
        }
        cursor.close();
        return newsHeadLinesList;
    }

    public int updateNewsHeadline(NewsHeadLines newsHeadLines) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsContract.NewsEntry.COLUMN_NAME_SOURCE, gson.toJson(newsHeadLines.getSource()));  // Serialize Source object
        values.put(NewsContract.NewsEntry.COLUMN_NAME_AUTHOR, newsHeadLines.getAuthor());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_TITLE, newsHeadLines.getTitle());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_DESCRIPTION, newsHeadLines.getDescription());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_URL, newsHeadLines.getUrl());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_URL_TO_IMAGE, newsHeadLines.getUrlToImage());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_PUBLISHED_AT, newsHeadLines.getPublishedAt());
        values.put(NewsContract.NewsEntry.COLUMN_NAME_CONTENT, newsHeadLines.getContent());

        String selection = NewsContract.NewsEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(newsHeadLines.getTitle()) };

        return db.update(NewsContract.NewsEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public void deleteNewsHeadline(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = NewsContract.NewsEntry._ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        db.delete(NewsContract.NewsEntry.TABLE_NAME, selection, selectionArgs);
    }

    public void deleteAll() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(NewsContract.NewsEntry.TABLE_NAME, null, null);

    }
}
