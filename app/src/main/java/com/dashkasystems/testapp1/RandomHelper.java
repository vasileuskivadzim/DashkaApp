package com.dashkasystems.testapp1;

import java.util.Random;

/**
 * Created by pandasystems on 11/13/16.
 */
public class RandomHelper {
    // Implementing Fisher–Yates shuffle
    public static void shuffleIndexArray(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            // Simple swap
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    public static int getInt(int ceil) {
        Random rnd = new Random();
        return rnd.nextInt(ceil);
    }
}
