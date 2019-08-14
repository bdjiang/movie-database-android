package com.example.gagdude.movieapp;

public class MovieCollection {
    private int page;
    private Movie[] results;
    private int total_pages;
    private int total_results;

    public Movie[] getResults() {
        return this.results;
    }
}
