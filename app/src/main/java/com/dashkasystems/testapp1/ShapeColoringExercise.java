package com.dashkasystems.testapp1;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorRes;
import android.util.Log;

/**
 * Created by pandasystems on 11/27/16.
 */


public class ShapeColoringExercise {
    public enum Shapes {SQUARE, CIRCLE, TRIANGLE, STAR, RECTANGLE, HEXAGON};


    public Shapes[] shapes;
    private String[] shapeNames;
    public Color[] colors;

    private int[] shuffledShapesIndexes;
    private int[] shuffledColorsIndexes;
    private boolean[] rightColoring;


    public ShapeColoringExercise(Shapes[] shapes, String[] shapeNames, Color[] colors){
        this.colors = colors;
        this.shapes = shapes;
        this.shapeNames = shapeNames;

        this.formIndexes();
        this.initColoring();
        this.mutate();
    }

    public void formIndexes() {
        shuffledShapesIndexes = new int[shapes.length];
        shuffledColorsIndexes = new int[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            shuffledShapesIndexes[i] = i;
            shuffledColorsIndexes[i] = i;
        }
    }

    public void initColoring() {
        rightColoring = new boolean[shapes.length];
        for (int i = 0; i < shapes.length; i++) {
            rightColoring[i] = false;
        }
    }


    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledColorsIndexes);
        RandomHelper.shuffleIndexArray(this.shuffledShapesIndexes);
    }

    public boolean isRightColored() {
        for (int i = 0; i < this.rightColoring.length; i++) {
            if (!this.rightColoring[i]) {
                return false;
            }
        }
        return true;
    }

    public String shapeNameAtIndex(int index) {
        int shuffledShapeIndex = this.shuffledShapesIndexes[index];
        String shapeName = this.shapeNames[shuffledShapeIndex];
        return shapeName;
    }

    public String colorNameAtIndex(int index) {
        int trueColorIndex = this.shuffledColorsIndexes[index];
        String colorName = this.colors[trueColorIndex].name;
        return colorName;
    }


    public String textAtIndex(int index) {
        return colorNameAtIndex(index) + " " + shapeNameAtIndex(index);
    }


    public void drawShapeAtIndex(int index, int color){
        int trueColorIndex = this.shuffledColorsIndexes[index];
        int trueColor = this.colors[trueColorIndex].color;
        this.rightColoring[index] = (color == trueColor);
    }


    public ShapeDrawable shapeAtIndex(int index, int width, int height, int color, int borderColor) {
        int shuffledShapeIndex = this.shuffledShapesIndexes[index];
        Shapes shape = this.shapes[shuffledShapeIndex];
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
