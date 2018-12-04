package com.ller.team_project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN;
import static android.widget.ImageView.ScaleType.FIT_CENTER;
import static android.widget.RelativeLayout.ALIGN_PARENT_END;
import static android.widget.RelativeLayout.ALIGN_PARENT_TOP;

public abstract class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(FLAG_FULLSCREEN, FLAG_FULLSCREEN);
        setContentView(defaultContentView());

        final float scale = getResources().getDisplayMetrics().density;
        final int imgLenInDp = (int) (72 * scale + .5f);
        final int imgMarInDp = (int) (12 * scale + .5f);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(imgLenInDp, imgLenInDp);
        p.addRule(ALIGN_PARENT_END);
        p.addRule(ALIGN_PARENT_TOP);
        p.setMargins(imgMarInDp, imgMarInDp, imgMarInDp, imgMarInDp);

        ImageButton imgBack = new ImageButton(this);
        imgBack.setImageResource(R.drawable.back);
        imgBack.setBackgroundColor(Color.TRANSPARENT);
        imgBack.setScaleType(FIT_CENTER);
        imgBack.setOnClickListener(v -> finish());
        imgBack.setLayoutParams(p);

        ViewGroup layout = findViewById(android.R.id.content);
        layout = (ViewGroup) layout.getChildAt(0);
        layout.addView(imgBack);
    }

    protected abstract int defaultContentView();

}
