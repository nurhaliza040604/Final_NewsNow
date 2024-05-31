package com.example.newsnow.listeners;

import com.example.newsnow.model.NewsHeadLines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadLines> list, String message);

    void onError(String message);
}
