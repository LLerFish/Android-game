package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Stage2lActivity extends StageLeftActivity implements View.OnClickListener {

    private boolean drinkGateOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register gate on-click listener
        findViewById(R.id.btnCoinGate);
        findViewById(R.id.btnDrinkGate);

        // Play music
        playMedia();
    }

    @Override
    protected int stage() {
        return 2;
    }

    @Override
    protected void onBlockClick(int pos) {

    }

    @Override
    protected void onSwitch() {
        // afterActivityIndex = 2;
        // Bundle bundle = new Bundle();
        // bundle.putIntArray("pictureTag2", pictureStageTwo);
        // bundle.putInt("musicStopIndex", afterActivityIndex);
        // bundle.putIntArray("coinPlace", flag);

        Intent intent = new Intent(this, Stage2rActivity.class);
        // intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    protected int defaultContentView() {
        return R.layout.activity_stage2l;
    }

    // Goto setting page
    /*public void setting(View v) {
        Bundle bundle = new Bundle();
        index = 2;
        afterActivityIndex = 2;
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        Intent intent = new Intent(this, GameSettingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go back to map
    public void back(View v) {
        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag2", pictureStageTwo);

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Goto right side
    public void changePage(View v) {
        afterActivityIndex = 2;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        //bundle.putIntArray("coinPlace", flag);

        Intent intent = new Intent(this, Stage2rActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

    private void playMedia() {
        MediaPlayer mp = GameMediaController.getStage2(this);
        mp.start();
        mp.setLooping(true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.btnDrinkGate:
            ImageView bg = findViewById(R.id.imgBackground);
            bg.setImageResource(drinkGateOpen ? R.drawable.stage2 : R.drawable.stage2_2_open);
            drinkGateOpen = !drinkGateOpen;
            break;
        }
    }

    /*// Pull objects to below blocks
    public void changeButtonListEvent(View v) {
        if (v.getId() == R.id.btnOneDollar) {
            if (pictureStageTwo[0] == 0) {
                btnList1.setImageResource(R.drawable.one);
                pictureStageTwo[0] = 1;
                btnOneDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[1] == 0) {
                btnList2.setImageResource(R.drawable.one);
                pictureStageTwo[1] = 1;
                btnOneDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[2] == 0) {
                btnList3.setImageResource(R.drawable.one);
                pictureStageTwo[2] = 1;
                btnOneDollar.setVisibility(View.GONE);
            }
        }
    }*/

    /* public void changeButtomOpenDrinkgateEvent(View v){
        if (v.getId() == R.id.btnDrinkGate) {
            if(drinkgate == 0) {
                background.setImageResource(R.drawable.stage2_2_open);
                drinkgate=1;
            }
            else if(drinkgate == 1){
                background.setImageResource(R.drawable.stage2);
                drinkgate=0;
            }
        }
    }*/
}
