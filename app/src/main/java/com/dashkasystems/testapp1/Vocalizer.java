package com.dashkasystems.testapp1;


import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pandasystems on 4/12/17.
 */

public class Vocalizer {
    private static final Map<String, Integer> wordMap;
    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("Волк", R.raw.wolf);
        aMap.put("Заяц", R.raw.rabbit);
        aMap.put("Кот", R.raw.cat);
        aMap.put("Курица", R.raw.hen);
        aMap.put("Лев", R.raw.lion);
        aMap.put("Лиса", R.raw.fox);
        aMap.put("Лошадь", R.raw.horse);
        aMap.put("Медведь", R.raw.bear);
        aMap.put("Мышь", R.raw.mouse);
        aMap.put("Олень", R.raw.deer);
        aMap.put("Петух", R.raw.rooster);
        aMap.put("Свинья", R.raw.pig);
        aMap.put("Собака", R.raw.dog);
        wordMap = Collections.unmodifiableMap(aMap);
    }

    public static void vocalizeWord(String word, Context context) {
        int res = getSoundResForWord(word);
        if (res != -1) {
            playSound(res, context);
        }

    }

    public static void playSound(@RawRes int soundRes, Context context) {
        MediaPlayer mPlayer = MediaPlayer.create(context, soundRes);
        mPlayer.start();
    }

    private static @RawRes int getSoundResForWord(String word) {
        Integer res = wordMap.get(word);
        if (res != null) {
            return res;
        }
        return -1;
    }



}
