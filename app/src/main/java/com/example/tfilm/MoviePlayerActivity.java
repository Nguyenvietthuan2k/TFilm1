package com.example.tfilm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MoviePlayerActivity extends AppCompatActivity {
    // Initialize variable

    PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    ProgressBar progressBar;
    ImageView btFullScreen;
    boolean flag = false;
    public static final String VIDEO = "https://static.videezy.com/system/resources/previews/000/008/302/original/Dark_Haired_Girl_angry_-what-!-_1.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Make activity full screen
        setFullScreen();
        setContentView(R.layout.activity_movie_player);
        // Assign Variable
//        initExoPlayer();
        playerView = findViewById(R.id.player_view);
        progressBar = findViewById(R.id.progress_bar);
        btFullScreen = playerView.findViewById(R.id.bt_fullscreen);

        // Video url
        Uri videoUrl = Uri.parse(VIDEO);
        // Initialize load control
        LoadControl loadControl = new DefaultLoadControl();
        // Initialize band width meter
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        // Initialize track selector
        TrackSelector trackSelector = new DefaultTrackSelector(
                new AdaptiveTrackSelection.Factory(bandwidthMeter)
        );
        // Initialize simple exo player
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(MoviePlayerActivity.this, trackSelector, loadControl
        );
        // Initialize data source factory
        DefaultHttpDataSourceFactory defaultHttpDataSourceFactory = new DefaultHttpDataSourceFactory(
                "exoplayer_video"
        );
        // Initialize extractors factory
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
        // Initialize media source
        MediaSource mediaSource = new ExtractorMediaSource(videoUrl, defaultHttpDataSourceFactory, extractorsFactory, null, null);
        // Set player
        playerView.setPlayer(simpleExoPlayer);
        // Keep screen on
        playerView.setKeepScreenOn(true);
        // Prepare media
        simpleExoPlayer.prepare(mediaSource);
        // Play video when ready
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                // Check condition
                if (playbackState == Player.STATE_BUFFERING) {
                    // When buffering
                    // Show progress bar
                    progressBar.setVisibility(View.VISIBLE);
                }else if (playbackState == Player.STATE_READY) {
                    // When ready
                    // Hide progress bar
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public boolean equals(@Nullable Object obj) {
                return super.equals(obj);
            }

            @NonNull
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }

            @NonNull
            @Override
            public String toString() {
                return super.toString();
            }

            @Override
            protected void finalize() throws Throwable {
                super.finalize();
            }
        });

        btFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Check condition
                if (flag) {
                    btFullScreen.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_fullscreen));
            // Set portrait orientation
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            // Set flag value is false
            flag = false;
                }else {
                    // When flag is false
                    // Set exit full screen
                    btFullScreen.setImageDrawable(getResources()
                            .getDrawable(R.drawable.ic_fullscreen_exit));
                    // Set landscape orientation
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    // Set flag value is true
                    flag = true;
                }
            }
        });
//        }
//        hideActionbar();
    }

//    private void hideActionbar() {
//        getSupportActionBar().hide();
//    }

    private void setFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

//    private void initExoPlayer() {
//        playerView = findViewById(R.id.player_view);
//        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this);
//
//        playerView.setPlayer(simpleExoPlayer);
//        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
//                Util.getUserAgent(this, "appName"));
//        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse(VIDEO));
//        simpleExoPlayer.prepare(mediaSource);
//        simpleExoPlayer.setPlayWhenReady(true);
//
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        simpleExoPlayer.release();
//    }
    // Controlling the player
//    private voide playPlayer() {
//        if (player != null) {
//            player.setPlayWhenReady(true);
//        }
//    }
//
//    private voide pausePlayer() {
//        if (player != null) {
//            player.setPlayWhenReady(false);
//        }
//    }
//
//    private void seekTo(long positionInMS) {
//        if (player != null) {
//            player.seekTo(positionInMS);
//        }
//    }
//
//    private void releasePlayer() {
//        if (player != null) {
//            player.release();
//        }
//    }

    @Override
    protected void onPause() {
        super.onPause();
        // Stop video when ready
        simpleExoPlayer.setPlayWhenReady(false);
        // Get playback state
        simpleExoPlayer.getPlaybackState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Play video when ready
        simpleExoPlayer.setPlayWhenReady(true);
        // Get playback state
        simpleExoPlayer.getPlaybackState();
    }
}