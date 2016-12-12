package com.dashkasystems.testapp1;

import java.util.Random;

/**
 * Created by pandasystems on 11/2/16.
 */
public class ReorderWordsExercise {
    public String[] words;
    public int[] shuffledIndexes;
    private String initialSentence;

    public ReorderWordsExercise(String initialSentence) {
        this.initialSentence = initialSentence;

        this.words = initialSentence.split("[ .?!]");


        this.formIndexes();
        this.mutate();
    }

    public String getSentence() {
        return initialSentence;
    }

    public void formIndexes() {
        shuffledIndexes = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            shuffledIndexes[i] = i;
        }
    }

    // Implementing Fisher–Yates shuffle
    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledIndexes);
    }

}
