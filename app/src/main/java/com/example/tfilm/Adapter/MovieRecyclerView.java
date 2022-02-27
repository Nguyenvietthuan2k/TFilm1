package com.example.tfilm.Adapter;

import static android.os.Build.VERSION_CODES.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tfilm.Api.Credentials;
import com.example.tfilm.Interface.OnMovieListener;
import com.example.tfilm.Model.Movie;
import com.example.tfilm.MovieViewHolder;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> mMovies;
    private OnMovieListener onMovieListener;

    private static final int DISPLAY_POP = 1;
    private static final int DISPLAY_SEARCH = 2;

    public MovieRecyclerView(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.,parent, false);
//        return new MovieViewHolder(view); // (view, onMovieListener)

        View view = null;
        if (viewType == DISPLAY_SEARCH) {
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.,parent, false);
            return new MovieViewHolder(view, onMovieListener); // (view, onMovieListener)
        }else {
            view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.,parent, false);
            return new MovieViewHolder(view, onMovieListener); // (view, onMovieListener)
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int itemViewType = getItemViewType(position);
        if (itemViewType == DISPLAY_SEARCH) {
            // Vote average is over 7, and our rating bar is over 5 star: dividing by 2
//      ((MovieViewHolder)holder).ratingbar.setText(mMovies.get(position).getTitle());

            // ImageView: Using Glide Library
//        Glide.with(holder.itemView.getContext())
//                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
//                .into(((MovieViewHolder)holder).imageView);
        }else {
            // Vote average is over 7, and our rating bar is over 5 star: dividing by 2
//      ((Popular_View_Holder)holder).ratingbar22.setRatting(mMovies.get(position).getVote_average()/2);

            // ImageView: Using Glide Library
//        Glide.with(holder.itemView.getContext())
//                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
//                .into(((MovieViewHolder)holder).imageView);
        }

//      ((MovieViewHolder)holder).title.setText(mMovies.get(position).getTitle());
//      ((MovieViewHolder)holder).release_date.setText(mMovies.get(position).getTitle());

        // Their is an error in runtime duration
        // Let's Fix this error

//      ((MovieViewHolder)holder).duration.setText(mMovies.get(position).getTitle());
        // Vote average is over 7, and our rating bar is over 5 star: dividing by 2
//      ((MovieViewHolder)holder).ratingbar.setText(mMovies.get(position).getTitle());

        // ImageView: Using Glide Library
//        Glide.with(holder.itemView.getContext())
//                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
//                .into(((MovieViewHolder)holder).imageView);
    }
    @Override
    public int getItemCount() {
        if (mMovies !=null) {
            return mMovies.size();
        }
        return 0;
    }

    public void setmMovies(List<Movie> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    // Getting the id of the movie clicked
    public Movie getSelectedMovie(int position) {
        if (mMovies!=null) {
            if (mMovies.size() > 0) {
                return mMovies.get(position);
            }
        }
        return null;
    }
    public int getItemViewType(int position) {
        if (Credentials.POPULAR) {
            return DISPLAY_POP;
        }else {
            return DISPLAY_SEARCH;
        }
    }
}
