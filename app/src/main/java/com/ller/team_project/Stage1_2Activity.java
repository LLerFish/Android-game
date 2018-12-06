package com.ller.team_project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage1_2Activity extends AppCompatActivity {

    int[] pictureStageOne = new int[]{0, 0, 0};
    int[] btnStage1Gone = new int[]{0, 0, 0};
    private ImageButton btnList1, btnList2;
    private int afterActivityIndex, index;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            pictureStageOne = bundle.getIntArray("pictureTag1");
            btnStage1Gone = bundle.getIntArray("pictureGone1");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            assert pictureStageOne != null;

            setImage();
        }

        playMedia();
    }

    // get pictureTag1 and set image source
    public void setImage(){
        if (pictureStageOne[0] == 1) {
            btnList1.setImageResource(R.drawable.erase);
        } else if (pictureStageOne[1] == 1) {
            btnList2.setImageResource(R.drawable.erase);
        }
        if (pictureStageOne[0] == 2) {
            btnList1.setImageResource(R.drawable.cake);
        } else if (pictureStageOne[1] == 2) {
            btnList2.setImageResource(R.drawable.cake);
        }
    }

    // go to the GameSetActivity
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1_2Activity.this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 1;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // back to the MapActivity
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1_2Activity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.stop();
        mp.prepareAsync();
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // switch the stage to the left side
    public void changePage(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1_2Activity.this, Stage1Activity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.start();
        mp.setLooping(true);
    }

    public void setFlagOfResult(View v) {
        if (v.getId() == R.id.btnList1)
            flag = pictureStageOne[0];
        else if (v.getId() == R.id.btnList2)
            flag = pictureStageOne[1];
        else
            return;
        if (flag <= 0 || flag > 2)
            return;
    }
    // check whether to clear stage1

    public void resultView(View v) {
        if (flag == 1)   {
            //go to the Fail
            ImageView imvResult = new ImageView(Stage1_2Activity.this);
            imvResult.setImageResource(R.drawable.stage1_fail);
            new AlertDialog.Builder(Stage1_2Activity.this)
                    .setIcon(R.drawable.icn_score)
                    .setTitle("失敗了...")
                    .setView(imvResult)
                    .setPositiveButton("重來一次", null)
                    .show();
        }
        else {
            //go to the Success
            ImageView imvResult = new ImageView(Stage1_2Activity.this);
            imvResult.setImageResource(R.drawable.stage1_success);
            new AlertDialog.Builder(Stage1_2Activity.this)
                    .setIcon(R.drawable.icn_score)
                    .setTitle("成功了!!")
                    .setView(imvResult)
                    .setPositiveButton("回到地圖", null)
                    .show();
        }
    }
}
