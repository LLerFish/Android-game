package com.ller.team_project;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;

public class GameSetActivity extends AppCompatActivity implements View.OnClickListener {
    private CheckBox chb;
    private Button btn1;
    private int index;
    private int count;
    int[] pictureStageOne, pictureStageTwo, pictureStageThree;
    private int afterActivityIndex;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_set);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btn1 = findViewById(R.id.btn_02);
        btn1.setOnClickListener(this);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert audioManager != null;
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null && bundle.containsKey("pictureTag1")) {
            pictureStageOne = bundle.getIntArray("pictureTag");
            index = bundle.getInt("musicIndex");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            count = index;
        } else if (bundle != null && bundle.containsKey("pictureTag2")) {
            pictureStageTwo = bundle.getIntArray("pictureTag2");
            index = bundle.getInt("musicIndex");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            count = index;
        } else if (bundle != null && bundle.containsKey("pictureTag3")) {
            pictureStageThree = bundle.getIntArray("pictureTag3");
            index = bundle.getInt("musicIndex");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            count = index;
        }

        chb = findViewById(R.id.chb1);
        chb.setOnCheckedChangeListener((buttonView, isChecked) ->
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, isChecked ? 0 : (maxVolume / 2), 0)
        );

        playMedia();
    }

    // 按下返回
    public void back(View v) {
        Intent intent = new Intent();

        Bundle bundle = new Bundle();
        afterActivityIndex = 1;
        //if (x1 == a) {
            bundle.putIntArray("pictureTag1", pictureStageOne);
        //} else if (x2 == b) {
        //    bundle.putIntArray("pictureTag2", pictureStageTwo);
        //} else if (x3 == c) {
        //    bundle.putIntArray("pictureTag3", pictureStageThree);
        // }

        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        finish();
    }

    // 撥放音樂
    public void playMedia() {
        if (index == 0) {
            MediaPlayer mp = GameMediaController.getMain(this);
            mp.start();
            mp.setLooping(true);
        }
        if (index == 1) {
            MediaPlayer mp = GameMediaController.getStage1(this);
            mp.start();
            mp.setLooping(true);
        }

        if (index == 2) {
            MediaPlayer mp = GameMediaController.getStage2(this);
            mp.start();
            mp.setLooping(true);
        }
        if (index == 3) {
            MediaPlayer mp = GameMediaController.getStage3(this);
            mp.start();
            mp.setLooping(true);
        }
    }

    // 按下成員名單
    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(GameSetActivity.this)
                .setIcon(R.drawable.icn_score)
                .setTitle("成員名單")
                .setMessage("老師:鄭富國\n組長:吳芃\n組員:謝季軒\n組員:陳廷瑀\n組員:藍莉淇\n組員:廖建鈞")
                .setPositiveButton("ok", null)
                .show();
    }
}
