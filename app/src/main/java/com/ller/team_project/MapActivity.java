package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class MapActivity extends AppCompatActivity {

    private int[] pictureStageOne = new int[]{0, 0, 0, 0, 0};
    private int[] pictureStageTwo = new int[]{0, 0, 0, 0, 0};
    private int[] pictureStageThree = new int[]{0, 0, 0, 0, 0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("pictureTag1")) {
                pictureStageOne = bundle.getIntArray("pictureTag1");
            }
            else if (bundle.containsKey("pictureTag2")) {
                pictureStageTwo = bundle.getIntArray("pictureTag2");
            }
            else if (bundle.containsKey("pictureTag3")) {
                pictureStageThree = bundle.getIntArray("pictureTag3");
            }
        }

        // Play music
        playMedia();
    }

    // Go back to home page
    public void back(View v) {
        startActivity(new Intent(this, MainActivity.class));
    }

    // Go to stage I
    public void changeStage1(View v) {
        Intent intent = new Intent(this, Stage1Activity.class);

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag1", pictureStageOne);

        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go to stage II
    public void changeStage2(View v) {
        Intent intent = new Intent(this, Stage2Activity.class);

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);

        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go to stage III
    public void changeStage3(View v) {
        Intent intent = new Intent(this, Stage3Activity.class);

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag3", pictureStageThree);

        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Play music
    public void playMedia() {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.start();
        mp.setLooping(true);
    }
}

