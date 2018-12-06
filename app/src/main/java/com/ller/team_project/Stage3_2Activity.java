package com.ller.team_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Stage3_2Activity extends AppCompatActivity {
    private ImageButton btnList1, btnList2, btnList3, btnObjectPostCard, btnObjectClosetCard;
    int[] pictureStageThree = new int[]{0, 0, 0};
    int[] btnStage3Gone = new int[]{0, 0, 0};          //GONE=1
    private int afterActivityIndex = 0;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage3_2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnObjectPostCard = findViewById(R.id.btnObjectPostCard);
        btnObjectClosetCard = findViewById(R.id.btnObjectClosetCard);
        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);


    }

    // go to the GameSetActivity
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage3_2Activity.this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 3;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag3", pictureStageThree);
        bundle.putIntArray("pictureGone3", btnStage3Gone);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    // back to the MapActivity
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage3_2Activity.this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag3", pictureStageThree);
        bundle.putIntArray("pictureGone3", btnStage3Gone);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // switch the stage to the right side
    public void changePage(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage3_2Activity.this, Stage3Activity.class);

        afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag3", pictureStageThree);
        bundle.putIntArray("pictureGone3", btnStage3Gone);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // 把上方物品拉到下面物品格子
    public void changeButtomListEvent(View v) {
        if (v.getId() == R.id.btnObjectPostCard) {
            if (pictureStageThree[0] == 0) {
                btnList1.setImageResource(R.drawable.poster_list_card);
                pictureStageThree[0] = 2;
                btnObjectPostCard.setVisibility(View.GONE);
                btnStage3Gone[1] = 1;
            } else if (pictureStageThree[1] == 0) {
                btnList2.setImageResource(R.drawable.poster_list_card);
                pictureStageThree[1] = 2;
                btnObjectPostCard.setVisibility(View.GONE);
                btnStage3Gone[1] = 1;
            } else if (pictureStageThree[2] == 0) {
                btnList3.setImageResource(R.drawable.poster_list_card);
                pictureStageThree[2] = 2;
                btnObjectPostCard.setVisibility(View.GONE);
                btnStage3Gone[1] = 1;
            }
        }
        if (v.getId() == R.id.btnObjectClosetCard) {
            if (pictureStageThree[0] == 0) {
                btnList1.setImageResource(R.drawable.closet_card);
                pictureStageThree[0] = 3;
                btnObjectClosetCard.setVisibility(View.GONE);
                btnStage3Gone[2] = 1;
            } else if (pictureStageThree[1] == 0) {
                btnList2.setImageResource(R.drawable.closet_card);
                pictureStageThree[1] = 3;
                btnObjectClosetCard.setVisibility(View.GONE);
                btnStage3Gone[2] = 1;
            } else if (pictureStageThree[2] == 0) {
                btnList3.setImageResource(R.drawable.closet_card);
                pictureStageThree[2] = 3;
                btnObjectClosetCard.setVisibility(View.GONE);
                btnStage3Gone[2] = 1;
            }
        }
    }
}
