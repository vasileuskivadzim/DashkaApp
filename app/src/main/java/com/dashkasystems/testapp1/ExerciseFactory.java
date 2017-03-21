package com.dashkasystems.testapp1;

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



}
