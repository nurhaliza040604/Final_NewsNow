package com.example.newsnow.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsnow.R;
import com.example.newsnow.listeners.SelectListener;
import com.example.newsnow.model.NewsHeadLines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private Context context;
    private List<NewsHeadLines> headLines;
    private SelectListener listener;

    public HomeAdapter(Context context, List<NewsHeadLines> headLines, SelectListener listener) {
        this.context = context;
        this.headLines = headLines;
        this.listener = listener;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeViewHolder(LayoutInflater.from(context).inflate
                (R.layout.headline_list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.text_title.setText(headLines.get(position).getTitle());
        holder.text_source.setText(headLines.get(position).getSource().getName());
        if (headLines.get(position).getUrlToImage() != null) {
            Picasso.get().load(headLines.get(position).getUrlToImage()).into(holder.img_headline);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(headLines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headLines.size();
    }
}
