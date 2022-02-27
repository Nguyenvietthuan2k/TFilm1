package com.example.tfilm;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tfilm.Api.MovieApi;
import com.example.tfilm.Model.Movie;
import com.example.tfilm.Response.MovieSearchResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    // Live Data Search
    private MutableLiveData<List<Movie>> mMovies = new MutableLiveData<>();
    private static MovieApiClient instance;

    // Make Global RUNNABLE
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    // Live Data Popular
    private MutableLiveData<List<Movie>> mMoviesPop = new MutableLiveData<>();

    // Make Popular RUNNABLE
    private RetrieveMoviesRunnablePop retrieveMoviesRunnablePop;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMoviesPop = new MutableLiveData<>();
    }

    public LiveData<List<Movie>> getMovies() {
        return mMovies;
    }
    public LiveData<List<Movie>> getMoviesPop() {
        return mMoviesPop;
    }



    // This method that we are going to call through the classes
    public void searchMoviesApi(String query, int pageNumber) {

        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 4000, TimeUnit.MICROSECONDS);
    }

    public void searchMoviesPop(int pageNumber) {

        if (retrieveMoviesRunnablePop != null) {
            retrieveMoviesRunnablePop = null;
        }
        retrieveMoviesRunnablePop = new RetrieveMoviesRunnablePop(pageNumber);

        final Future myHandler2 = AppExecutors.getInstance().getmNetworkIO().submit(retrieveMoviesRunnablePop);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler2.cancel(true);
            }
        }, 1000, TimeUnit.MICROSECONDS);
    }

    // Retreiving data from RestApi by runnable
    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest=false;
        }
        @Override
        public void run() {
            // Getting the response objects
            try {
                Response response = getMovies(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovieList());
                    if (pageNumber == 1) {
                        // Sending data to live date
                        // PostValue: used for background thread
                        // setValue: not for background thread
                        mMovies.postValue(list);
                    }else {
                        List<Movie> currentMovies = mMovies.getValue();
                        currentMovies.addAll(list);
                        mMovies.postValue(currentMovies);
                    }
                }else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    mMovies.postValue(null);
                }

            }catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }

//             Search Method/ query

        }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return MovieApi.movieApi.searchMovie(
                    "",
                    query,
                    pageNumber
            );
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    // Popular

    private class RetrieveMoviesRunnablePop implements Runnable {

        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePop(int pageNumber) {
            this.pageNumber = pageNumber;
            cancelRequest=false;
        }
        @Override
        public void run() {
            // Getting the response objects
            try {
                Response response2 = getPop(pageNumber).execute();

                if (cancelRequest) {
                    return;
                }
                if (response2.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieSearchResponse)response2.body()).getMovieList());
                    if (pageNumber == 1) {
                        // Sending data to live date
                        // PostValue: used for background thread
                        // setValue: not for background thread
                        mMoviesPop.postValue(list);
                    }else {
                        List<Movie> currentMovies = mMoviesPop.getValue();
                        currentMovies.addAll(list);
                        mMoviesPop.postValue(currentMovies);
                    }
                }else {
                    String error = response2.errorBody().string();
                    Log.v("Tag", "Error " + error);
                    mMoviesPop.postValue(null);
                }

            }catch (IOException e) {
                e.printStackTrace();
                mMoviesPop.postValue(null);
            }

//             Search Method/ query

        }
        private Call<MovieSearchResponse> getPop(int pageNumber) {
            return MovieApi.movieApi.getPopular(
                    "",
                    pageNumber
            );
        }
        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
}


