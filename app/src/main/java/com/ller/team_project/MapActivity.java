package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MapActivity extends GameActivity implements OnClickListener {

    private int[] images = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init views
        findViewById(R.id.btnStage1).setOnClickListener(this);
        findViewById(R.id.btnStage2).setOnClickListener(this);
        findViewById(R.id.btnStage3).setOnClickListener(this);

        // Check bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            for (int i = 1; i <= 3; i++) {
                if ((images = bundle.getIntArray("pictureTag" + i)) != null) { break; }
            }
        }
        if (images == null) {
            images = new int[]{0, 0, 0, 0, 0};
        }

        // Play music
        playMedia();
    }

    @Override
    protected int defaultContentView() {
        return R.layout.activity_map;
    }

    private void gotoStage(int stage) {

        MediaPlayer mp = GameMediaController.getMain(this);
        if (mp.isPlaying()) {
            mp.stop();
            mp.prepareAsync();
        }

        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag" + stage, images);

        final Class clazz;
        switch (stage) {
        case 1:
            clazz = Stage1Activity.class;
            break;
        case 2:
            clazz = Stage2Activity.class;
            break;
        case 3:
            clazz = Stage3Activity.class;
            break;
        default:
            throw new RuntimeException("Invalid stage: " + stage);
        }

        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Play music
    private void playMedia() {
        MediaPlayer mp = GameMediaController.getMain(this);
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.btnStage1:
            gotoStage(1);
            break;
        case R.id.btnStage2:
            gotoStage(2);
            break;
        case R.id.btnStage3:
            gotoStage(3);
            break;
        }
    }
}

