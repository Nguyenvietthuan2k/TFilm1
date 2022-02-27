package com.example.tfilm.Adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfilm.Interface.OnMovieListener;
import com.example.tfilm.R;

public class Popular_View_Holder extends RecyclerView.ViewHolder implements View.OnClickListener {

    // Anh xa
    OnMovieListener onMovieListener;
    ImageView imageView22;
    RatingBar ratingBar22;

    public Popular_View_Holder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);

        this.onMovieListener = onMovieListener;
        imageView22 = itemView.findViewById(R.id.image_Results_Movie);
        ratingBar22 = itemView.findViewById(R.id.ratingBar);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
