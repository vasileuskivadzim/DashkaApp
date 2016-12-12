package com.dashkasystems.testapp1;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class ShapeColoringExerciseActivity extends AppCompatActivity implements View.OnTouchListener {

    private  @ColorRes int[] palette = {R.color.red, R.color.orange, R.color.yellow, R.color.green, R.color.blue,
            R.color.darkBlue, R.color.purple, R.color.white, R.color.black, R.color.darkGreen};
    private @ColorRes int selectionColor = R.color.darkBlue;

    private int currentColorIndex = 0;
    private int shapeDim;
    private int paletteDim;

    protected ShapeColoringExercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_coloring_exercise);

        this.setupExercise();
        this.configureShapeTable();
        this.configurePaletteView();
    }


    private void configureShapeTable() {
        TableLayout shapeTable = (TableLayout) this.findViewById(R.id.shapeLayout);
        float screenWidth = ScreenHelper.getPXWidth(this);

        int colCount = 3;
        this.shapeDim = (int) ((screenWidth / colCount) - 50);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                this.shapeDim,
                this.shapeDim);
        layoutParams.setMargins(10, 10, 10, 0);

        int picturesCount = this.exercise.shapes.length;

        int rowCount = picturesCount / colCount;

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            for (int col = 0; col < colCount; col++) {
                int index = row * colCount + col;

                ImageView shape = new ImageView(this);
                shape.setScaleType(ImageView.ScaleType.CENTER);
                int borderColor = getResources().getColor(R.color.black);
                ShapeDrawable shapeDrawable = this.exercise.shapeAtIndex(index, this.shapeDim, this.shapeDim, getResources().getColor(R.color.white), borderColor);
                shape.setImageDrawable(shapeDrawable);
                shape.setLayoutParams(layoutParams);
                shape.setOnTouchListener(this);
                shape.setId(100 + index);

                newRow.addView(shape, col);
            }

            shapeTable.addView(newRow, row);
        }
    }

    private void configurePaletteView() {
        TableLayout paletteTable = (TableLayout) this.findViewById(R.id.palletelayout);
        float screenWidth = ScreenHelper.getPXWidth(this);

        int colCount = 5;
        this.paletteDim = (int) ((screenWidth / colCount) - 40);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                paletteDim,
                paletteDim);
        layoutParams.setMargins(10, 10, 10, 0);

        int picturesCount = 10;

        int rowCount = picturesCount / colCount;

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            for (int col = 0; col < colCount; col++) {
                int index = row * colCount + col;

                ImageView color = new ImageView(this);
                int borderColor = index == this.currentColorIndex ? getResources().getColor(this.selectionColor) : getResources().getColor(palette[index]);
                color.setImageDrawable(ShapeFactory.drawCircle(paletteDim, paletteDim, getResources().getColor(palette[index]), borderColor));
                color.setLayoutParams(layoutParams);
                color.setOnTouchListener(this);
                color.setId(index);

                newRow.addView(color, col);
            }

            paletteTable.addView(newRow, row);
        }
    }

    protected void setupExercise() {
        ShapeColoringExercise.Shapes[] shapes = {ShapeColoringExercise.Shapes.CIRCLE,
                ShapeColoringExercise.Shapes.SQUARE, ShapeColoringExercise.Shapes.TRIANGLE,
                ShapeColoringExercise.Shapes.STAR, ShapeColoringExercise.Shapes.RECTANGLE,
                ShapeColoringExercise.Shapes.HEXAGON };
        @ColorRes int[] colors = {R.color.red, R.color.orange, R.color.yellow,
                R.color.green, R.color.blue, R.color.darkBlue};
        this.exercise = new ShapeColoringExercise(shapes, colors);
    }





    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int id = v.getId();
        if (id < this.palette.length) {
            this.setCurrentColorIndex(v, id);
        } else {
            this.fillShape(v, id - 100);
        }


        return false;
    }

    private void setCurrentColorIndex(View v, int index) {
        ImageView prevColor = (ImageView) findViewById(this.currentColorIndex);
        int prevPaletteColor = getResources().getColor(palette[this.currentColorIndex]);
        prevColor.setImageDrawable(ShapeFactory.drawCircle(paletteDim, paletteDim, prevPaletteColor, prevPaletteColor));
        this.currentColorIndex = index;
        ImageView newColor = (ImageView) v;
        int newPaletteColor = getResources().getColor(palette[this.currentColorIndex]);
        int borderColor = getResources().getColor(selectionColor);
        newColor.setImageDrawable(ShapeFactory.drawCircle(paletteDim, paletteDim, newPaletteColor, borderColor));

    }

    private void fillShape(View v, int index) {
        ImageView imageView = (ImageView) v;
        int color = getResources().getColor(this.palette[this.currentColorIndex]);
        int borderColor = getResources().getColor(R.color.black);
        ShapeDrawable shape = this.exercise.shapeAtIndex(index, this.shapeDim, this.shapeDim, color, borderColor);
        imageView.setImageDrawable(shape);
    }


}
