package com.dashkasystems.testapp1.Vocalizing;


import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;

import com.dashkasystems.testapp1.Declension.Collocation;
import com.dashkasystems.testapp1.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

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

        aMap.put("Книг", R.raw.bookgp);
        aMap.put("Книги", R.raw.bookgs);
        aMap.put("Кукла", R.raw.doll);
        aMap.put("Куклы", R.raw.dollgs);
        aMap.put("Кукол", R.raw.dollgp);
        aMap.put("Лошадка", R.raw.horsetoy);
        aMap.put("Лошадки", R.raw.horsetoygs);
        aMap.put("Лошадок", R.raw.horsetoygp);
        aMap.put("Машинка", R.raw.cartoy);
        aMap.put("Машинки", R.raw.cartoygs);
        aMap.put("Машинок", R.raw.cartoygp);
        aMap.put("Мишек", R.raw.beartoygp);
        aMap.put("Мишка", R.raw.beartoy);
        aMap.put("Мишки", R.raw.beartoygs);
        aMap.put("Мяч", R.raw.ball);
        aMap.put("Мяча", R.raw.ballgs);
        aMap.put("Мячей", R.raw.ballgp);
        aMap.put("Пирамидка", R.raw.pyramid);
        aMap.put("Пирамидки", R.raw.pyramidp);
        aMap.put("Пирамидок", R.raw.pyramidgp);
        aMap.put("Самолёт", R.raw.flight);
        aMap.put("Самолётов", R.raw.flightgp);
        aMap.put("Черепашка", R.raw.turtle);
        aMap.put("Черепашек", R.raw.turtlegp);
        aMap.put("Юла", R.raw.yule);
        aMap.put("Юлы", R.raw.yuleg);

        aMap.put("Звезда", R.raw.star);
        aMap.put("Квадрат", R.raw.square);
        aMap.put("Круг", R.raw.circle);
        aMap.put("Прямоугольник", R.raw.rectangle);
        aMap.put("Треугольник", R.raw.triangle);
        aMap.put("Шестиугольник", R.raw.hexagon);

        aMap.put("Белый", R.raw.white);
        aMap.put("Белая", R.raw.whitef);
        aMap.put("Жёлтый", R.raw.yellow);
        aMap.put("Жёлтая", R.raw.yellowf);
        aMap.put("Зелёный", R.raw.green);
        aMap.put("Зелёная", R.raw.greenf);
        aMap.put("Красная", R.raw.redf);
        aMap.put("Красный", R.raw.red);
        aMap.put("Оранжевая", R.raw.orangef);
        aMap.put("Оранжевый", R.raw.orange);
        aMap.put("Синий", R.raw.blue);
        aMap.put("Синяя", R.raw.bluef);

        aMap.put("Один", R.raw.one);
        aMap.put("Одна", R.raw.onef);
        aMap.put("Два", R.raw.two);
        aMap.put("Две", R.raw.twof);
        aMap.put("Три", R.raw.three);
        aMap.put("Четыре", R.raw.four);
        aMap.put("Пять", R.raw.five);
        aMap.put("Шесть", R.raw.six);
        aMap.put("Семь", R.raw.seven);
        aMap.put("Восемь", R.raw.eight);
        aMap.put("Девять", R.raw.nine);
        aMap.put("Десять", R.raw.ten);
        aMap.put("Одиннадцать", R.raw.eleven);
        aMap.put("Двенадцать", R.raw.twelve);
        aMap.put("Тринадцать", R.raw.thirteen);

        wordMap = Collections.unmodifiableMap(aMap);
    }

    public static void vocalizeWord(String word, Context context, MediaPlayer.OnCompletionListener completionListener) {
        int res = getSoundResForWord(word);
        if (res != -1) {
            playSound(res, context, completionListener);
        }
    }

    private static void playSound(@RawRes int soundRes, Context context, MediaPlayer.OnCompletionListener completionListener) {
        MediaPlayer mPlayer = MediaPlayer.create(context, soundRes);
        mPlayer.setOnCompletionListener(completionListener);
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
