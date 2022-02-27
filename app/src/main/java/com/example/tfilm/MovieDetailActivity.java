package com.example.tfilm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.tfilm.Adapter.MovieAdapter;
import com.example.tfilm.Interface.MovieItemClickListener;
import com.example.tfilm.Model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieItemClickListener {

    private ImageView movieThumbnailImg, posterMovieThumbnailImg;
    private TextView txtDirector, txtGenre, txtTime, txtYear;
    private FloatingActionButton floatingActionButton;
    private RecyclerView recyclerViewCast;
    ActionBar actionbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // get Data
        innitViews();
        getData();
        actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                floatingAction();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void innitViews() {
        recyclerViewCast = findViewById(R.id.rcvSimilarMovies);
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("MeMa","1", R.drawable.endgame, R.drawable.evengerposter));
        movieList.add(new Movie("MeMa1","12", R.drawable.infinitywar, R.drawable.infinityposter));
        movieList.add(new Movie("MeMa2","13", R.drawable.blackwindow, R.drawable.blackwindowposter));
        movieList.add(new Movie("MeMa2","13", R.drawable.farfromhome));
        movieList.add(new Movie("MeMa2","13", R.drawable.ironman));

        recyclerViewCast.setAdapter(new MovieAdapter(this, movieList, this));
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getData() {
        String movieTittle = getIntent().getExtras().getString("tittle");
        int imageResourceId = getIntent().getExtras().getInt("imgURL");
        int imagePhoto = getIntent().getExtras().getInt("imgCover");
//        movieThumbnailImg = findViewById(R.id.image_movie_detail);
//        Glide.with(this).load(imageResourceId).into(movieThumbnailImg);
//        movieThumbnailImg.setImageResource(imageResourceId);

        posterMovieThumbnailImg = findViewById(R.id.image_detail_poster);
        Glide.with(this).load(imagePhoto).into(posterMovieThumbnailImg);
        posterMovieThumbnailImg.setImageResource(imagePhoto);

//        txtDirector = findViewById(R.id.director);
//        txtGenre = findViewById(R.id.genre);
//        txtTime = findViewById(R.id.timeFilm);
//        txtYear = findViewById(R.id.year);
    }

    private void floatingAction() {
        floatingActionButton.setAnimation(AnimationUtils.loadAnimation(this, R.anim.animation));
        Intent intent = new Intent(this, MoviePlayerActivity.class);
        startActivity(intent);
    }

    @Override
    public void MovieClick(Movie movie, ImageView imageViewMovie) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        // Send movie information to movieDetail
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        //Let create the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MovieDetailActivity.this, imageViewMovie, "sharedName");
        startActivity(intent, options.toBundle());

        // I will make a simple test to see if the click works
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG).show();
    }

}
