package com.example.tfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import com.example.tfilm.Adapter.MovieRecyclerView;
import com.example.tfilm.Interface.OnMovieListener;
import com.example.tfilm.Model.Movie;
import com.example.tfilm.ViewModels.MovieListViewModel;

import java.util.List;

public class MovieListActivity extends AppCompatActivity implements OnMovieListener {

    // RecyclerView
    private RecyclerView recyclerView;
    private MovieRecyclerView movieRecyclerAdapter;

    // ViewModel
    private MovieListViewModel movieListViewModel;

    Button button;
    Toolbar toolbar;
    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // Set up SearchView
        SetupSearchView();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rcv_list);
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);


        // Calling the observers
        ObserveAnyChange();
        ObservePopularMovies();
        ConfigureRecyclerView();

        // Getting popular movies
        movieListViewModel.searchMoviesPop(1);


    }
    // 5- Intializing recyclerview & adding data to it
    private void ConfigureRecyclerView() {
        // Live Data can't be passed via the constructor
        movieRecyclerAdapter = new MovieRecyclerView(this);

        recyclerView.setAdapter(movieRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // RecyclerView Pagination
        // Loading next page of api response
        recyclerView.addOnScrollListener(onScr
                if(!recyclerView.canScrollVertically(1)) {
                    // Here we need to display the next search results on the next page of api
                    movieListViewModel.searchNextPage();
        }
        );
    }

    private void ObservePopularMovies() {
        movieListViewModel.getPop().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // Observing for any data change
                if (movies != null) {
                    for (Movie movie: movies) {
                        // Get the data in log
//                        movieRecyclerAdapter.setmMovies(movies);
                    }
                }

            }
        });
    }
    // Get data from searchView & query the api to get the results (Movie)
    private void SetupSearchView() {
        final SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieListViewModel.searchMovieApi(
                        query,
                        1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPopular = false;
            }
        });
    }

    // Observing any data change
    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // Observing for any data change
                if (movies != null) {
                    for (Movie movie: movies) {
                        // Get the data in log
//                        movieRecyclerAdapter.setmMovies(movies);
                    }
                }

            }
        });
    }

    // 4 - Calling method in Main Activity
    private void searchMovieApi(String query, int pageNumber) {
        movieListViewModel.searchMovieApi(query, pageNumber);
    }


    @Override
    public void onMovieClick(int position) {

        // We don't need position of the movie in recyclerview
        // WE NEED THE ID OF THE MOVIE IN ORDER TO GET ALL IT"S DETAILS

        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", (CharSequence) movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

    @Override
    public void onCategoryClick(String category) {

    }
}