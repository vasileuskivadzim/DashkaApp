package com.dashkasystems.testapp1;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import ru.yandex.speechkit.Vocalizer;

public class ChooseImageExerciseActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    protected ChooseImageExercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_image_exercise);

        String exerciseTitle = getIntent().getStringExtra("CHOSEN_EXERCISE_INTENT_KEY");

        TextView captionTextView = (TextView) findViewById(R.id.choose_image_exercise_caption);
        captionTextView.setText(exerciseTitle);

        exercise = new ChooseImageExercise();

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
        int rowCount = picturesCount / colCount;

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            for (int col = 0; col < colCount; col++) {
                int index = row * colCount + col;

                ImageView image = new ImageView(this);
                image.setImageResource(this.exercise.namedPictures[index].picture);
                image.setLayoutParams(layoutParams);
                image.setOnTouchListener(this);
                image.setId(index);

                newRow.addView(image, col);
            }

            table.addView(newRow, row);
        }
    }

    @Override
    public void onClick(View v) {
        String name = this.exercise.namedPictures[this.exercise.getRightAnswerIndex()].name;
        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", name, true);
        vocalizer.start();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean result = v.getId() == this.exercise.getRightAnswerIndex();
        ToastHelper.showToast(this, result);

        return false;
    }


}
