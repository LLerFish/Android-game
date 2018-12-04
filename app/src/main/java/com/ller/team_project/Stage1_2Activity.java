package com.ller.team_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

public class Stage1_2Activity extends AppCompatActivity {

    int[] pictureStageOne = new int[]{0, 0, 0};
    private ImageButton btnList1, btnList2;
    private int afterActivityIndex, index;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stage1_2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        btnList1 = findViewById(R.id.btnList1);
        btnList2 = findViewById(R.id.btnList2);

        Bundle bundle = getIntent().getExtras();
        //Check bundle
        if (bundle != null) {
            pictureStageOne = bundle.getIntArray("pictureTag1");
            afterActivityIndex = bundle.getInt("musicStopIndex");
            assert pictureStageOne != null;
            if (pictureStageOne[0] == 1) {
                btnList1.setImageResource(R.drawable.erase);
            }
            else if (pictureStageOne[1] == 1) {
                btnList2.setImageResource(R.drawable.erase);
            }
            if (pictureStageOne[0] == 2) {
                btnList1.setImageResource(R.drawable.cake);
                // picture[0] = 2;
            }
            else if (pictureStageOne[1] == 2) {
                btnList2.setImageResource(R.drawable.cake);
                // picture[1] = 2;
            }
        }

        // Play music
        playMedia();
    }

    // Go setting page
    public void setting(View v) {
        Intent intent = new Intent(this, GameSetActivity.class);

        Bundle bundle = new Bundle();
        index = 1;
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    // Go back to map
    public void back(View v) {
        Intent intent = new Intent(this, MapActivity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.stop();
        mp.prepareAsync();
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // Go to left-map
    public void changePage(View v) {
        Intent intent = new Intent(this, Stage1Activity.class);

        Bundle bundle = new Bundle();
        afterActivityIndex = 1;
        bundle.putIntArray("pictureTag1", pictureStageOne);
        bundle.putInt("musicStopIndex", afterActivityIndex);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    // Play music
    public void playMedia() {
        MediaPlayer mp = GameMediaController.getStage1(this);
        mp.start();
        mp.setLooping(true);
    }
}
