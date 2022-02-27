package com.example.tfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.tfilm.Adapter.GenreAdapter;
import com.example.tfilm.Model.Genre;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerViewGenre, recyclerViewArtists, recyclerViewMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        callRecyclerViewGenre();
        setUpSearchView();

    }
    // Get data from searchView & query the api to get the result
    private void setUpSearchView() {
        SearchView searchView = findViewById(R.id.searchMovie);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void callRecyclerViewGenre() {
        recyclerViewGenre = findViewById(R.id.recyclerViewGenre);

        List<Genre> genreList = new ArrayList<>();
        genreList.add(new Genre(R.drawable.endgame));
        genreList.add(new Genre(R.drawable.infinityposter));
        genreList.add(new Genre(R.drawable.blackwindowposter));
        genreList.add(new Genre(R.drawable.farfromhome));
        genreList.add(new Genre(R.drawable.ironman));

        recyclerViewGenre.setAdapter(new GenreAdapter(this, genreList));
        recyclerViewGenre.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

}