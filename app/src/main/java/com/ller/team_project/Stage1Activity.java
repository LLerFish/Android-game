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
    int[] pictureStageOne = new int[]{0, 0, 0};
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

        Bundle bundle = this.getIntent().getExtras();          // 判斷解包
        if (bundle != null) {
            pictureStageOne = bundle.getIntArray("pictureTag1");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            index = bundle.getInt("musicIndex");
            if (pictureStageOne[0] == 1) {
                btnList1.setImageResource(R.drawable.erase);
               // picture[0] = 1;
            } else if (pictureStageOne[1] == 1) {
                btnList2.setImageResource(R.drawable.erase);
              //  picture[1] = 1;
            }
            if (pictureStageOne[0] == 2) {
                btnList1.setImageResource(R.drawable.cake);
              //  picture[0] = 2;
            } else if (pictureStageOne[1] == 2) {
                btnList2.setImageResource(R.drawable.cake);
               // picture[1] = 2;
            }
        }
        if (afterActivityIndex == 0) {
            playMedia();        //進入撥放音樂
        }
    }

    // 關掉這個activity時是否關掉音樂
    @Override
    protected void onPause() {
        super.onPause();

        if (afterActivityIndex == 0) {
            MediaPlayer mp = GameMediaController.getStage1(this);
            mp.stop();
            mp.prepareAsync();
        }
    }

    // 進入設定畫面
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, GameSetActivity.class);

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

    // 回到地圖
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // 切到右半邊
    public void changePage(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage1Activity.this, Stage1_2Activity.class);

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

    // 把上方物品拉到下面物品格子
    public void changeButtomListEvent(View v) {
        if (v.getId() == R.id.btnObjectEraser) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.erase);
                pictureStageOne[0] = 1;
                btnObjectEraser.setVisibility(View.GONE);
            } else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.erase);
                pictureStageOne[1] = 1;
                btnObjectEraser.setVisibility(View.GONE);
            }
        } else if (v.getId() == R.id.btnObjectCake) {
            if (pictureStageOne[0] == 0) {
                btnList1.setImageResource(R.drawable.cake);
                pictureStageOne[0] = 2;
                btnObjectCake.setVisibility(View.GONE);
            } else if (pictureStageOne[1] == 0) {
                btnList2.setImageResource(R.drawable.cake);
                pictureStageOne[1] = 2;
                btnObjectCake.setVisibility(View.GONE);
            }
        }
    }

    // 判斷是否通關
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
        Intent intent = new Intent();
        if (flag == 1) //跳到失敗頁面
            intent.setClass(Stage1Activity.this, ShowResult2Activity.class);
        else         //跳到成功頁面
            intent.setClass(Stage1Activity.this, ShowResultActivity.class);
        startActivity(intent);

        Stage1Activity.this.finish();
    }

    //判斷是否把道具的view設定為GONE
    public void whetherToSetGone(View v) {
        // 判斷哪個關卡進來,給定一個參數判斷btn的數量,在把參數用迴圈方式去判斷是否取得物件跟設定為GONE...etc
        //for (int i=0; i<num; i++) {

       // }
    }
}


