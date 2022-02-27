package com.example.tfilm.Interface;

import android.widget.ImageView;

import com.example.tfilm.Model.Artists;
import com.example.tfilm.Model.Movie;

public interface MovieItemClickListener {
    void MovieClick(Movie movie, ImageView imageViewMovie);
//    void ArtistsClick(Artists artists, ImageView imageViewArtists);
}
