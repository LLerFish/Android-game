package com.ller.team_project;

import android.media.MediaPlayer;
import android.os.Bundle;

public class Stage1rActivity extends StageRightActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        playMedia();
    }

    @Override
    protected int defaultContentView() {
        return R.layout.activity_stage1r;
    }

    @Override
    protected int stage() {
        return 1;
    }

    @Override
    protected void onBlockClick(int pos) {

    }

    // Go back to map
    /*public void back(View v) {
        Intent intent = new Intent(this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.stop();
        mp.prepareAsync();
        intent.putExtras(bundle);

        startActivity(intent);
    }*/

    // Play music
    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.start();
        mp.setLooping(true);
    }
}
