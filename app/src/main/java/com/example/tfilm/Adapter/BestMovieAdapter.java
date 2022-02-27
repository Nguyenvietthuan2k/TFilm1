package com.example.tfilm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tfilm.Model.Movie;
import com.example.tfilm.R;

import java.util.List;

public class BestMovieAdapter extends RecyclerView.Adapter<BestMovieAdapter.MyViewHolder> {
    Context context;
    List<Movie> movieBestList;

    @NonNull
    @Override
    public BestMovieAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new BestMovieAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BestMovieAdapter.MyViewHolder holder, int position) {
        holder.tittleMovie.setText(movieBestList.get(position).getTitle());
        holder.imageMovie.setImageResource(movieBestList.get(position).getThumbnail());
        holder.movieDuration.setText(movieBestList.get(position).getMovieDuration());
    }

    @Override
    public int getItemCount() {
        return movieBestList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tittleMovie;
        ImageView imageMovie;
        TextView movieDuration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tittleMovie = itemView.findViewById(R.id.title_movie);
            imageMovie = itemView.findViewById(R.id.image_movie);
            movieDuration = itemView.findViewById(R.id.movie_duration);
        }
    }
}
