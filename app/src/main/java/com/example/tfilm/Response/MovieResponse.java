package com.example.tfilm.Response;
// This class is for single movie request

import com.example.tfilm.Model.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResponse {
    // 1 - Finding the Movie
    @SerializedName("results")
    @Expose
    private Movie movie;

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
