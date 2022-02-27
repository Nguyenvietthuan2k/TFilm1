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
import com.example.tfilm.Interface.MovieItemClickListener;
import com.example.tfilm.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context context;
    List<Movie> movieList;
    MovieItemClickListener movieItemClickListener;

    public MovieAdapter(Context context, List<Movie> movieList, MovieItemClickListener movieItemClickListener) {
        this.context = context;
        this.movieList = movieList;
        this.movieItemClickListener = movieItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tittleMovie.setText(movieList.get(position).getTitle());
        holder.imageMovie.setImageResource(movieList.get(position).getThumbnail());
        holder.movieDuration.setText(movieList.get(position).getMovieDuration());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieItemClickListener.MovieClick(movieList.get(getAdapterPosition()),imageMovie);
                }
            });
        }
    }
}
