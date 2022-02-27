package com.example.tfilm.Api;

import com.example.tfilm.Model.Movie;
import com.example.tfilm.Response.MovieSearchResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
    MovieApi movieApi = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieApi.class);
    // Search for Movies
    // https://api.themoviedb.org/3/search/movie?api_key=&language=en-US&page=1
    @GET("3/search/movie")
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String api_key,
            @Query("query") String query,
            int pageNumber
    );
//    // Making search with ID
//    // Remember that movie_id = 550 is for Fight Club Movie
//    // Let's Test Jack Reacher id
//    @GET("")
//    Call<Movie> getMovie(
//            @Path("movie_id") int movie_id,
//            @Query("api_key") String api_key
//    );
    @GET("3/search/popular")
    Call<MovieSearchResponse> getPopular(
            @Query("api_key") String key,
            @Query("page") int page
    );
}