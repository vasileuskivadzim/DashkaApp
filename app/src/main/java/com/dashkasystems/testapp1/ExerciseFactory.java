package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;

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



    private static @DrawableRes int[] imagesToReorder = { R.drawable.lion, R.drawable.deer, R.drawable.rabbit, R.drawable.wolf};
    private static String[] picturesNames = {"Лев", "Oлень", "Кролик", "Волк"};

    public static ChooseImageExercise chooseImageExercise(int dimension) {
        NamedPicture namedPictures[] = new NamedPicture[dimension];
        Set<Integer> added = new HashSet<>(dimension);
        Random rnd = new Random();
        for (int i = 0; i < dimension; i++) {
            int rand = rnd.nextInt(imagesToReorder.length);
            while (added.contains(rand)) {
                rand = rnd.nextInt(imagesToReorder.length);
            }
            added.add(rand);

            NamedPicture newPicture = new NamedPicture(picturesNames[rand], imagesToReorder[rand]);
            namedPictures[i] = newPicture;
        }

        return new ChooseImageExercise(namedPictures);
    }



}
