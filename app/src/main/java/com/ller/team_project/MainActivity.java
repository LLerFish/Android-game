package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnGameStart).setOnClickListener(this);
        findViewById(R.id.btnSetting).setOnClickListener(this);

        // Play music
        playMedia();
    }

    // Game starts
    private void gameStart() {
        startActivity(new Intent(this, MapActivity.class));
    }

    // Go gotoSetting page
    private void gotoSetting() {
        startActivity(new Intent(this, GameSettingActivity.class));
    }

    // Play background music
    private void playMedia() {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.btnGameStart:
            gameStart();
            break;
        case R.id.btnSetting:
            gotoSetting();
            break;
        }
    }
}
