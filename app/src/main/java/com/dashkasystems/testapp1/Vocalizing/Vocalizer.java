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

        aMap.put("белый", R.raw.white);
        aMap.put("белая", R.raw.whitef);
        aMap.put("жёлтый", R.raw.yellow);
        aMap.put("жёлтая", R.raw.yellowf);
        aMap.put("зелёный", R.raw.green);
        aMap.put("зелёная", R.raw.greenf);
        aMap.put("красная", R.raw.redf);
        aMap.put("красный", R.raw.red);
        aMap.put("оранжевая", R.raw.orangef);
        aMap.put("оранжевый", R.raw.orange);
        aMap.put("синий", R.raw.blue);
        aMap.put("синяя", R.raw.bluef);

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

    private static final Map<String, Integer> sentenceMap;
    static {
        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("Кошка съела воробья и теперь не голодна.", R.raw.sentence_cat_ate_sparrow);
        aMap.put("Бабочка села на цветок.", R.raw.sentence_butterfly_on_flower);
        aMap.put("На высокой сосне сидит белка.", R.raw.sentence_squirel_on_hight);
        aMap.put("Снегири сидят на ветках.", R.raw.sentence_bullfinch_on_branches);
        aMap.put("Снегири прилетели и сели на ветки.", R.raw.sentence_bullfinches_arrived);
        aMap.put("На лугу растут цветы.", R.raw.sentence_flowers_on_meadow);
        aMap.put("Ярко светит солнце.", R.raw.sentence_sun_is_shining);
        aMap.put("Caша пошёл гулять на улицу.", R.raw.sasha_go_out);
        aMap.put("Начался дождь.", R.raw.sasha_rain_started);
        aMap.put("У мальчика не было зонта.", R.raw.sasha_havent_umberella);
        aMap.put("Он промок", R.raw.sasha_wet);
        aMap.put("Саша вернулся домой мокрым", R.raw.sasha_came_back);
        sentenceMap = Collections.unmodifiableMap(aMap);
    }

    public static void vocalizeSentence(String sentence, Context context, MediaPlayer.OnCompletionListener completionListener) {
        int res = getSoundResForSentence(sentence);
        if (res != -1) {
            playSound(res, context, completionListener);
        }
    }


    public static void vocalizeWord(String word, Context context, MediaPlayer.OnCompletionListener completionListener) {
        int res = getSoundResForWord(word);
        if (res != -1) {
            playSound(res, context, completionListener);
        }
    }

    public static void playSound(@RawRes int soundRes, Context context, MediaPlayer.OnCompletionListener completionListener) {
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

    private static @RawRes int getSoundResForSentence(String sentence) {
        Integer res = sentenceMap.get(sentence);
        if (res != null) {
            return res;
        }
        return -1;
    }



}
