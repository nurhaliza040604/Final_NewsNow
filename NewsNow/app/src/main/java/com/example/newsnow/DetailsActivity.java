package com.example.newsnow;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newsnow.db.NewsRepository;
import com.example.newsnow.model.NewsHeadLines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {

    NewsHeadLines headLines;
    TextView txt_title, txt_author, txt_time, txt_detail;
    ImageView img_news;
    FloatingActionButton fabFav;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txt_detail = findViewById(R.id.text_detail_detail);
        txt_title = findViewById(R.id.text_detail_title);
        txt_time = findViewById(R.id.text_detail_time);
        txt_author = findViewById(R.id.text_detail_author);
        img_news = findViewById(R.id.img_detail_news);
        fabFav = findViewById(R.id.fav_button);

        headLines = (NewsHeadLines) getIntent().getSerializableExtra("data");

        txt_title.setText(headLines.getTitle());
        txt_author.setText(headLines.getAuthor());
        txt_detail.setText(headLines.getDescription());
        txt_time.setText(convertDateString(headLines.getPublishedAt()));

        Picasso.get().load(headLines.getUrlToImage()).into(img_news);

        NewsRepository newsRepository = new NewsRepository(getApplicationContext());

        fabFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsRepository.insertNewsHeadline(headLines);
                Toast.makeText(DetailsActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static String convertDateString(String dateString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy, HH:mm", Locale.getDefault());

        try {
            Date date = inputFormat.parse(dateString);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}