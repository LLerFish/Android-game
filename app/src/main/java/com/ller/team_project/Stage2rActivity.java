package com.ller.team_project;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Stage2rActivity extends StageRightActivity implements View.OnClickListener {

    private boolean doorOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.btnDoor).setOnClickListener(this);
    }

    @Override
    protected int defaultContentView() {
        return R.layout.activity_stage2r;
    }

    @Override
    protected int stage() {
        return 2;
    }

    @Override
    protected void onBlockClick(int pos) {

    }

    // Goto setting page
    /*public void setting(View v) {
        Bundle bundle = new Bundle();
        index = 2;
        afterActivityIndex = 2;
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        bundle.putInt("musicIndex", index);
        bundle.putInt("musicStopIndex", afterActivityIndex);

        Intent intent = new Intent(this, GameSettingActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

    // Go back to map
    /*public void back(View v) {
        Bundle bundle = new Bundle();
        afterActivityIndex = 0;
        bundle.putIntArray("pictureTag2", pictureStageTwo);

        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

    // Goto left side
    /*public void changePage(View v) {
        // afterActivityIndex = 1;
        Bundle bundle = new Bundle();
        bundle.putIntArray("pictureTag2", pictureStageTwo);
        // bundle.putInt("musicStopIndex", afterActivityIndex);
        bundle.putIntArray("coinPlace", flag);

        Intent intent = new Intent(this, Stage2lActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }*/

    // Pull objects to below blocks
    /*public void changeButtonListEvent(View v) {
        if (v.getId() == R.id.objCoin10) {
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
        else if (v.getId() == R.id.objCoin50) {
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
    }*/

    private void toggleDoor(boolean open) {
        ImageView bg = findViewById(R.id.imgBackground);
        bg.setImageResource(open ? R.drawable.stage2_2_open : R.drawable.stage2_2);
        ImageButton objCoin50 = findViewById(R.id.objCoin50);
        if (objCoin50 != null) { objCoin50.setVisibility(open ? View.VISIBLE : View.GONE); }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
        case R.id.btnDoor:
            doorOpen = !doorOpen;
            toggleDoor(doorOpen);
            break;
        }
    }
}