package com.example.tfilm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tfilm.Model.Artists;
import com.example.tfilm.R;

import java.util.List;

public class ArtistsMovieAdapter extends RecyclerView.Adapter<ArtistsMovieAdapter.MyViewHolder> {

    Context context;
    List<Artists> artistsList;


    public ArtistsMovieAdapter(){}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_artists, parent, false);
        return new ArtistsMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(artistsList.get(position).getThumbnail()).into(holder.imgArtists);
    }

    @Override
    public int getItemCount() {
        return artistsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imgArtists;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArtists = itemView.findViewById(R.id.image_artists);
        }
    }
}
