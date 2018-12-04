package com.ller.team_project;

import android.content.Context;
import android.media.MediaPlayer;

public class GameMediaController {

    private static MediaPlayer main, stage1, stage2, stage3;

    private GameMediaController() {
    }

    public static MediaPlayer getMain(Context context) {
        if (main == null) {
            main = MediaPlayer.create(context, R.raw.music01);
        }
        return main;
    }

    public static MediaPlayer getStage1(Context context) {
        if (stage1 == null) {
            stage1 = MediaPlayer.create(context, R.raw.music02);
        }
        return stage1;
    }

    public static MediaPlayer getStage2(Context context) {
        if (stage2 == null) {
            stage2 = MediaPlayer.create(context, R.raw.music03);
        }
        return stage2;
    }

    public static MediaPlayer getStage3(Context context) {
        if (stage3 == null) {
            stage3 = MediaPlayer.create(context, R.raw.music04);
        }
        return stage3;
    }
}
