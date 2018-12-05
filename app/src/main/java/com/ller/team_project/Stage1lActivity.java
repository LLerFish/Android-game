package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Stage1lActivity extends StageLeftActivity {

    // What is this?
    private int afterActivityIndex = 0;
    // WHat is this
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            afterActivityIndex = bundle.getInt("musicStopIndex");
            index = bundle.getInt("musicIndex");
        }
        if (afterActivityIndex == 0) {
            // Play music
            playMedia();
        }
    }

    @Override
    protected int stage() {
        return 1;
    }

    @Override
    protected int defaultContentView() {
        return R.layout.activity_stage1l;
    }

    @Override
    protected void onBlockClick(int pos) {

    }

    @Override
    protected void onSwitch() {
        // afterActivityIndex = 1;
        // Bundle bundle = new Bundle();
        // bundle.putIntArray("pictureTag1", blocks);
        // bundle.putInt("musicStopIndex", afterActivityIndex);

        Intent intent = new Intent(this, Stage1rActivity.class);
        // intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Check stopping music
        if (afterActivityIndex == 0) {
            MediaPlayer mp = GameMediaController.getStage1(this);
            mp.stop();
            mp.prepareAsync();
        }
    }

    // Goto setting page
    public void setting(View v) {
        Intent intent = new Intent(this, GameSettingActivity.class);

        Bundle bundle = new Bundle();
        index = 1;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", blocks);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go back to map
    /*public void back(View v) {
        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", blocks);

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.start();
        mp.setLooping(true);
    }

    // Check game completed
    /*public void resultView(View v) {
        final int flag;
        switch (v.getId()) {
        case R.id.btnList1:
            flag = blocks[0];
            break;
        case R.id.btnList2:
            flag = blocks[1];
            break;
        default:
            return;
        }

        if (flag <= 0 || flag > 2) { return; }

        Intent intent = new Intent(this, flag == 1 ? ShowResult2Activity.class : ShowResultActivity.class);
        startActivity(intent);
        finish();
    }*/
}


