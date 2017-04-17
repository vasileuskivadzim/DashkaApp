package com.dashkasystems.testapp1;

import android.content.Context;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

import java.util.Random;

/**
 * Created by pandasystems on 4/17/17.
 */

public class ChooseSoundedPictureExercise implements ChooseImageExercise {
    public SoundedPicture[] pictures;

    private int rightAnswerIndex;
    public int getRightAnswerIndex() { return this.rightAnswerIndex; }


    public ChooseSoundedPictureExercise(SoundedPicture[] pictures) {
        this.pictures = pictures;
        this.mutate();
        this.newRightAnswer();
    }

    // Implementing Fisher–Yates shuffle
    public void mutate() {
        Random rnd = new Random();
        for (int i = this.pictures.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);

            // Simple swap picture
            SoundedPicture picture = this.pictures[index];
            this.pictures[index] = this.pictures[i];
            this.pictures[i] = picture;
        }
    }

    public void newRightAnswer() {
        Random random = new Random();
        this.rightAnswerIndex = random.nextInt(this.pictures.length);
    }

    public int getPicturesCount() {
        return pictures.length;
    }

    public int getPictureResAtIndex(int index){
        return pictures[index].picture;
    }

    public void vocalizeAtIndex(int index, Context context){
        int soundRes = pictures[index].sound;
        Vocalizer.playSound(soundRes, context, null);
    }
}
