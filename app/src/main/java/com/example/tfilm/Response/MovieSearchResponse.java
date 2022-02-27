package com.example.tfilm.Response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieSearchResponse {
    @SerializedName("total_results")
    private int total_count;

    @SerializedName("results")
    private List<MovieResponse> movieList;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<MovieResponse> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieResponse> movieList) {
        this.movieList = movieList;
    }

    @Override
    public String toString() {
        return "MovieSearch{" +
                "total_count=" + total_count +
                ", movieList=" + movieList +
                '}';
    }
}
