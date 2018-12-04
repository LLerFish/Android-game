package com.ller.team_project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;

import java.util.HashMap;
import java.util.Map;

@Deprecated
public class MediaPlayerStock {
    // 不太好預測使用時機比較會用
   // Object obj = new String("I am tingyu");
    private static final Map<Integer, MediaPlayer> STOCK = new HashMap<>();     //class/<generic>

    public static MediaPlayer createMediaPlayer(Integer key, Context context, int resId) {
        MediaPlayer mp = MediaPlayer.create(context, resId);
        STOCK.put(key, mp);
        return mp;
    }//thread

    public static MediaPlayer getMediaPlayer(Integer key){
        return STOCK.get(key);
    }

    private MediaPlayerStock(){}//construct

    // 1 -> 31
    // 2 -> 28
    // 3 -> 32
    // 4 -> 30

}
