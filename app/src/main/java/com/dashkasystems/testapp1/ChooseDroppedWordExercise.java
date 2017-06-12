package com.dashkasystems.testapp1;

import android.content.Context;
import android.media.MediaPlayer;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

/**
 * Created by pandasystems on 5/30/17.
 */

public class ChooseDroppedWordExercise {

    private String[] words;

    private String[] currentStep;
    private int rightIndex;

    private int stepNumber = 0;


    int packSize = 3;

    ChooseDroppedWordExercise(String[] words) {
        this.words = words;
        generateStep();
    }

    int getRightIndex() {
        return rightIndex;
    }

    String[] getCurrentStep() {
        return currentStep;
    }

    int getStepNumber() { return stepNumber; }

    String[] nextStep() {
        generateStep();
        stepNumber++;
        return getCurrentStep();
    }


    void vocalize(Context context) {
        Vocalizer.shared.vocalizeWord(currentStep[rightIndex], context, null);
    }

    private void generateStep() {
        currentStep = new String[packSize];

        for (int i = 0; i < packSize; i++) {
            boolean generated = false;
            String candidate = "";
            while (!generated) {
                candidate = words[RandomHelper.getInt(words.length)];
                if (!isCurrStepContains(candidate)) {
                    generated = true;
                    currentStep[i] = candidate;
                }
            }
        }

        rightIndex = RandomHelper.getInt(packSize);
    }


    private boolean isCurrStepContains(String string) {
        for (int i = 0; i < currentStep.length; i++) {
            if ((currentStep[i] != null) && (!currentStep[i].equals(""))) {
                if (currentStep[i].equals(string)) {
                    return true;
                }
            }
        }
        return false;
    }


}
