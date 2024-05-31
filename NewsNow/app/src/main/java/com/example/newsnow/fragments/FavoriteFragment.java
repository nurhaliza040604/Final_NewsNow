package com.example.newsnow.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsnow.DetailsActivity;
import com.example.newsnow.R;
import com.example.newsnow.adapters.FavoriteAdapter;
import com.example.newsnow.db.NewsRepository;
import com.example.newsnow.listeners.OnFetchDataListener;
import com.example.newsnow.listeners.RequestManager;
import com.example.newsnow.listeners.SelectListener;
import com.example.newsnow.model.NewsApiResponse;
import com.example.newsnow.model.NewsHeadLines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class FavoriteFragment extends Fragment implements SelectListener, View.OnClickListener {
    View view;
    RecyclerView recyclerView;
    FavoriteAdapter adapter;
    ProgressDialog dialog;
    FloatingActionButton fabDeleteFav;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favorite, container, false);

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Fetching news articles");
        dialog.show();

        NewsRepository newsRepository = new NewsRepository(getContext());

        fabDeleteFav = view.findViewById(R.id.delete_all_fav);
        fabDeleteFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewsRepository newsRepository = new NewsRepository(getContext());
                newsRepository.deleteAll();
                Toast.makeText(getContext(), "Favorites cleared", Toast.LENGTH_SHORT).show();
                List<NewsHeadLines> list = newsRepository.getAllNewsHeadlines();
                showNews(list);
                dialog.dismiss();
            }
        });

        List<NewsHeadLines> list = newsRepository.getAllNewsHeadlines();
        if (list.isEmpty()) {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        } else {
            showNews(list);
            dialog.dismiss();
        }
        return view;
    }

    private void showNews(List<NewsHeadLines> list) {
        recyclerView = view.findViewById(R.id.recycler_favorites);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new FavoriteAdapter(getContext(), list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadLines headLines) {
        startActivity(new Intent(getContext(), DetailsActivity.class)
                .putExtra("data", headLines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        dialog.setTitle("Fetching news articles of " + category);
        dialog.show();
        NewsRepository newsRepository = new NewsRepository(getContext());
        List<NewsHeadLines> list = newsRepository.getAllNewsHeadlines();
        if (list.isEmpty()) {
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
        } else {
            showNews(list);
            dialog.dismiss();
        }
    }
}