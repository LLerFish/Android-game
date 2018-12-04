package com.ller.team_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage2_2Activity extends AppCompatActivity {
    private ImageButton btnList1, btnList2, btnList3, btnFiftyDollar, btnTenDollar;
    int[] pictureStageTwo = new int[]{0, 0, 0};
    int[] flag = new int[]{0, 0, 0};
    private int afterActivityIndex, index, door=0;
    private ImageView background2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2_2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnTenDollar = findViewById(R.id.btnTenDollar);
        btnFiftyDollar = findViewById(R.id.btnFiftyDollar);
        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);
        btnList3 = findViewById(R.id.btnList3);
        background2 = findViewById(R.id.background2);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {  //判斷解包
            pictureStageTwo = bundle.getIntArray("pictureTag");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            flag = bundle.getIntArray("coinPlace");
            assert pictureStageTwo != null;
            if (pictureStageTwo[0] == 1) {
                btnList1.setImageResource(R.drawable.one);
            } else if (pictureStageTwo[1] == 1) {
                btnList2.setImageResource(R.drawable.one);
            } else if (pictureStageTwo[2] == 1) {
                btnList3.setImageResource(R.drawable.one);
            }
            if (pictureStageTwo[0] == 2) {
                btnList1.setImageResource(R.drawable.ten);
            } else if (pictureStageTwo[1] == 2) {
                btnList2.setImageResource(R.drawable.ten);
            } else if (pictureStageTwo[2] == 2) {
                btnList3.setImageResource(R.drawable.ten);
            }
            if (pictureStageTwo[0] == 3) {
                btnList1.setImageResource(R.drawable.fifty);
            } else if (pictureStageTwo[1] == 3) {
                btnList2.setImageResource(R.drawable.fifty);
            } else if (pictureStageTwo[2] == 3) {
                btnList3.setImageResource(R.drawable.fifty);
            }
        }
    }

    // 進入設定畫面
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage2_2Activity.this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 2;
        afterActivityIndex = 2;
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // 回到地圖
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage2_2Activity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag2", pictureStageTwo);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // 切到左半邊
    public void changePage(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage2_2Activity.this, Stage2Activity.class);

        //afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        // bundle.putInt("musicStopIndex", afterActivityIndex);
        bundle.putIntArray("coinPlace", flag);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // 把上方物品拉到下面物品格子
    public void changeButtomListEvent(View v) {
        if (v.getId() == R.id.btnTenDollar) {
            if (pictureStageTwo[0] == 0) {
                btnList1.setImageResource(R.drawable.ten);
                pictureStageTwo[0] = 2;
                btnTenDollar.setVisibility(View.GONE);
            } else if (pictureStageTwo[1] == 0) {
                btnList2.setImageResource(R.drawable.ten);
                pictureStageTwo[1] = 2;
                btnTenDollar.setVisibility(View.GONE);
            } else if (pictureStageTwo[2] == 0) {
                btnList3.setImageResource(R.drawable.ten);
                pictureStageTwo[2] = 2;
                btnTenDollar.setVisibility(View.GONE);
            }
        }
        if (v.getId() == R.id.btnFiftyDollar) {
            if (pictureStageTwo[0] == 0) {
                btnList1.setImageResource(R.drawable.fifty);
                pictureStageTwo[0] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            } else if (pictureStageTwo[1] == 0) {
                btnList2.setImageResource(R.drawable.fifty);
                pictureStageTwo[1] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            } else if (pictureStageTwo[2] == 0) {
                btnList3.setImageResource(R.drawable.fifty);
                pictureStageTwo[2] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            }
        }
    }
    public void changeButtomOpendoorEvent(View v){
        if (v.getId() == R.id.btnDoor) {
            if(door == 0) {
                background2.setImageResource(R.drawable.stage2_2_open);
                door=1;
            }
            else if(door == 1){
                background2.setImageResource(R.drawable.stage2_2);
                door=0;
            }
        }
    }

    public void openDoor(View v){
        background2.setImageResource(R.drawable.stage2_2_open);
        btnFiftyDollar.setVisibility(View.VISIBLE);
    }
}