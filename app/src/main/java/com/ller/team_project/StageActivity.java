package com.ller.team_project;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static android.widget.ImageView.ScaleType.FIT_CENTER;
import static android.widget.RelativeLayout.ALIGN_PARENT_BOTTOM;

public abstract class StageActivity extends GameActivity {

    protected static final String BLOCK_CONTENT_PREFERENCE = "block_contents";
    private static final int BLOCK_CONSTANT = 19970731;
    private static final float[] startMar = {0.03f, 0.25f, 0.49f, 0.72f, 0.96f};

    protected int[] blocks = new int[5];

    protected static int[] toIntArray(String data) {
        if (data.equals("")) { return new int[]{}; }
        String[] strings = data.split(",");
        int[] array = new int[strings.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = Integer.parseInt(strings[i]);
        }
        return array;
    }

    protected static String parseIntArray(int[] array) {
        if (array.length == 0) { return ""; }
        if (array.length == 1) { return Integer.toString(array[0]); }
        StringBuilder sb = new StringBuilder(60);
        sb.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            sb.append(',').append(array[i]);
        }
        return sb.toString();
    }

    private ImageButton addBlockList(int pos, OnClickListener listener, ConstraintLayout parent) {

        float scale = getDpScale();
        int len = roundScale(64, scale);
        ConstraintLayout.LayoutParams p = new ConstraintLayout.LayoutParams(len, len);

        ImageButton btnBlock = new ImageButton(this);
        btnBlock.setLayoutParams(p);
        btnBlock.setBackgroundColor(Color.TRANSPARENT);
        btnBlock.setOnClickListener(listener);
        btnBlock.setId(getIdOfBlock(pos));
        btnBlock.setScaleType(FIT_CENTER);
        parent.addView(btnBlock);

        ConstraintSet c = new ConstraintSet();
        c.clone(parent);
        c.connect(btnBlock.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP);
        c.connect(btnBlock.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM);
        c.connect(btnBlock.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START);
        c.connect(btnBlock.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
        c.setHorizontalBias(btnBlock.getId(), startMar[pos]);
        c.applyTo(parent);
        return btnBlock;
    }

    private String getBlockContentPreferenceKey() {
        return "stage" + stage();
    }

    protected int getIdOfBlock(int pos) {
        return BLOCK_CONSTANT + pos;
    }

    private int[] objectIds() {
        switch (stage()) {
        case 1:
            return new int[]{R.id.objEraser, R.id.objCake};
        case 2:
            return new int[]{R.id.objCoin1, R.id.objCoin10, R.id.objCoin50};
        }
        throw new RuntimeException("Unknown stage: " + stage());
    }

    @Override
    protected void onCreate(Bundle saved) {
        super.onCreate(saved);

        // Add block buttons below
        float scale = getDpScale();
        final int h = roundScale(60, scale);
        RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, h);
        p.setMargins(0, 0, 0, roundScale(7.5f, scale));
        p.addRule(ALIGN_PARENT_BOTTOM);
        ConstraintLayout layout = new ConstraintLayout(this);
        layout.setLayoutParams(p);
        OnClickListener listener = v -> onBlockClick(v.getId() - BLOCK_CONSTANT);
        for (int i = 0; i < 5; i++) { addBlockList(i, listener, layout); }
        getRootLayout().addView(layout);

        // Add objects into scene
        listener = this::onObjectCollect;
        for (int id : objectIds()) {
            View v = findViewById(id);
            if (v != null) { v.setOnClickListener(listener); }
        }
    }

    private void onObjectCollect(View view) {
        // Remove view from scene
        ((ViewGroup) view.getParent()).removeView(view);
        // Add view into block
        pullObjectToBlock(view);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Update block contents
        String contents = getSharedPreferences(BLOCK_CONTENT_PREFERENCE, MODE_PRIVATE).getString(getBlockContentPreferenceKey(), "");
        int[] array = toIntArray(contents);
        System.arraycopy(array, 0, blocks, 0, array.length);
        updateBlockList();
    }

    private int pullObjectToBlock(View object) {
        for (int i = 0; i < 5; i++) {
            if (blocks[i] == 0) {
                blocks[i] = object.getId();
                ImageButton block = findViewById(getIdOfBlock(i));
                block.setImageDrawable(((ImageButton) object).getDrawable());
                saveData();
                return i;
            }
        }
        throw new RuntimeException("Full.");
    }

    private void saveData() {
        getSharedPreferences(BLOCK_CONTENT_PREFERENCE, MODE_PRIVATE).edit()
                                                                    .putString(getBlockContentPreferenceKey(), parseIntArray(blocks))
                                                                    .apply();
    }

    private int toImageId(int viewId) {
        switch (viewId) {
        case R.id.objCake:
            return R.drawable.cake;
        case R.id.objEraser:
            return R.drawable.eraser;
        case R.id.objCoin1:
            return R.drawable.one;
        case R.id.objCoin10:
            return R.drawable.ten;
        case R.id.objCoin50:
            return R.drawable.fifty;
        }
        return 0;
    }

    private void updateBlockList() {
        ImageButton btnBlock;
        for (int pos = 0; pos < 5; pos++) {
            if (blocks[pos] == 0) { continue; }
            btnBlock = findViewById(getIdOfBlock(pos));
            btnBlock.setImageResource(toImageId(blocks[pos]));
            ImageButton temp = findViewById(blocks[pos]);
            if (temp != null) {
                ((ViewGroup) temp.getParent()).removeView(temp);
            }
        }
    }

    protected abstract void onBlockClick(int pos);

    protected abstract int stage();
}
