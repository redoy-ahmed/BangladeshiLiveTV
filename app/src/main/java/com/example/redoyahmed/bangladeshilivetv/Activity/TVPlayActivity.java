package com.example.redoyahmed.bangladeshilivetv.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.redoyahmed.bangladeshilivetv.R;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class TVPlayActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnPreparedListener {

    private TextView empty;
    private ProgressBar load;
    private VideoView mVideoView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvplay);

        Vitamio.isInitialized(this);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_tvplay);
        this.url = getIntent().getStringExtra("url");
        init();
    }

    public void init() {
        this.load = findViewById(R.id.load);
        this.empty = findViewById(R.id.empty);
        this.mVideoView = findViewById(R.id.surface_view);
        this.mVideoView.setMediaController(new MediaController(this));
        this.mVideoView.setOnCompletionListener(this);
        this.mVideoView.setOnPreparedListener(this);
        this.mVideoView.setOnErrorListener(this);
        this.mVideoView.setVideoURI(Uri.parse(this.url));
        this.mVideoView.requestFocus();
        loading();
    }

    private void loading() {
        this.load.setVisibility(View.VISIBLE);
        this.empty.setVisibility(View.GONE);
    }

    private void loadComplete(MediaPlayer arg0) {
        this.load.setVisibility(View.GONE);
        this.empty.setVisibility(View.GONE);
        this.mVideoView.start();
        this.mVideoView.resume();
    }

    private void error(String msg) {
        this.load.setVisibility(View.GONE);
        this.mVideoView.setVisibility(View.GONE);
        this.empty.setVisibility(View.VISIBLE);
        if (msg != null) {
            this.empty.setText(msg);
        }
    }

    public void onPrepared(MediaPlayer mp) {
        Log.d("ONLINE TV", "Prepared");
        loadComplete(mp);
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.d("ONLINE TV", "Error");
        error("Unable to play this channel.");
        return false;
    }

    public void onCompletion(MediaPlayer mp) {
        Log.d("ONLINE TV", "Complete");
    }
}
