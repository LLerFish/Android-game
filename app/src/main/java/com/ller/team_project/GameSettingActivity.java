package com.ller.team_project;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class GameSettingActivity extends AppCompatActivity implements OnClickListener, CompoundButton.OnCheckedChangeListener {

    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Init views
        findViewById(R.id.btnStaff).setOnClickListener(this);
        CheckBox chbMute = findViewById(R.id.chbMute);
        chbMute.setOnCheckedChangeListener(this);

        // Check bundles
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int afterActivityIndex;
            int count;
            if (bundle.containsKey("pictureTag1")) {
                int[] pictureStageOne = bundle.getIntArray("pictureTag1");
                index = bundle.getInt("musicIndex");
                afterActivityIndex = bundle.getInt("musicStopIndex");
                count = index;
            }
            else if (bundle.containsKey("pictureTag2")) {
                int[] pictureStageTwo = bundle.getIntArray("pictureTag2");
                index = bundle.getInt("musicIndex");
                afterActivityIndex = bundle.getInt("musicStopIndex");
                count = index;
            }
            else if (bundle.containsKey("pictureTag3")) {
                int[] pictureStageThree = bundle.getIntArray("pictureTag3");
                index = bundle.getInt("musicIndex");
                afterActivityIndex = bundle.getInt("musicStopIndex");
                count = index;
            }
        }

        // Play music
        playMedia();
    }

    // Goto back
    /*public void back(View v) {

        Bundle bundle = new Bundle();
        afterActivityIndex = 1;
        //if (x1 == a) {
        bundle.putIntArray("pictureTag1", pictureStageOne);
        //} else if (x2 == b) {
        //    bundle.putIntArray("pictureTag2", pictureStageTwo);
        //} else if (x3 == c) {
        //    bundle.putIntArray("pictureTag3", pictureStageThree);
        // }

        Intent intent = new Intent();
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        finish();
    }*/

    // Play music
    public void playMedia() {
        if (index == 0) {
            MediaPlayer mp = GameMediaController.getMain(this);
            mp.start();
            mp.setLooping(true);
        }
        else if (index == 1) {
            MediaPlayer mp = GameMediaController.getStage1(this);
            mp.start();
            mp.setLooping(true);
        }
        else if (index == 2) {
            MediaPlayer mp = GameMediaController.getStage2(this);
            mp.start();
            mp.setLooping(true);
        }
        else if (index == 3) {
            MediaPlayer mp = GameMediaController.getStage3(this);
            mp.start();
            mp.setLooping(true);
        }
    }

    // Click staff
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStaff) {
            final String msg = "老師:鄭富國\n組長:吳芃\n組員:謝季軒\n組員:陳廷瑀\n組員:藍莉淇\n組員:廖建鈞";
            new AlertDialog.Builder(GameSettingActivity.this).setIcon(R.drawable.icn_score)
                                                             .setTitle("成員名單")
                                                             .setMessage(msg)
                                                             .setPositiveButton("ok", null)
                                                             .show();
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        // TODO Not good. Music should be stopped.
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        assert audioManager != null;
        final int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, isChecked ? 0 : (maxVolume / 2), 0);
    }
}
