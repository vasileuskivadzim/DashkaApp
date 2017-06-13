package com.dashkasystems.testapp1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

import java.util.ArrayList;
import java.util.List;

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


        String soundsType = getIntent().getStringExtra("Sounds");
        if (soundsType != null) {
            exercise = ExerciseFactory.soundsExercise(soundsType, 4);
            captionTextView.setText("Нажми на картинку и запомни звук");
        } else {
            exercise = ExerciseFactory.chooseNamedPictureExercise(4);
            captionTextView.setText("Нажми на животное и запомни его название");
        }

        rememberButton = (Button) findViewById(R.id.rememberBtn);
        rememberButton.setOnClickListener(this);



        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.choose_image_exersize_speak_button);
        vocalizeBtn.setOnClickListener(this);

        this.setupImageViews();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Vocalizer.shared.stop();
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

        int picturesCount = this.exercise.getPicturesCount();
        int rowCount = (int) Math.ceil( picturesCount / (double) colCount);

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            for (int col = 0; col < colCount; col++) {
                int index = row * colCount + col;

                if (index < picturesCount) {
                    ImageView image = new ImageView(this);
                    image.setImageResource(this.exercise.getPictureResAtIndex(index));
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
            captionTextView.setText("Послушай и выбери картинку");
            v.setVisibility(View.INVISIBLE);
        } else {
            vocalizeAtIndex(this.exercise.getRightAnswerIndex());
        }
    }

    private void vocalizeAtIndex(int index) {
        this.exercise.vocalizeAtIndex(index, this);
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
                    gameOver();
                } else {
                    this.exercise.newRightAnswer();
                    ToastHelper.showToast(this, right);
                }
            } else {
                ToastHelper.showToast(this, right);
            }
        } else {
            vocalizeAtIndex(imageIndex);
            if (!tappedItems.contains(imageIndex)) {
                tappedItems.add(imageIndex);
                if (tappedItems.size() == this.exercise.getPicturesCount()) {
                    rememberButton.setVisibility(View.VISIBLE);
                }
            }
        }

        return false;
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Молодец");
        alertDialogBuilder
                .setMessage("Ты успешно выполнил упражнение! Можешь попробовать ещё раз позже.")
                .setCancelable(false)
                .setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        ChooseImageExerciseActivity.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


}
