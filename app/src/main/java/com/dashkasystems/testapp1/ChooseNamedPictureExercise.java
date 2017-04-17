package com.dashkasystems.testapp1;

import android.content.Context;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

import java.util.Random;

public class ChooseNamedPictureExercise implements ChooseImageExercise {
    public NamedPicture[] namedPictures;

    private int rightAnswerIndex;
    public int getRightAnswerIndex() { return this.rightAnswerIndex; }


    public ChooseNamedPictureExercise(NamedPicture[] pictures) {
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

    public int getPicturesCount() {
        return namedPictures.length;
    }

    public int getPictureResAtIndex(int index){
        return namedPictures[index].picture;
    }

    public void vocalizeAtIndex(int index, Context context){
        String name = namedPictures[index].name;
        Vocalizer.vocalizeWord(name, context, null);
    }


}
