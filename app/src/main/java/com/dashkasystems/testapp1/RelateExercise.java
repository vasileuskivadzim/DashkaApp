package com.dashkasystems.testapp1;


/**
 * Created by pandasystems on 11/2/16.
 */
public class RelateExercise {

    public String[] rights;
    public String[] lefts;

    public int[] shuffledRightIndexes;
    public int[] shuffledLeftIndexes;


    public RelateExercise(String[] lefts, String[] rights) {
        this.lefts = lefts;
        this.rights = rights;

        this.formIndexes();
        this.mutate();
    }

    public void formIndexes() {
        shuffledRightIndexes = new int[rights.length];
        shuffledLeftIndexes = new int[lefts.length];

        for (int i = 0; i < lefts.length; i++) {
            shuffledLeftIndexes[i] = i;
        }
        for (int i = 0; i < rights.length; i++) {
            shuffledRightIndexes[i] = i;
        }
    }

    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledLeftIndexes);
        RandomHelper.shuffleIndexArray(this.shuffledRightIndexes);
    }

}
