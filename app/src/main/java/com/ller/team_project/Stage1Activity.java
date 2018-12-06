package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage1Activity extends AppCompatActivity {

    private ImageButton btnList1, btnList2, btnObjectCake, btnObjectEraser;
    int[] pictureStageOne = new int[]{0, 0};
    int[] btnStage1Gone = new int[]{0, 0};          //GONE=1
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

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("pictureGone1")) {
                btnStage1Gone = bundle.getIntArray("pictureGone1");
                whetherToSetGone();
            }
            if (bundle.containsKey("musicStopIndex")) {
                afterActivityIndex = bundle.getInt("musicStopIndex");
            }
            if (bundle.containsKey("musicIndex")) {
                index = bundle.getInt("musicIndex");
            }
            if (bundle.containsKey("pictureTag1")) {
                pictureStageOne = bundle.getIntArray("pictureTag1");
                assert pictureStageOne != null;
                setImage();
            }
        }

        if (afterActivityIndex == 0) {
            playMedia();
        }
    }

    // whether to shut down the music when leaving this Activity
    @Override
    protected void onPause() {
        super.onPause();

        if (afterActivityIndex == 0) {
            MediaPlayer mp = GameMediaController.getStage1(this);
            mp.stop();
            mp.prepareAsync();
        }
    }

    // go to the GameSetActivity
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 1;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // back to the MapActivity
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putIntArray("pictureGone1", btnStage1Gone);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // switch the stage to the right side
    public void changePage(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, Stage1_2Activity.class);

        afterActivityIndex = 1;
        Bundle bundle = new Bundle();
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

    // to get the above object and set the list's image
    public void changeButtomListEvent(View v) {
        if (v.getId() == R.id.btnObjectEraser) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.erase);
                pictureStageOne[0] = 1;
                btnObjectEraser.setVisibility(View.GONE);
                btnStage1Gone[0] = 1;

            } else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.erase);
                pictureStageOne[1] = 1;
                btnObjectEraser.setVisibility(View.GONE);
                btnStage1Gone[0]= 1;
            }
        } else if (v.getId() == R.id.btnObjectCake) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.cake);
                pictureStageOne[0] = 2;
                btnObjectCake.setVisibility(View.GONE);
                btnStage1Gone[1] = 1;
            } else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.cake);
                pictureStageOne[1] = 2;
                btnObjectCake.setVisibility(View.GONE);
                btnStage1Gone[1] = 1;
            }
        }
    }

    // check whether to clear stage1
    public void resultView(View v) {
        int flag;
        if (v.getId() == R.id.btnList1)
            flag = pictureStageOne[0];
        else if (v.getId() == R.id.btnList2)
            flag = pictureStageOne[1];
        else
            return;
        if (flag <= 0 || flag > 2)
            return;

        if (flag == 1)   {
            //go to the Fail
            ImageView imvResult = new ImageView(Stage1Activity.this);
            imvResult.setImageResource(R.drawable.stage1_fail);
            new AlertDialog.Builder(Stage1Activity.this)
                    .setIcon(R.drawable.icn_score)
                    .setTitle("失敗了...")
                    .setView(imvResult)
                    .setPositiveButton("ok", null)
                    .show();
        }
        else {
            //go to the Success
            ImageView imvResult = new ImageView(Stage1Activity.this);
            imvResult.setImageResource(R.drawable.stage1_success);
            new AlertDialog.Builder(Stage1Activity.this)
                    .setIcon(R.drawable.icn_score)
                    .setTitle("成功了!!")
                    .setView(imvResult)
                    .setPositiveButton("ok", null)
                    .show();
        }
    }

    // check whether to set the attributes of the above object's imageView to GONE
    public void whetherToSetGone() {
        if(btnStage1Gone[0]==1){
            btnObjectEraser.setVisibility(View.GONE);
        }
        if(btnStage1Gone[1]==1) {
            btnObjectCake.setVisibility(View.GONE);
        }
    }

    public void setFlagOfResult(View v) {
        int flag;
        if (v.getId() == R.id.btnList1)
            flag = pictureStageOne[0];
        else if (v.getId() == R.id.btnList2)
            flag = pictureStageOne[1];
        else
            return;
        if (flag <= 0 || flag > 2)
            return;
    }
}


