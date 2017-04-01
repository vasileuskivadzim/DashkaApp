package com.dashkasystems.testapp1;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ru.yandex.speechkit.Vocalizer;

public class ChooseImageExerciseActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    protected TextView captionTextView;
    protected Button rememberButton;

    protected ChooseImageExercise exercise;
    protected boolean exerciseStarted = false;
    protected List<Integer> tappedItems = new ArrayList<>();
    protected int exerciseStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image_exercise);

        captionTextView = (TextView) findViewById(R.id.choose_image_exercise_caption);
        captionTextView.setText("Нажми на животное и запопмни его название");

        rememberButton = (Button) findViewById(R.id.rememberBtn);
        rememberButton.setOnClickListener(this);

        exercise = ExerciseFactory.chooseImageExercise(4);

        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.choose_image_exersize_speak_button);
        vocalizeBtn.setOnClickListener(this);

        this.setupImageViews();
    }


    private void setupImageViews(){
        TableLayout table = (TableLayout) findViewById(R.id.choose_image_exersise_layout);


        float screenWidth = ScreenHelper.getPXWidth(this);

        int colCount = 2;
        int shapeDim = (int) ((screenWidth / colCount) - 50);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                shapeDim,
                shapeDim);
        layoutParams.setMargins(10, 10, 10, 0);

        int picturesCount = this.exercise.namedPictures.length;
        int rowCount = (int) Math.ceil( picturesCount / (double) colCount);

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            for (int col = 0; col < colCount; col++) {
                int index = row * colCount + col;

                if (index < picturesCount) {
                    ImageView image = new ImageView(this);
                    image.setImageResource(this.exercise.namedPictures[index].picture);
                    image.setLayoutParams(layoutParams);
                    image.setOnTouchListener(this);
                    image.setId(index);

                    newRow.addView(image, col);
                }
            }

            table.addView(newRow, row);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rememberBtn) {
            exerciseStarted = true;
            captionTextView.setText("Послушай и выбери животное");
            v.setVisibility(View.INVISIBLE);
        } else {
            vocalizeAtIndex(this.exercise.getRightAnswerIndex());
        }
    }

    private void vocalizeAtIndex(int index) {
        String name = this.exercise.namedPictures[index].name;
        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", name, true);
        vocalizer.start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int imageIndex = v.getId();
        if (exerciseStarted) {
            boolean right = (imageIndex == this.exercise.getRightAnswerIndex());
            if (right) {
                exerciseStep++;
                if (exerciseStep == 5) {
                    captionTextView.setText("Упражнение выполнено");
                } else {
                    this.exercise.newRightAnswer();
                }
            }
            ToastHelper.showToast(this, right);
        } else {
            vocalizeAtIndex(imageIndex);
            if (!tappedItems.contains(imageIndex)) {
                tappedItems.add(imageIndex);
                if (tappedItems.size() == this.exercise.namedPictures.length) {
                    rememberButton.setVisibility(View.VISIBLE);
                }
            }
        }

        return false;
    }


}
