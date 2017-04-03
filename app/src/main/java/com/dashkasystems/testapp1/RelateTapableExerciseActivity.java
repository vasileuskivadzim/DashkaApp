package com.dashkasystems.testapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import ru.yandex.speechkit.Vocalizer;

public class RelateTapableExerciseActivity extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {
    protected TextView captionTextView;
    protected TableLayout table;
    protected TextView collocationTextView;
    protected Button nextBtn;

    protected RelateImageNumberExercise exercise;
    protected int exerciseStep = 0;
    protected int selectedNumberIndex = -1;
    protected int selectedImageIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relate_tapable_exercise);

        captionTextView = (TextView) findViewById(R.id.choose_image_exercise_caption);
        captionTextView.setText("Соотнеси число и животное");

        collocationTextView = (TextView) findViewById(R.id.resultTextView);
        collocationTextView.setText("");

        nextBtn = (Button) findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(this);
        nextBtn.setVisibility(View.INVISIBLE);

        exercise = ExerciseFactory.relateImageExercise();

        table = (TableLayout) findViewById(R.id.choose_image_exersise_layout);

        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.choose_image_exersize_speak_button);
        vocalizeBtn.setOnClickListener(this);

        this.setupColumns();
    }

    private void setupColumns(){
        float screenHeight = ScreenHelper.getPXHeight(this);

        int rowCount = this.exercise.dimension;
        float density = ScreenHelper.getDensity();
        int shapeDim = (int) ((screenHeight - 250 * density)/ rowCount);
        TableRow.LayoutParams layoutParamsImage = new TableRow.LayoutParams(shapeDim, shapeDim);
        layoutParamsImage.setMargins(50, 0, 0, 0);
        TableRow.LayoutParams layoutParamsButton = new TableRow.LayoutParams(shapeDim / 2, shapeDim / 2);
        layoutParamsButton.setMargins(0, 0, 50, 0);

        for(int row = 0; row < rowCount; row++) {
            TableRow newRow = new TableRow(this);
            newRow.setGravity(Gravity.CENTER);

            Button button = new Button(this);
            button.setText(this.exercise.numbers[exercise.shuffledNumbersIndexes[row]]);
            button.setLayoutParams(layoutParamsButton);
            button.setOnClickListener(this);
            button.setId(row);
            newRow.addView(button, 0);

            ImageView image = new ImageView(this);
            image.setImageResource(this.exercise.pictures[exercise.shuffledPicturesIndexes[row]].picture);
            image.setLayoutParams(layoutParamsImage);
            image.setOnTouchListener(this);
            image.setId(row);

            newRow.addView(image, 1);

            table.addView(newRow, row);
        }
    }

    private void showNextStep() {
        exerciseStep++;
        exercise.mutate();
        table.removeAllViewsInLayout();
        setupColumns();
        selectedNumberIndex = -1;
        selectedImageIndex = -1;
        collocationTextView.setText("");
        this.vocalizeStep();
    }


    private boolean isExerciseStepCompleted() {
        return exercise.isRightSelection(true, selectedNumberIndex) && exercise.isRightSelection(false, selectedImageIndex);
    }

    private void vocalizeStep() {
        String name = this.exercise.getCollocation();
        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", name, true);
        vocalizer.start();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.choose_image_exersize_speak_button) {
            this.vocalizeStep();
        } else if (id == R.id.nextBtn) {
            showNextStep();
            nextBtn.setVisibility(View.INVISIBLE);
        } else {
            if (selectedNumberIndex != -1) {
                TableRow row = (TableRow) table.getChildAt(selectedNumberIndex);
                View prev = row.getChildAt(0);
                prev.setBackgroundColor(getResources().getColor(R.color.white));
            }
            selectedNumberIndex = id;
            int color = exercise.isRightSelection(true, selectedNumberIndex) ? R.color.green : R.color.red;
            v.setBackgroundColor(getResources().getColor(color));

            if (isExerciseStepCompleted()) {
                collocationTextView.setText(exercise.getCollocation());
                nextBtn.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (selectedImageIndex != -1) {
            TableRow row = (TableRow) table.getChildAt(selectedImageIndex);
            View prev = row.getChildAt(1);
            prev.setBackgroundColor(getResources().getColor(R.color.white));
        }
        selectedImageIndex = v.getId();
        int color = exercise.isRightSelection(false, selectedImageIndex) ? R.color.green : R.color.red;
        v.setBackgroundColor(getResources().getColor(color));

        if (isExerciseStepCompleted()) {
            collocationTextView.setText(exercise.getCollocation());
            nextBtn.setVisibility(View.VISIBLE);
        }

        return false;
    }


}
