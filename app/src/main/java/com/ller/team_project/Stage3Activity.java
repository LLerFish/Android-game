package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Stage3Activity extends AppCompatActivity {
    private ImageButton btnList1, btnList2, btnList3, btnObjectFloorCard;
    int[] pictureStageThree = new int[]{0, 0, 0};
    int[] btnStage3Gone = new int[]{0, 0, 0};          //GONE=1
    private int afterActivityIndex = 0;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage3);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnObjectFloorCard = findViewById(R.id.btnObjectFloorCard);
        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);
        btnList3 = findViewById(R.id.btnList3);

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {  //判斷解包
            if(bundle.containsKey("pictureGone3")){
                btnStage3Gone = bundle.getIntArray("pictureGone3");
                whetherToSetGone();
            }

            if(bundle.containsKey("musicIndex")) {
                index = bundle.getInt("musicIndex");
            }
            if(bundle.containsKey("musicStopIndex")) {
                afterActivityIndex = bundle.getInt("musicStopIndex");
            }

            if (bundle.containsKey("pictureTag3")) {
                pictureStageThree = bundle.getIntArray("pictureTag3");
                assert pictureStageThree != null;
                setImage();
            }
        }

        if (afterActivityIndex == 0) {
            playMedia();
        }
    }

    // check whether to set the attributes of the above object's imageView to GONE
    public void whetherToSetGone() {
        if(btnStage3Gone[0]==1){
            btnObjectFloorCard.setVisibility(View.GONE);
        }
    }

    public void setImage(){
        if (pictureStageThree[0] == 1) {
            btnList1.setImageResource(R.drawable.floor_card);
        } else if (pictureStageThree[1] == 1) {
            btnList2.setImageResource(R.drawable.floor_card);
        } else if (pictureStageThree[2] == 1) {
            btnList3.setImageResource(R.drawable.floor_card);
        }
        if (pictureStageThree[0] == 2) {
            btnList1.setImageResource(R.drawable.poster_list_card);
        } else if (pictureStageThree[1] == 2) {
            btnList2.setImageResource(R.drawable.poster_list_card);
        } else if (pictureStageThree[2] == 2) {
            btnList3.setImageResource(R.drawable.poster_list_card);
        }
        if (pictureStageThree[0] == 3) {
            btnList1.setImageResource(R.drawable.closet_card);
        } else if (pictureStageThree[1] == 3) {
            btnList2.setImageResource(R.drawable.closet_card);
        } else if (pictureStageThree[2] == 3) {
            btnList3.setImageResource(R.drawable.closet_card);
        }
    }

    // go to the GameSetActivity
    public void setting(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage3Activity.this, GameSetActivity.class);

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

    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage3(this);
        mp.start();
        mp.setLooping(true);
    }

    // back to the MapActivity
    public void back(View v) {
        Intent intent = new Intent();
        intent.setClass(Stage3Activity.this, MapActivity.class);

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
        intent.setClass(Stage3Activity.this, Stage3_2Activity.class);

        afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag3", pictureStageThree);
        bundle.putIntArray("pictureGone3", btnStage3Gone);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // to get the above object and set the list's image
    public void changeButtomListEvent(View v) {
        if (v.getId() == R.id.btnObjectFloorCard) {
            if (pictureStageThree[0] == 0) {
                btnList1.setImageResource(R.drawable.floor_card);
                pictureStageThree[0] = 1;
                btnObjectFloorCard.setVisibility(View.GONE);
                btnStage3Gone[0] = 1;

            } else if (pictureStageThree[1] == 0) {
                btnList2.setImageResource(R.drawable.floor_card);
                pictureStageThree[1] = 1;
                btnObjectFloorCard.setVisibility(View.GONE);
                btnStage3Gone[0]= 1;
            }
        }
    }
}
