package com.example.tfilm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tfilm.Adapter.MovieAdapter;
import com.example.tfilm.Adapter.SlidePagerAdapter;
import com.example.tfilm.Interface.MovieItemClickListener;
import com.example.tfilm.Model.Movie;
import com.example.tfilm.Model.Slide;
import com.example.tfilm.ViewModels.MovieListViewModel;
import com.google.android.material.tabs.TabLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity  implements MovieItemClickListener {

    private ViewPager viewPager;
    private List<Slide> slideList;
    private TabLayout indicator;
    private RecyclerView movieRCV;
    private ImageView imageViewUser;
    private TextView txtViewAll;
    private TextView nameUser;
    private RelativeLayout relativeLayout;
    // ViewModel
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Anh xa
        nameUserLogin();
        callProfileUser();
        ViewPagerMain();
        viewAllActionMovies();
        callHomeActivity();

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

    }
    // Observing any data change
    private void ObserveAnyChange() {
        movieListViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                // Observing for any data change
            }
        });
    }
    // Function call to HomeActivity screen
    private void callHomeActivity() {
        relativeLayout = findViewById(R.id.callHome);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
    }



    // Function get data name from login - set up name
    private void nameUserLogin() {
        nameUser = findViewById(R.id.textNameMain);
        Intent intent = getIntent();
        String nameMain = intent.getStringExtra("Name");
        nameUser.setText(nameMain);
    }
    // Call to ImageUserProfile Screen -> UserProfileActivity
    private void callProfileUser() {
        imageViewUser = findViewById(R.id.userProfileImage);
        imageViewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UserProfileActivity.class));
            }
        });
    }
    // Set up ViewPager in App
    private void ViewPagerMain() {
        viewPager = findViewById(R.id.slide_pager);
        movieRCV = findViewById(R.id.movieRCV);
        // Get Data
        slideList = new ArrayList<>();
        slideList.add(new Slide(R.drawable.endgame));
        slideList.add(new Slide(R.drawable.infinitywar));
        slideList.add(new Slide(R.drawable.blackwindow));
        slideList.add(new Slide(R.drawable.farfromhome));

        viewPager.setAdapter(new SlidePagerAdapter(this, slideList));

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("MeMa","1", R.drawable.endgame, R.drawable.evengerposter));
        movieList.add(new Movie("MeMa1","12", R.drawable.infinitywar, R.drawable.infinityposter));
        movieList.add(new Movie("MeMa2","13", R.drawable.blackwindow, R.drawable.blackwindowposter));
        movieList.add(new Movie("MeMa2","13", R.drawable.farfromhome));
        movieList.add(new Movie("MeMa2","13", R.drawable.ironman));

        movieRCV.setAdapter(new MovieAdapter(this, movieList, this));
        movieRCV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

//        viewPager.setAdapter(slidePagerAdapter);
//        indicator.setupWithViewPager(viewPager, true);

        // Set up timer slider timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MainActivity.SlideTimer(), 4000, 6000);
    }
    // Set up timer run ViewPager
    class SlideTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            });
        }
    }
    // Function call to Action Movies -
    private void viewAllActionMovies() {
        txtViewAll = findViewById(R.id.txtViewAll);
        txtViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent());
            }
        });
    }

    // Function call to Action Movies screen -> Detail Movie
    @Override
    public void MovieClick(Movie movie, ImageView imageView) {

        // Here we send movie information to detail activity
        // Also we will create transition animation between the two activity

        Intent intent = new Intent(this, MovieDetailActivity.class);
        // Send movie information to movieDetail
        intent.putExtra("title",movie.getTitle());
        intent.putExtra("imgURL", movie.getThumbnail());
        intent.putExtra("imgCover", movie.getCoverPhoto());
        //Let create the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, imageView, "sharedName");
        startActivity(intent, options.toBundle());

        // I will make a simple test to see if the click works
        Toast.makeText(this, "item clicked: " + movie.getTitle(), Toast.LENGTH_LONG).show();
    }

//
//    private void getData() {
//        String strPhoneNumber = getIntent().getStringExtra("phone_number");
//        TextView tvUserInfor = findViewById(R.id.tv_info_user);
//        tvUserInfor.setText(strPhoneNumber);
//    }

//    private boolean isNetworkConnected() {
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
//    }

}