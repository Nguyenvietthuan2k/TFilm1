package com.example.tfilm;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfilm.Interface.OnMovieListener;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Widgets
    TextView title, release_date, duration;
    ImageView imageView;
    RatingBar ratingBar;
    // Click Listener
//    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView) { //  OnMovieListener onMovieListener
        super(itemView);

//        this.onMovieListener = onMovieListener;

//        imageView = itemView.findViewById();
//        ratingBar = itemView.findViewById(R.id.);

//        itemView.setOnClickListener(this);
    }

    public MovieViewHolder(View view, OnMovieListener onMovieListener) {
        super();
    }

    @Override
    public void onClick(View view) {
//        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
