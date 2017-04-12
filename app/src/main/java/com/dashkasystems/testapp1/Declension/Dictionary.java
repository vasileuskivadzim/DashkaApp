package com.dashkasystems.testapp1.Declension;

import com.dashkasystems.testapp1.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pandasystems on 4/12/17.
 */

public class Dictionary {
    private static final Map<String, PartOfSpeech> words;
    static {
        Map<String, PartOfSpeech> aMap = new HashMap<>();
        PartOfSpeech wolf = new Noun(new NumeralSpeechCase("Волк", "Волки"),
                new NumeralSpeechCase("Волкa", "Волков"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);

        aMap.put("Волк", wolf);
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
        words = Collections.unmodifiableMap(aMap);
    }

}
