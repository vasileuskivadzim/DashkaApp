package com.dashkasystems.testapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

import java.util.ArrayList;
import java.util.List;

public class ReorderWordsTapableExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView title;
    private TextView sentenceTextView;
    private RelativeLayout wordsLayout;
    private Button nextButton;

    protected ReorderWordsExerciseSequence exerciseSequence;
    protected int nextWordIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_words_tapable_exercise);

        this.setupExercise(ExerciseFactory.sashaAndRain());

        title = (TextView) findViewById(R.id.titleTextView);
        title.setText("Собери предложение из слов");

        sentenceTextView = (TextView) findViewById(R.id.sentenceTextView);
        sentenceTextView.setText("");

        wordsLayout = (RelativeLayout) findViewById(R.id.buttonsLayout);

        nextButton = (Button) findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(this);

        ImageButton speakButton = (ImageButton) findViewById(R.id.reorder_words_exercise_speak_btn);
        speakButton.setOnClickListener(this);


        configureTapableWords();
    }

    public void setupExercise(ReorderWordsExerciseSequence exercise){
        this.nextWordIndex = 0;
        this.exerciseSequence = exercise;
    }

    private void configureTapableWords() {
        float screenWidth = ScreenHelper.getPXWidth(this) - 64;
        ReorderWordsExercise exercise = exerciseSequence.getCurrent();

        int xOffset = 0;
        int yOffset = 0;
        int minMargin = 10;
        List<Integer> wordsLength = new ArrayList<>();
        List<String> line = new ArrayList<>();
        for (int i = 0; i < exercise.words.length; i++) {
            String word = exercise.words[exercise.shuffledIndexes[i]];
            Size wordSize = measureWord(word);
            if (xOffset + wordSize.getWidth() < screenWidth) {
                xOffset += wordSize.getWidth() + minMargin;
                wordsLength.add(wordSize.getWidth());
                line.add(word);
            } else {
                int addition = (int) (screenWidth - xOffset);
                int avgAddition = addition / (line.size() + 1);

                xOffset = wordSize.getWidth() + 10;

                int lineXOffset = avgAddition;
                for (int wordInLine = 0; wordInLine < line.size(); wordInLine++) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = lineXOffset;
                    layoutParams.topMargin = yOffset;
                    lineXOffset += wordsLength.get(wordInLine) + minMargin + avgAddition;
                    this.configureWord(layoutParams, line.get(wordInLine));
                }
                wordsLength.clear();
                line.clear();
                wordsLength.add(wordSize.getWidth());
                line.add(word);
                yOffset += wordSize.getHeight() + 10;
            }

            if (line.size() > 0 && (i + 1) == exercise.words.length) {
                int addition = (int) (screenWidth - xOffset);
                int avgAddition = (int) addition / (line.size() + 1);
                int lineXOffset = avgAddition;
                for (int wordInLine = 0; wordInLine < line.size(); wordInLine++) {
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.leftMargin = lineXOffset;
                    layoutParams.topMargin = yOffset;
                    lineXOffset += wordsLength.get(wordInLine) + minMargin + avgAddition;
                    this.configureWord(layoutParams, line.get(wordInLine));
                }
            }
        }

    }

    protected void configureWord(RelativeLayout.LayoutParams layoutParams, String word) {
        Button button = new Button(this);
        button.setLayoutParams(layoutParams);
        button.setText(word);
        button.setTag(word);
        button.setOnClickListener(this);
        wordsLayout.addView(button);
    }

    protected Size measureWord(String word) {
        Button button = new Button(this);
        button.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        button.setText(word);
        button.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = button.getMeasuredHeight();
        int width = button.getMeasuredWidth();

        return new Size(width, height);
    }

    private void vocalize() {
        if (!exerciseSequence.isCompleted()) {
            Vocalizer.vocalizeSentence(exerciseSequence.getCurrent().getSentence(), this, null);
        }
    }


    @Override
    public void onClick(View v) {
        Log.d("TAGG", "is " + v.getId());

        final int childCount = wordsLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View word = wordsLayout.getChildAt(i);
            word.setBackgroundResource(android.R.drawable.btn_default);
        }
        if (v.getId() == R.id.reorder_words_exercise_speak_btn) {
            vocalize();
        }
        else if (v.getId() == R.id.nextBtn) {
            if (!exerciseSequence.isCompleted()) {
                v.setVisibility(View.INVISIBLE);
                nextWordIndex = 0;
                sentenceTextView.setText("");
                configureTapableWords();
                vocalize();
            }
        }
        else {
            ReorderWordsExercise exercise = exerciseSequence.getCurrent();
            if (v.getTag().equals(exercise.words[nextWordIndex]))  {
                v.setVisibility(View.INVISIBLE);
                String text = (String) sentenceTextView.getText();
                text += " " + exercise.words[nextWordIndex];
                sentenceTextView.setText(text);
                nextWordIndex++;
                if (nextWordIndex == exercise.words.length) {
                    wordsLayout.removeAllViewsInLayout();
                    exerciseSequence.next();
                    if (exerciseSequence.isCompleted()) {
                        nextButton.setText("Выполнено");
                    }
                    nextButton.setVisibility(View.VISIBLE);

                    ToastHelper.showToast(this, true);
                }
            } else {
                v.setBackgroundColor(getResources().getColor(R.color.red));
                ToastHelper.showToast(this, false);
            }
        }



    }
}
