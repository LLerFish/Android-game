package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Play music
        playMedia();
    }

    // Game starts
    public void start(View v) {
        startActivity(new Intent(this, MapActivity.class));
    }

    // Go setting page
    public void setting(View v) {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.stop();
        mp.prepareAsync();

        Intent intent = new Intent();
        intent.setClass(MainActivity.this, GameSetActivity.class);

        index = 0;
        Bundle bundle = new Bundle();
        bundle.putInt("musicIndex", index);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // Play background music
    public void playMedia() {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.start();
        mp.setLooping(true);
    }
}
