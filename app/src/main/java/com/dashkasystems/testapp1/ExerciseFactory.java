package com.dashkasystems.testapp1;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by pandasystems on 3/21/17.
 */

public class ExerciseFactory {
    private static String[] textsToReorder = {"Дядя Семён ехал из города домой."+
            " С ним была собака Жучка. Вдруг из леса выскочили волки. "+
            "Жучка испугалась и прыгнула в сани. У дяди Семёна была хорошая лошадь. "+
            "Она тоже испугалась и быстро помчалась по дороге. Деревня была близко. "+
            "Показались огни в окнах. Волки отстали. " +
            "Умная лошадь спасла дядю Семена и Жучку."};
    private static String[] textsToReorderTitles = {"Дядя Семён и Жучка"};

    public static ReorderTextExercise reorderTextExercise(int index) {
       return new ReorderTextExercise(textsToReorder[index], textsToReorderTitles[index]);
    }



    private static @DrawableRes int[] imagesToChoose = {
            R.drawable.wolf, R.drawable.rabbit, R.drawable.cat, R.drawable.hen, R.drawable.lion,
            R.drawable.fox, R.drawable.horse, R.drawable.bear, R.drawable.mouse, R.drawable.deer,
            R.drawable.rooster, R.drawable.pig, R.drawable.dog};
    private static String[] picturesNames = {"Волк", "Заяц", "Кот", "Курица", "Лев",
            "Лиса", "Лошадь", "Медведь", "Мышь", "Олень", "Петух", "Свинья", "Собака" };


    public static ChooseImageExercise chooseNamedPictureExercise(int dimension) {
        NamedPicture namedPictures[] = new NamedPicture[dimension];
        Set<Integer> added = new HashSet<>(dimension);
        Random rnd = new Random();
        for (int i = 0; i < dimension; i++) {
            int rand = rnd.nextInt(imagesToChoose.length);
            while (added.contains(rand)) {
                rand = rnd.nextInt(imagesToChoose.length);
            }
            added.add(rand);

            NamedPicture newPicture = new NamedPicture(picturesNames[rand], imagesToChoose[rand]);
            namedPictures[i] = newPicture;
        }

        return new ChooseNamedPictureExercise(namedPictures);
    }

    private static @DrawableRes int[] soundsNatureImages = {
            R.drawable.sounds_nature_creek, R.drawable.sounds_nature_leaves,
            R.drawable.sounds_nature_rain_on_umbrella,
            R.drawable.sounds_nature_waterfall, R.drawable.sounds_nature_wind};
    private static @RawRes int[] soundsNatureSounds = {R.raw.sounds_nature_creek, R.raw.sounds_nature_leaves,
            R.raw.sounds_nature_rain_on_umbrella, R.raw.sounds_nature_waterfall, R.raw.sounds_nature_wind};

    private static @DrawableRes int[] soundsDomesticImages = {
            R.drawable.sounds_domestic_clean_teeth, R.drawable.sounds_domestic_swipe,
            R.drawable.sounds_domestic_vase, R.drawable.sounds_domestic_washer};
    private static @RawRes int[] soundsDomesticSounds = {R.raw.sounds_domestic_clean_teeth, R.raw.sounds_domestic_swipe,
            R.raw.sounds_domestic_vase, R.raw.sounds_domestic_washer};

    private static @DrawableRes int[] soundsMusicImages = {
            R.drawable.sounds_music_flute, R.drawable.sounds_music_piano,
            R.drawable.sounds_music_small_drum,
            R.drawable.sounds_music_trumpet, R.drawable.sounds_music_violin};
    private static @RawRes int[] soundsMusicSounds = {R.raw.sounds_music_flute, R.raw.sounds_music_piano,
            R.raw.sounds_music_small_drum, R.raw.sounds_music_trumpet, R.raw.sounds_music_violin};

    public static ChooseImageExercise soundsExercise(String category, int dimension) {
        int[] images = soundsNatureImages;
        int[] sounds = soundsNatureSounds;
        if (category.equals("Domestic")) {
            images = soundsDomesticImages;
            sounds = soundsDomesticSounds;
        } else if (category.equals("Music")) {
            images = soundsMusicImages;
            sounds = soundsMusicSounds;
        }

        SoundedPicture namedPictures[] = new SoundedPicture[dimension];
        Set<Integer> added = new HashSet<>(dimension);
        Random rnd = new Random();
        for (int i = 0; i < dimension; i++) {
            int rand = rnd.nextInt(images.length);
            while (added.contains(rand)) {
                rand = rnd.nextInt(images.length);
            }
            added.add(rand);

            SoundedPicture newPicture = new SoundedPicture(sounds[rand], images[rand]);
            namedPictures[i] = newPicture;
        }

        return new ChooseSoundedPictureExercise(namedPictures);
    }





    private static String[] sentences = {"Кошка съела воробья и теперь не голодна.",
                            "Бабочка села на цветок.",
    "На высокой сосне сидит белка.",
            "Снегири сидят на ветках.",
            "Снегири прилетели и сели на ветки.",
            "На лугу растут цветы.",
            "Ярко светит солнце."};

    private static String[] sashaAndRain = {"Caша пошёл гулять на улицу.",
            "Начался дождь.",
            "У мальчика не было зонта.",
            "Он промок",
            "Саша вернулся домой мокрым"};



    public static ReorderWordsExercise reorderWordsExercise(int index) {
        return new ReorderWordsExercise(sentences[index]);
    }

    public static ReorderWordsExerciseSequence sashaAndRain() {
        ReorderWordsExercise[] exercises = new ReorderWordsExercise[sashaAndRain.length];
        for (int i = 0; i < exercises.length; i++) {
            exercises[i] = new ReorderWordsExercise(sashaAndRain[i]);
        }
        return new ReorderWordsExerciseSequence(exercises);
    }

    public static ReorderWordsExerciseSequence simpleSentences() {
        ReorderWordsExercise[] exercises = new ReorderWordsExercise[sentences.length];
        for (int i = 0; i < exercises.length; i++) {
            exercises[i] = new ReorderWordsExercise(sentences[i]);
        }
        return new ReorderWordsExerciseSequence(exercises);
    }



    private static ShapeColoringExercise.Shapes[] shapes = {ShapeColoringExercise.Shapes.CIRCLE,
            ShapeColoringExercise.Shapes.SQUARE, ShapeColoringExercise.Shapes.TRIANGLE,
            ShapeColoringExercise.Shapes.STAR, ShapeColoringExercise.Shapes.RECTANGLE,
            ShapeColoringExercise.Shapes.HEXAGON };
    private static String[] shapeNames = {"Круг", "Квадрат", "Треугольник", "Звезда", "Прямоугольник", "Шестиугольник"};

    private static Color[] colors = {Color.red(), Color.orange(), Color.yellow(),
            Color.green(), Color.blue(), Color.darkBlue()};


    public static ShapeColoringExercise shapeColoringExercise() {
        return new ShapeColoringExercise(shapes, shapeNames, colors);
    }

    private static String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"};

    private static @DrawableRes int[] imagesToRelate = {
            R.drawable.book, R.drawable.doll, R.drawable.horsetoy, R.drawable.cartoy, R.drawable.beartoy,
            R.drawable.ball, R.drawable.pyramid, R.drawable.flight, R.drawable.turtle, R.drawable.yule};
    private static String[] picturesToRelateNames = {"Книга", "Кукла", "Лошадка", "Машинка", "Мишка",
            "Мяч", "Пирамидка", "Самолёт", "Черепашка", "Юла"};

    public static RelateImageNumberExercise relateImageExercise() {
        int imagesCount = imagesToRelate.length;
        NamedPicture namedPictures[] = new NamedPicture[imagesCount];
        for (int i = 0; i < imagesCount; i++) {
            NamedPicture newPicture = new NamedPicture(picturesToRelateNames[i], imagesToRelate[i]);
            namedPictures[i] = newPicture;
        }
        return new RelateImageNumberExercise(numbers, namedPictures, 4);
    }





}
