package com.example.gagdude.movieapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.p003v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MovieInfo extends AppCompatActivity {
    ImageView backdrop;
    TextView overview;
    TextView popularity;
    TextView releaseDate;
    TextView title;
    FloatingActionButton webButton;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0392R.layout.activity_movie_info);
        final Intent intent = getIntent();
        this.title = (TextView) findViewById(C0392R.C0394id.titleView);
        this.releaseDate = (TextView) findViewById(C0392R.C0394id.release);
        this.backdrop = (ImageView) findViewById(C0392R.C0394id.backdrop);
        this.overview = (TextView) findViewById(C0392R.C0394id.overview);
        this.popularity = (TextView) findViewById(C0392R.C0394id.popularity);
        this.webButton = (FloatingActionButton) findViewById(C0392R.C0394id.webButton);
        this.title.setText(intent.getStringExtra(MovieAdapter.MOVIE_TITLE));
        this.releaseDate.setText(getString(C0392R.string.releasedate) + " " + intent.getStringExtra(MovieAdapter.MOVIE_RELEASE_DATE));
        Picasso.with(this.backdrop.getContext()).load("https://image.tmdb.org/t/p/w640/" + intent.getStringExtra(MovieAdapter.MOVIE_BACKDROP)).resize(1800, 1800).centerInside().into(this.backdrop);
        this.overview.setText(intent.getStringExtra(MovieAdapter.MOVIE_OVERVIEW));
        this.popularity.setText(getString(C0392R.string.popularity) + " " + intent.getStringExtra(MovieAdapter.MOVIE_POPULARITY));
        this.webButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                String url = "https://www.themoviedb.org/movie/" + intent.getStringExtra(MovieAdapter.MOVIE_ID);
                Intent browserIntent = new Intent("android.intent.action.VIEW");
                browserIntent.setData(Uri.parse(url));
                MovieInfo.this.startActivity(browserIntent);
            }
        });
    }
}
