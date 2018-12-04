package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage2Activity extends AppCompatActivity {

    private ImageButton btnList1, btnList2, btnList3, btnOneDollar, btnCoinGate, btnDrinkGate;
    private int[] pictureStageTwo = new int[]{0, 0, 0};
    // Check which coin is selected
    private int[] flag = new int[]{0, 0, 0};
    private int drinkgate = 0, afterActivityIndex = 2;
    private int index;
    private ImageView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnOneDollar = findViewById(R.id.btnOneDollar);
        btnCoinGate = findViewById(R.id.btnCoinGate);
        btnDrinkGate = findViewById(R.id.btnDrinkGate);
        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);
        btnList3 = findViewById(R.id.btnList3);
        background = findViewById(R.id.background);

        Bundle bundle = getIntent().getExtras();
        // Check bundle
        if (bundle != null) {
            pictureStageTwo = bundle.getIntArray("pictureTag");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            flag = bundle.getIntArray("coinPlace");
            if (pictureStageTwo[0] == 1) {
                btnList1.setImageResource(R.drawable.one);
            }
            else if (pictureStageTwo[1] == 1) {
                btnList2.setImageResource(R.drawable.one);
            }
            else if (pictureStageTwo[2] == 1) {
                btnList3.setImageResource(R.drawable.one);
            }

            // What ??
            if (pictureStageTwo[0] == 2) {
                btnList1.setImageResource(R.drawable.ten);
            }
            else if (pictureStageTwo[1] == 2) {
                btnList2.setImageResource(R.drawable.ten);
            }
            else if (pictureStageTwo[2] == 2) {
                btnList3.setImageResource(R.drawable.ten);
            }

            // What is this ???
            if (pictureStageTwo[0] == 3) {
                btnList1.setImageResource(R.drawable.fifty);
            }
            else if (pictureStageTwo[1] == 3) {
                btnList2.setImageResource(R.drawable.fifty);
            }
            else if (pictureStageTwo[2] == 3) {
                btnList3.setImageResource(R.drawable.fifty);
            }
        }

        // Play music
        playMedia();
    }

    // Goto setting page
    public void setting(View v) {
        Bundle bundle = new Bundle();
        index = 2;
        afterActivityIndex = 2;
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        Intent intent = new Intent(this, GameSetActivity.class);
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

        Intent intent = new Intent(this, Stage2_2Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage2(this);
        mp.start();
        mp.setLooping(true);
    }

    // Pull objects to below blocks
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
    }

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
