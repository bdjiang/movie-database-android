package com.example.gagdude.movieapp;

public class Movie {
    private String backdrop_path;

    /* renamed from: id */
    private int f43id;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private double vote_average;

    public String getTitle() {
        return this.title;
    }

    public double getRating() {
        return this.vote_average;
    }

    public String getPoster() {
        return this.poster_path.substring(1);
    }

    public String getBackdrop() {
        return this.backdrop_path.substring(1);
    }

    public String getReleaseDate() {
        return this.release_date;
    }

    public String getOverview() {
        return this.overview;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public int getID() {
        return this.f43id;
    }
}
