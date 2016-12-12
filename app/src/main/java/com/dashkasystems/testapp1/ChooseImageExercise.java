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


    public ChooseImageExercise() {
        this.namedPictures = new NamedPicture[4];
        this.namedPictures[0] = new NamedPicture("Лев", R.drawable.lion);
        this.namedPictures[1] = new NamedPicture("Oлень", R.drawable.deer);
        this.namedPictures[2] = new NamedPicture("Кролик", R.drawable.rabbit);
        this.namedPictures[3] = new NamedPicture("Волк", R.drawable.wolf);

        this.mutate();
        this.rightAnswerIndex = this.newRightAnswer();
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

    public int newRightAnswer() {
        Random random = new Random();
        return random.nextInt(this.namedPictures.length);
    }


}
