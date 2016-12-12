package com.dashkasystems.testapp1;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorRes;

/**
 * Created by pandasystems on 11/27/16.
 */


public class ShapeColoringExercise {
    public enum Shapes {SQUARE, CIRCLE, TRIANGLE, STAR, RECTANGLE, HEXAGON};

    public @ColorRes int[] colors;
    public Shapes[] shapes;

    public int[] shuffledIndexes;


    public ShapeColoringExercise(Shapes[] shapes, @ColorRes int[] colors){
        this.colors = colors;
        this.shapes = shapes;

        this.formIndexes();
        this.mutate();
    }

    public void formIndexes() {
        shuffledIndexes = new int[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            shuffledIndexes[i] = i;
        }
    }

    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledIndexes);
    }

    public ShapeDrawable shapeAtIndex(int index, int width, int height, int color, int borderColor) {
        int shuffledIndex = this.shuffledIndexes[index];
        Shapes shape = this.shapes[shuffledIndex];
        switch (shape) {
            case HEXAGON:
                return ShapeFactory.drawHexagon(width, height, color, borderColor);
            case RECTANGLE:
                return ShapeFactory.drawRectangle(width, height, color, borderColor);
            case SQUARE:
                return ShapeFactory.drawSquare(width, height, color, borderColor);
            case CIRCLE:
                return ShapeFactory.drawCircle(width, height, color, borderColor);
            case STAR:
                return ShapeFactory.drawStar(width, height, color, borderColor);
            case TRIANGLE:
                return  ShapeFactory.drawTriangle(width, height, color, borderColor);
            default:
                return null;
        }

    }

}
