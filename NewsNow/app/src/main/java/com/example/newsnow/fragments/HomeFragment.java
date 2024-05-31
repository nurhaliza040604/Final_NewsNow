package com.example.newsnow.fragments;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.newsnow.adapters.HomeAdapter;
import com.example.newsnow.DetailsActivity;
import com.example.newsnow.listeners.OnFetchDataListener;
import com.example.newsnow.R;
import com.example.newsnow.listeners.RequestManager;
import com.example.newsnow.listeners.SelectListener;
import com.example.newsnow.model.NewsApiResponse;
import com.example.newsnow.model.NewsHeadLines;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements SelectListener, View.OnClickListener {

    View view;
    RecyclerView recyclerView;
    HomeAdapter adapter;
    ProgressDialog dialog;
    Button b1, b2, b3, b4, b5, b6, b7;
    SearchView searchView;
     SwipeRefreshLayout swipeRefreshLayout;

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching news articles of " + query);
                dialog.show();
                RequestManager manager = new RequestManager(getContext());
                manager.getNewsHeadlines(listener, "general", query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Fetching news articles");
        dialog.show();

        b1 = view.findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = view.findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = view.findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = view.findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = view.findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = view.findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = view.findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dialog.setTitle("Fetching news articles");
                dialog.show();
                // Refresh the news headlines
                RequestManager manager = new RequestManager(getContext());
                manager.getNewsHeadlines(listener, "general", null);
            }
        });

        RequestManager manager = new RequestManager(getContext());
        manager.getNewsHeadlines(listener, "general", null);
        return view;
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {

            if (list.isEmpty()) {
                Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            } else {
                showNews(list);
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }
        }

        @Override
        public void onError(String message) {

            Toast.makeText(getContext(), "An Error Occurred!!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadLines> list) {

        recyclerView = view.findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        adapter = new HomeAdapter(getContext(), list, this);
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
        RequestManager manager = new RequestManager(getContext());
        manager.getNewsHeadlines(listener, category, null);
    }
}