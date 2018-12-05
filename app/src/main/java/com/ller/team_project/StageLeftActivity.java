package com.ller.team_project;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public abstract class StageLeftActivity extends StageActivity {

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);

        float scale = getDpScale();
        int len = roundScale(64, scale);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(len, len);
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        p.addRule(RelativeLayout.ALIGN_PARENT_END);

        ImageButton btnRight = new ImageButton(this);
        btnRight.setLayoutParams(p);
        btnRight.setBackgroundColor(Color.TRANSPARENT);
        btnRight.setImageResource(R.drawable.right);
        btnRight.setOnClickListener(v -> onSwitch());
        getRootLayout().addView(btnRight);
    }

    protected abstract void onSwitch();

}
