package com.example.newsnow.db;

import android.provider.BaseColumns;

public final class NewsContract {
    private NewsContract() {}

    public static class NewsEntry implements BaseColumns {
        public static final String TABLE_NAME = "news_headlines";
        public static final String COLUMN_NAME_SOURCE = "source";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_URL_TO_IMAGE = "urlToImage";
        public static final String COLUMN_NAME_PUBLISHED_AT = "publishedAt";
        public static final String COLUMN_NAME_CONTENT = "content";
    }
}

