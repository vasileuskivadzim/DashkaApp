package com.dashkasystems.testapp1;

/**
 * Created by pandasystems on 4/16/17.
 */

public class ReorderWordsExerciseSequence {
    ReorderWordsExercise[] exercises;
    int currentIndex = 0;

    ReorderWordsExerciseSequence(ReorderWordsExercise[] exercises) {
        this.exercises = exercises;
    }

    ReorderWordsExercise getCurrent() {
        if (currentIndex < exercises.length) {
            return exercises[currentIndex];
        } else {
            return null;
        }
    }

    void next() {
        currentIndex++;
    }

    boolean isCompleted() {
        return currentIndex == exercises.length;
    }
}
