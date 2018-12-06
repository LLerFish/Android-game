package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

public class MapActivity extends AppCompatActivity {

    int[] pictureStageOne = new int[]{0, 0, 0, 0, 0};
    int[] pictureStageTwo = new int[]{0, 0, 0, 0, 0};
    int[] pictureStageThree = new int[]{0, 0, 0, 0, 0};
    int[] btnStage1Gone = new int[]{0, 0,0 ,0, 0};
    int[] btnStage2Gone = new int[]{0, 0,0 ,0, 0};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey("pictureTag1") && bundle.containsKey("pictureTag1")) {
            pictureStageOne = bundle.getIntArray("pictureTag1");
            btnStage1Gone = bundle.getIntArray("pictureGone1");
        } else if (bundle != null && bundle.containsKey("pictureTag2") && bundle.containsKey("pictureTag1")) {
            pictureStageTwo = bundle.getIntArray("pictureTag2");
            btnStage2Gone = bundle.getIntArray("pictureGone2");
        } else if (bundle != null && bundle.containsKey("pictureTag3")) {
            pictureStageThree = bundle.getIntArray("pictureTag3");
        }

        playMedia();        //進入撥放音樂
    }

    // 按下返回主畫面
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(MapActivity.this, MainActivity.class);

        startActivity(intent);
    }

    // 按下第一關
    public void changeStage1(View v) {
        Intent intent = new Intent();
        intent.setClass(MapActivity.this, Stage1Activity.class);

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // 按下第二關
    public void changeStage2(View v) {
        Intent intent = new Intent();
        intent.setClass(MapActivity.this, Stage2Activity.class);

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putIntArray("pictureGone2", btnStage2Gone);
        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // 按下第三關
    public void changeStage3(View v) {
        Intent intent = new Intent();
        intent.setClass(MapActivity.this, Stage3Activity.class);

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

    // 撥放音樂
    public void playMedia() {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.start();
        mp.setLooping(true);
    }
}

