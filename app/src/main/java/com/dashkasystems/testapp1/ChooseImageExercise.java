package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pandasystems on 10/30/16.
 */
public class ChooseImageExercise {
    public NamedPicture[] namedPictures;

    private int rightAnswerIndex;
    public int getRightAnswerIndex() { return this.rightAnswerIndex; }


    public ChooseImageExercise(NamedPicture[] pictures) {
        this.namedPictures = pictures;
        this.mutate();
        this.newRightAnswer();
    }

    // Implementing Fisher–Yates shuffle
    public void mutate() {
        Random rnd = new Random();
        for (int i = this.namedPictures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            // Simple swap picture
            NamedPicture picture = this.namedPictures[index];
            this.namedPictures[index] = this.namedPictures[i];
            this.namedPictures[i] = picture;
        }
    }

    public void newRightAnswer() {
        Random random = new Random();
        this.rightAnswerIndex = random.nextInt(this.namedPictures.length);
    }


}
