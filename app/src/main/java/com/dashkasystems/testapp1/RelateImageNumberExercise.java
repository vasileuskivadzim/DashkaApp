package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;

import com.dashkasystems.testapp1.Declension.Collocation;

/**
 * Created by pandasystems on 4/1/17.
 */

public class RelateImageNumberExercise {
    public String[] numbers;
    public NamedPicture[] pictures;
    public int dimension;

    public int[] shuffledNumbersIndexes;
    public int[] shuffledPicturesIndexes;

    private int rightNumberIndex;
    private int rightPictureIndex;

    public String getNumber() {
        return this.numbers[shuffledNumbersIndexes[rightNumberIndex]];
    }

    public String getPictureName() {
        return this.pictures[shuffledPicturesIndexes[rightPictureIndex]].name;
    }

    public Collocation getCollocation() {
        String number = getNumber();
        String imageName = getPictureName();

        return new Collocation(number, imageName);
    }

    public boolean isRightSelection(boolean isNumber, int index) {
        if (isNumber) {
            return rightNumberIndex == index;
        } else {
            return rightPictureIndex == index;
        }
    }



    public RelateImageNumberExercise(String[] numbers, NamedPicture[] pictures, int dimension) {
        this.numbers = numbers;
        this.pictures = pictures;
        this.dimension = dimension;

        this.rightNumberIndex = RandomHelper.getInt(dimension);
        this.rightPictureIndex = RandomHelper.getInt(dimension);


        this.formIndexes();
        this.mutate();
    }

    public void formIndexes() {
        shuffledNumbersIndexes = new int[numbers.length];
        shuffledPicturesIndexes = new int[pictures.length];

        for (int i = 0; i < pictures.length; i++) {
            shuffledPicturesIndexes[i] = i;
        }
        for (int i = 0; i < numbers.length; i++) {
            shuffledNumbersIndexes[i] = i;
        }
    }

    public void mutate() {
        this.rightNumberIndex = (this.rightPictureIndex + RandomHelper.getInt(dimension)) % dimension;
        this.rightPictureIndex = (this.rightNumberIndex + RandomHelper.getInt(dimension)) % dimension;
        RandomHelper.shuffleIndexArray(this.shuffledNumbersIndexes);
        RandomHelper.shuffleIndexArray(this.shuffledPicturesIndexes);

    }

}
