package com.dashkasystems.testapp1;

import android.content.Context;
import android.support.annotation.DrawableRes;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by pandasystems on 10/30/16.
 */

interface ChooseImageExercise {
    void newRightAnswer();
    int getRightAnswerIndex();
    int getPicturesCount();
    int getPictureResAtIndex(int index);
    void vocalizeAtIndex(int index, Context context);
}

