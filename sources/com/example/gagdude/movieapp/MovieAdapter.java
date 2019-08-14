package com.example.gagdude.movieapp;

import android.content.Intent;
import android.support.p003v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MovieAdapter extends Adapter<ViewHolder> {
    public static final String MOVIE_BACKDROP = "MOVIE_BACKDROP";
    public static final String MOVIE_ID = "MOVIE_ID";
    public static final String MOVIE_OVERVIEW = "MOVIE_OVERVIEW";
    public static final String MOVIE_POPULARITY = "MOVIE_POPULARITY";
    public static final String MOVIE_RELEASE_DATE = "MOVIE_RELEASE_DATE";
    public static final String MOVIE_TITLE = "MOVIE_TITLE";
    ArrayList<Movie> movies;

    public static class ViewHolder extends android.support.p003v7.widget.RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView ratingView;
        public TextView titleView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(C0392R.C0394id.titleTextView);
            this.ratingView = (TextView) itemView.findViewById(C0392R.C0394id.ratingTextView);
            this.imageView = (ImageView) itemView.findViewById(C0392R.C0394id.imageView);
        }
    }

    public MovieAdapter(ArrayList<Movie> movies2) {
        this.movies = movies2;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0392R.layout.movie_list_item, parent, false));
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        final Movie movie = (Movie) this.movies.get(position);
        holder.titleView.setText(movie.getTitle());
        holder.ratingView.setText("Rating: " + String.valueOf(movie.getRating()));
        Picasso.with(holder.imageView.getContext()).load("https://image.tmdb.org/t/p/w640/" + movie.getPoster()).into(holder.imageView);
        holder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MovieInfo.class);
                intent.putExtra(MovieAdapter.MOVIE_TITLE, movie.getTitle());
                intent.putExtra(MovieAdapter.MOVIE_BACKDROP, movie.getBackdrop());
                intent.putExtra(MovieAdapter.MOVIE_RELEASE_DATE, movie.getReleaseDate());
                intent.putExtra(MovieAdapter.MOVIE_OVERVIEW, movie.getOverview());
                intent.putExtra(MovieAdapter.MOVIE_POPULARITY, String.valueOf(movie.getPopularity()));
                intent.putExtra(MovieAdapter.MOVIE_ID, String.valueOf(movie.getID()));
                v.getContext().startActivity(intent);
            }
        });
    }

    public int getItemCount() {
        return this.movies.size();
    }
}
