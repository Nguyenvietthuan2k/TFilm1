package com.example.tfilm.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tfilm.Model.Movie;
import com.example.tfilm.MovieApiClient;

import java.util.List;

public class MovieRepository {

    // This class is acting as repositories

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;


    public static MovieRepository getInstance(){
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }
    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }


    public LiveData<List<Movie>> getMovies() {
        return movieApiClient.getMovies();
    }
    public LiveData<List<Movie>> getPop() {
        return movieApiClient.getMoviesPop();
    }

    // 2 - Calling the method in repository
    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchMoviesPop(int pageNumber) {
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesPop(pageNumber);
    }

    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber+1);
    }
}
