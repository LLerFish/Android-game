package com.ller.team_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage2_2Activity extends AppCompatActivity {

    private ImageButton btnList1, btnList2, btnList3, btnFiftyDollar, btnTenDollar;
    private int[] pictureStageTwo = new int[]{0, 0, 0};
    private int[] flag = new int[]{0, 0, 0};
    private int afterActivityIndex, index, door = 0;
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

        Bundle bundle = getIntent().getExtras();
        // Check bundle
        if (bundle != null) {
            pictureStageTwo = bundle.getIntArray("pictureTag");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            flag = bundle.getIntArray("coinPlace");
            assert pictureStageTwo != null;
            if (pictureStageTwo[0] == 1) {
                btnList1.setImageResource(R.drawable.one);
            }
            else if (pictureStageTwo[1] == 1) {
                btnList2.setImageResource(R.drawable.one);
            }
            else if (pictureStageTwo[2] == 1) {
                btnList3.setImageResource(R.drawable.one);
            }
            if (pictureStageTwo[0] == 2) {
                btnList1.setImageResource(R.drawable.ten);
            }
            else if (pictureStageTwo[1] == 2) {
                btnList2.setImageResource(R.drawable.ten);
            }
            else if (pictureStageTwo[2] == 2) {
                btnList3.setImageResource(R.drawable.ten);
            }
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

    // Goto left side
    public void changePage(View v) {
        // afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        // bundle.putInt("musicStopIndex", afterActivityIndex);
        bundle.putIntArray("coinPlace", flag);

        Intent intent = new Intent(this, Stage2Activity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Pull objects to below blocks
    public void changeButtonListEvent(View v) {
        if (v.getId() == R.id.btnTenDollar) {
            if (pictureStageTwo[0] == 0) {
                btnList1.setImageResource(R.drawable.ten);
                pictureStageTwo[0] = 2;
                btnTenDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[1] == 0) {
                btnList2.setImageResource(R.drawable.ten);
                pictureStageTwo[1] = 2;
                btnTenDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[2] == 0) {
                btnList3.setImageResource(R.drawable.ten);
                pictureStageTwo[2] = 2;
                btnTenDollar.setVisibility(View.GONE);
            }
        }
        else if (v.getId() == R.id.btnFiftyDollar) {
            if (pictureStageTwo[0] == 0) {
                btnList1.setImageResource(R.drawable.fifty);
                pictureStageTwo[0] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[1] == 0) {
                btnList2.setImageResource(R.drawable.fifty);
                pictureStageTwo[1] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            }
            else if (pictureStageTwo[2] == 0) {
                btnList3.setImageResource(R.drawable.fifty);
                pictureStageTwo[2] = 3;
                btnFiftyDollar.setVisibility(View.GONE);
            }
        }
    }

    public void changeButtonOpenDoorEvent(View v) {
        if (v.getId() == R.id.btnDoor) {
            if (door == 0) {
                background2.setImageResource(R.drawable.stage2_2_open);
                door = 1;
            }
            else if (door == 1) {
                background2.setImageResource(R.drawable.stage2_2);
                door = 0;
            }
        }
    }

    public void openDoor(View v) {
        background2.setImageResource(R.drawable.stage2_2_open);
        btnFiftyDollar.setVisibility(View.VISIBLE);
    }
}