package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Stage1Activity extends AppCompatActivity {

    private ImageButton btnList1, btnList2, btnObjectCake, btnObjectEraser;
    private int[] pictureStageOne = new int[]{0, 0, 0};
    private int afterActivityIndex = 0;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnObjectEraser = findViewById(R.id.btnObjectEraser);
        btnObjectCake = findViewById(R.id.btnObjectCake);
        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);

        Bundle bundle = getIntent().getExtras();
        // Check bundle
        if (bundle != null) {
            pictureStageOne = bundle.getIntArray("pictureTag1");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            index = bundle.getInt("musicIndex");
            if (pictureStageOne[0] == 1) {
                btnList1.setImageResource(R.drawable.erase);
                // picture[0] = 1;
            }
            else if (pictureStageOne[1] == 1) {
                btnList2.setImageResource(R.drawable.erase);
                // picture[1] = 1;
            }
            if (pictureStageOne[0] == 2) {
                btnList1.setImageResource(R.drawable.cake);
                //  picture[0] = 2;
            }
            else if (pictureStageOne[1] == 2) {
                btnList2.setImageResource(R.drawable.cake);
                // picture[1] = 2;
            }
        }
        if (afterActivityIndex == 0) {
            // Play music
            playMedia();
        }
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
        Intent intent = new Intent(this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 1;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go back to map
    public void back(View v) {
        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Goto right-map
    public void changePage(View v) {
        Intent intent = new Intent(this, Stage2_2Activity.class);

        afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.start();
        mp.setLooping(true);
    }

    // Pull objects to below blocks
    public void changeButtonListEvent(View v) {
        if (v.getId() == R.id.btnObjectEraser) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.erase);
                pictureStageOne[0] = 1;
                btnObjectEraser.setVisibility(View.GONE);
            }
            else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.erase);
                pictureStageOne[1] = 1;
                btnObjectEraser.setVisibility(View.GONE);
            }
        }
        else if (v.getId() == R.id.btnObjectCake) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.cake);
                pictureStageOne[0] = 2;
                btnObjectCake.setVisibility(View.GONE);
            }
            else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.cake);
                pictureStageOne[1] = 2;
                btnObjectCake.setVisibility(View.GONE);
            }
        }
    }

    // Check game completed
    public void resultView(View v) {
        final int flag;
        switch (v.getId()) {
            case R.id.btnList1:
                flag = pictureStageOne[0];
                break;
            case R.id.btnList2:
                flag = pictureStageOne[1];
                break;
            default:
                return;
        }

        if (flag <= 0 || flag > 2)
            return;

        Intent intent = new Intent(this, flag == 1 ? ShowResult2Activity.class : ShowResultActivity.class);
        startActivity(intent);
        finish();
    }

    // Check setting tool's visibility to 'GONE'
    public void whetherToSetGone(View v) {
        // 判斷哪個關卡進來,給定一個參數判斷btn的數量,在把參數用迴圈方式去判斷是否取得物件跟設定為GONE...etc
        // for (int i=0; i<num; i++) {

        // }
    }
}


