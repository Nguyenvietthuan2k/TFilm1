package com.example.tfilm.ViewModels;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tfilm.Model.Movie;
import com.example.tfilm.Repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    // This class is used for ViewModel
    private MovieRepository movieRepository;

    // Constructor
    public MovieListViewModel(){
        movieRepository = MovieRepository.getInstance();
    }
    public LiveData<List<Movie>> getMovies() {
        return movieRepository.getMovies();
    }
    public LiveData<List<Movie>> getPop() {
        return movieRepository.getPop();
    }
    // 3- Calling method in view-model
    public void searchMovieApi(String query, int pageNumber) {
        movieRepository.searchMovieApi(query, pageNumber);
    }
    public void searchMoviesPop(int pageNumber) {
        movieRepository.searchMoviesPop(pageNumber);
    }
    public void searchNextPage() {
        movieRepository.searchNextPage();
    }
}
