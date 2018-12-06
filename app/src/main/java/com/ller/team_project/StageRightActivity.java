package com.ller.team_project;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public abstract class StageRightActivity extends StageActivity {

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);

        float scale = getDpScale();
        int len = roundScale(64, scale);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(len, len);
        p.addRule(RelativeLayout.CENTER_VERTICAL);
        p.addRule(RelativeLayout.ALIGN_PARENT_START);

        ImageButton btnLeft = new ImageButton(this);
        btnLeft.setLayoutParams(p);
        btnLeft.setBackgroundColor(Color.TRANSPARENT);
        btnLeft.setImageResource(R.drawable.left);
        btnLeft.setOnClickListener(v -> finish());
        getRootLayout().addView(btnLeft);
    }
}
