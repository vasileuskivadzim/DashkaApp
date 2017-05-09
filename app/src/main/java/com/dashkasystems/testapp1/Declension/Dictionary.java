package com.dashkasystems.testapp1.Declension;

import android.util.Log;

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

        //Noun
        PartOfSpeech wolf = new Noun(new NumeralSpeechCase("Волк", "Волки"),
                new NumeralSpeechCase("Волкa", "Волков"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Волк", wolf);

        PartOfSpeech rabbit = new Noun(new NumeralSpeechCase("Заяц", "Зайцы"),
                new NumeralSpeechCase("Зайца", "Зайцев"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Заяц", rabbit);

        PartOfSpeech cat = new Noun(new NumeralSpeechCase("Кот", "Коты"),
                new NumeralSpeechCase("Кота", "Котов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Кот", cat);

        PartOfSpeech hen = new Noun(new NumeralSpeechCase("Курица", "Курицы"),
                new NumeralSpeechCase("Курицы", "Куриц"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Курица", hen);

        PartOfSpeech lion = new Noun(new NumeralSpeechCase("Лев", "Львы"),
                new NumeralSpeechCase("Льва", "Львов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Лев", lion);

        PartOfSpeech fox = new Noun(new NumeralSpeechCase("Лиса", "Лисы"),
                new NumeralSpeechCase("Лисы", "Лис"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Лиса", fox);

        PartOfSpeech horse = new Noun(new NumeralSpeechCase("Лошадь", "Лошади"),
                new NumeralSpeechCase("Лошади", "Лошадей"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Лошадь", horse);

        PartOfSpeech bear = new Noun(new NumeralSpeechCase("Медведь", "Медведи"),
                new NumeralSpeechCase("Медведя", "Медведей"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Медведь", bear);

        PartOfSpeech mouse = new Noun(new NumeralSpeechCase("Мышь", "Мыши"),
                new NumeralSpeechCase("Мыши", "Мышей"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Мышь", mouse);

        PartOfSpeech deer = new Noun(new NumeralSpeechCase("Олень", "Олени"),
                new NumeralSpeechCase("Оленя", "Оленей"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Олень", deer);

        PartOfSpeech rooster = new Noun(new NumeralSpeechCase("Петух", "Петухи"),
                new NumeralSpeechCase("Петуха", "Петухов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Петух", rooster);

        PartOfSpeech pig = new Noun(new NumeralSpeechCase("Свинья", "Свиньи"),
                new NumeralSpeechCase("Свиньи", "Свиней"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Свинья", pig);

        PartOfSpeech dog = new Noun(new NumeralSpeechCase("Собака", "Собаки"),
                new NumeralSpeechCase("Собаки", "Собак"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Собака", dog);


///
        PartOfSpeech star = new Noun(new NumeralSpeechCase("Звезда", "Звёзды"),
                new NumeralSpeechCase("Звезды", "Звёзд"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Звезда", star);

        PartOfSpeech square = new Noun(new NumeralSpeechCase("Квадрат", "Квадраты"),
                new NumeralSpeechCase("Квадрата", "Квадратов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Квадрат", square);

        PartOfSpeech circle = new Noun(new NumeralSpeechCase("Круг", "Круги"),
                new NumeralSpeechCase("Круга", "Кругов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Круг", circle);

        PartOfSpeech rectangle = new Noun(new NumeralSpeechCase("Прямоугольник", "Прямоугольники"),
                new NumeralSpeechCase("Прямоугольника", "Прямоугольников"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Прямоугольник", rectangle);

        PartOfSpeech triangle = new Noun(new NumeralSpeechCase("Треугольник", "Треугольники"),
                new NumeralSpeechCase("Треугольника", "Треугольников"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Треугольник", triangle);

        PartOfSpeech hexagon = new Noun(new NumeralSpeechCase("Шестиугольник", "Шестиугольники"),
                new NumeralSpeechCase("Шестиугольника", "Шестиугольников"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Шестиугольник", hexagon);

///
        PartOfSpeech book = new Noun(new NumeralSpeechCase("Книга", "Книги"),
                new NumeralSpeechCase("Книги", "Книг"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Книга", book);

        PartOfSpeech doll = new Noun(new NumeralSpeechCase("Кукла", "Куклы"),
                new NumeralSpeechCase("Куклы", "Кукол"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Кукла", doll);

        PartOfSpeech horseToy = new Noun(new NumeralSpeechCase("Лошадка", "Лошадки"),
                new NumeralSpeechCase("Лошадки", "Лошадок"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Лошадка", horseToy);

        PartOfSpeech carToy = new Noun(new NumeralSpeechCase("Машинка", "Машинки"),
                new NumeralSpeechCase("Машинки", "Машинок"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Машинка", carToy);

        PartOfSpeech bearToy = new Noun(new NumeralSpeechCase("Мишка", "Мишки"),
                new NumeralSpeechCase("Мишки", "Мишек"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Мишка", bearToy);

        PartOfSpeech ball = new Noun(new NumeralSpeechCase("Мяч", "Мячи"),
                new NumeralSpeechCase("Мячa", "Мячей"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Мяч", ball);

        PartOfSpeech pyramid = new Noun(new NumeralSpeechCase("Пирамидка", "Пирамидки"),
                new NumeralSpeechCase("Пирамидки", "Пирамидок"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Пирамидка", pyramid);

        PartOfSpeech flight = new Noun(new NumeralSpeechCase("Самолёт", "Самолёты"),
                new NumeralSpeechCase("Самолёта", "Самолётов"),
                null, null, null, null,
                PartOfSpeechKind.Masculine);
        aMap.put("Самолёт", flight);

        PartOfSpeech turtle = new Noun(new NumeralSpeechCase("Черепашка", "Черепашки"),
                new NumeralSpeechCase("Черепашки", "Черепашек"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Черепашка", turtle);

        PartOfSpeech yule = new Noun(new NumeralSpeechCase("Юла", "Юлы"),
                new NumeralSpeechCase("Юлы", "Юлы"),
                null, null, null, null,
                PartOfSpeechKind.Feminine);
        aMap.put("Юла", yule);


        ///
        PartOfSpeech fish = new Noun(new NumeralSpeechCase("рыбка", "рыбки"),
                new NumeralSpeechCase("рыбки", "рыбок"),
                null,
                new NumeralSpeechCase("рыбку", "рыбок"),
                new NumeralSpeechCase("рыбкой", "рыбками"),
                null,
                PartOfSpeechKind.Feminine);
        aMap.put("рыбка", fish);

        PartOfSpeech waterPlant = new Noun(new NumeralSpeechCase("растение", "растения"),
                new NumeralSpeechCase("растения", "растений"),
                null,
                new NumeralSpeechCase("растение", "растения"),
                new NumeralSpeechCase("растением", "растениями"),
                null,
                PartOfSpeechKind.Neuter);
        aMap.put("растение", waterPlant);



        //Adjective
        PartOfSpeech red = new Adjective(new KindNumeralSpeechCase("красный", "красная", "красное", "красные"),
                new KindNumeralSpeechCase("красного", "красной", "красного", "красных"),
                null,
                new KindNumeralSpeechCase("красный", "красную", "красное", "красные"),
                new KindNumeralSpeechCase("красным", "красной", "красным", "красными"),
                null);
        aMap.put("красный", red);

        PartOfSpeech orange = new Adjective(new KindNumeralSpeechCase("оранжевый", "оранжевая", "оранжевое", "оранжевые"),
                new KindNumeralSpeechCase("оранжевого", "оранжевой", "оранжевого", "оранжевых"),
                null,
                new KindNumeralSpeechCase("оранжевый", "оранжевую", "оранжевое", "оранжевые"),
                new KindNumeralSpeechCase("оранжевым", "оранжевой", "оранжевым", "оранжевыми"),
                null);
        aMap.put("оранжевый", orange);

        PartOfSpeech blue = new Adjective(new KindNumeralSpeechCase("синий", "синяя", "синее", "синие"),
                new KindNumeralSpeechCase("синего", "синей", "синего", "синих"),
                null,
                new KindNumeralSpeechCase("синий", "синюю", "синее", "синие"),
                new KindNumeralSpeechCase("синим", "синей", "синим", "синими"),
                null);
        aMap.put("синий", blue);

        PartOfSpeech green = new Adjective(new KindNumeralSpeechCase("зелёный", "зелёная", "зелёное", "зелёные"),
                new KindNumeralSpeechCase("зелёного", "зелёной", "зелёного", "зелёных"),
                null,
                new KindNumeralSpeechCase("зелёный", "зелёную", "зелёное", "зелёные"),
                new KindNumeralSpeechCase("зелёным", "зелёной", "зелёным", "зелёными"),
                null);
        aMap.put("зелёный", green);

        PartOfSpeech white = new Adjective(new KindNumeralSpeechCase("белый", "белая", "белое", "белые"),
                new KindNumeralSpeechCase("белого", "белой", "белого", "белых"),
                null,
                new KindNumeralSpeechCase("белый", "белую", "белое", "белые"),
                new KindNumeralSpeechCase("белым", "белой", "белым", "белыми"),
                null);
        aMap.put("белый", white);

        PartOfSpeech yellow = new Adjective(new KindNumeralSpeechCase("жёлтый", "жёлтая", "жёлтое", "жёлтые"),
                new KindNumeralSpeechCase("жёлтого", "жёлтой", "жёлтого", "жёлтых"),
                null,
                new KindNumeralSpeechCase("жёлтый", "жёлтую", "жёлтое", "жёлтые"),
                new KindNumeralSpeechCase("жёлтым", "жёлтой", "жёлтым", "жёлтыми"),
                null);
        aMap.put("жёлтый", yellow);




        ///
        PartOfSpeech big = new Adjective(new KindNumeralSpeechCase("большой", "большая", "большое", "большие"),
                new KindNumeralSpeechCase("большого", "большой", "большого", "больших"),
                null,
                new KindNumeralSpeechCase("большой", "большую", "большое", "большие"),
                new KindNumeralSpeechCase("большим", "большой", "большим", "большими"),
                null);
        aMap.put("большой", big);

        PartOfSpeech small = new Adjective(new KindNumeralSpeechCase("маленький", "маленькая", "маленькое", "маленькие"),
                new KindNumeralSpeechCase("маленького", "маленькой", "маленького", "маленьких"),
                null,
                new KindNumeralSpeechCase("маленький", "маленькую", "маленькое", "маленькие"),
                new KindNumeralSpeechCase("маленьким", "маленькой", "маленьким", "маленькими"),
                null);
        aMap.put("маленький", small);



        //Numeral
        PartOfSpeech one = new Numeral(new KindSpeechCase("Один", "Одна", "Одно"),
                null,
                null, null, null, null,
                PartOfSpeechNumeral.Singular,
                PartOfSpeechCase.Nominative);
        aMap.put("Один", one);
        aMap.put("1", one);

        PartOfSpeech two = new Numeral(new KindSpeechCase("Два", "Две", "Два"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Singular,
                PartOfSpeechCase.Genitive);
        aMap.put("Два", two);
        aMap.put("2", two);

        PartOfSpeech three = new Numeral(new KindSpeechCase("Три", "Три", "Три"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Singular,
                PartOfSpeechCase.Genitive);
        aMap.put("Три", three);
        aMap.put("3", three);

        PartOfSpeech four = new Numeral(new KindSpeechCase("Четыре", "Четыре", "Четыре"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Singular,
                PartOfSpeechCase.Genitive);
        aMap.put("Четыре", four);
        aMap.put("4", four);

        PartOfSpeech five = new Numeral(new KindSpeechCase("Пять", "Пять", "Пять"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Пять", five);
        aMap.put("5", five);

        PartOfSpeech six = new Numeral(new KindSpeechCase("Шесть", "Шесть", "Шесть"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Шесть", six);
        aMap.put("6", six);

        PartOfSpeech seven = new Numeral(new KindSpeechCase("Семь", "Семь", "Семь"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Семь", seven);
        aMap.put("7", seven);

        PartOfSpeech eight = new Numeral(new KindSpeechCase("Восемь", "Восемь", "Восемь"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Восемь", eight);
        aMap.put("8", eight);

        PartOfSpeech nine = new Numeral(new KindSpeechCase("Девять", "Девять", "Девять"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Девять", nine);
        aMap.put("9", nine);

        PartOfSpeech ten = new Numeral(new KindSpeechCase("Десять", "Десять", "Десять"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Десять", ten);
        aMap.put("10", ten);

        PartOfSpeech eleven = new Numeral(new KindSpeechCase("Одиннадцать", "Одиннадцать", "Одиннадцать"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Одиннадцать", eleven);
        aMap.put("11", eleven);

        PartOfSpeech twelve = new Numeral(new KindSpeechCase("Двенадцать", "Двенадцать", "Двенадцать"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Двенадцать", twelve);
        aMap.put("12", twelve);

        PartOfSpeech thirteen = new Numeral(new KindSpeechCase("Тринадцать", "Тринадцать", "Тринадцать"),
                new KindSpeechCase("", "", ""),
                null, null, null, null,
                PartOfSpeechNumeral.Plural,
                PartOfSpeechCase.Genitive);
        aMap.put("Тринадцать", thirteen);
        aMap.put("13", thirteen);


        words = Collections.unmodifiableMap(aMap);
    }

    public static PartOfSpeech getWord(String word) {
        PartOfSpeech partOfSpeech = words.get(word);
        if (partOfSpeech != null) {
            return partOfSpeech;
        } else {
            Log.e("Dictionary", "Word NOT Found");
            return new Noun(new NumeralSpeechCase(word, word),
                    new NumeralSpeechCase(word, word),
                    null, null, null, null,
                    PartOfSpeechKind.Masculine);
        }

    }

}
