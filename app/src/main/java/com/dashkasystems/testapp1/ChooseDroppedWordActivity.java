package com.dashkasystems.testapp1;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

import org.w3c.dom.Text;

public class ChooseDroppedWordActivity extends AppCompatActivity implements View.OnClickListener {
    public enum DroppingState {DROPPING, CANCEL_DROPPING, CLEARING, READY}

    int droppingItemsHeight = (int) (115 * ScreenHelper.getDensity());
    int droppingItemMargin = (int) (70 * ScreenHelper.getDensity());

    ChooseDroppedWordExercise exercise = ExerciseFactory.chooseDroppedWordExercise();

    FrameLayout dropArea;
    LinearLayout droppingItems;
    ValueAnimator positionAnimator;

    TextView levelCaption;

    DroppingState state = DroppingState.READY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_dropped_word);

        TextView caption = (TextView) findViewById(R.id.caption);
        caption.setText("Выбери прозвучавшее слово, пока оно не достигло низа экрана.");

        levelCaption = (TextView) findViewById(R.id.levelCaption);
        levelCaption.setText(levelText(0));

        ImageButton speakBtn = (ImageButton) findViewById(R.id.speak_button);
        speakBtn.setOnClickListener(this);

        Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);

        dropArea = (FrameLayout) findViewById(R.id.drop_area_layout);

        setupDroppingItems();
        setupDroppingWords();
    }

    @Override
    protected void onPause() {
        super.onPause();
        state = DroppingState.CANCEL_DROPPING;
        if (positionAnimator != null) {
            positionAnimator.end();
        }
        Vocalizer.shared.stop();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startBtn) {
            if (state == DroppingState.READY) {
                v.setVisibility(View.INVISIBLE);
                startAnimation();
                exercise.vocalize(this);
            }
        }
        else if (v.getId() == R.id.speak_button) {
            exercise.vocalize(this);
        }
        else {
            if (state == DroppingState.DROPPING) {
                int index = v.getId();
                if (index == exercise.getRightIndex()) {
                    levelCaption.setText(levelText(exercise.getStepNumber() + 1));
                    clearLevel();
                }
            }
        }
    }

    private void setupDroppingItems() {
        droppingItems = new LinearLayout(this);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, droppingItemsHeight);
        layoutParams.topMargin = 0 - droppingItemsHeight;

        dropArea.addView(droppingItems, layoutParams);
    }

    private void setupDroppingWords() {
        droppingItems.removeAllViewsInLayout();
        String[] step = exercise.getCurrentStep();
        for (int i=0; i<step.length; i++) {
            Button text = new Button(this);
            text.setText(step[i]);
            text.setOnClickListener(this);
            text.setId(i);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.topMargin = RandomHelper.getInt(droppingItemMargin);
            droppingItems.addView(text, params);
        }
        droppingItems.setWeightSum(step.length);
    }

    private void nextStep() {
        exercise.nextStep();
        exercise.vocalize(this);
        setupDroppingWords();
        startAnimation();

    }

    private void startAnimation() {
        state = DroppingState.DROPPING;
        positionAnimator = positionAnimator(0, animationDurationForLevel(exercise.getStepNumber()));
        positionAnimator.start();
    }

    private void clearLevel() {
        state = DroppingState.CANCEL_DROPPING;
        positionAnimator.cancel();
        state = DroppingState.CLEARING;
        positionAnimator = positionAnimator(droppingItems.getTranslationY(), (long) (70 * ScreenHelper.getDensity()));
        positionAnimator.start();
    }

    private ValueAnimator positionAnimator(final float start, long duration) {
        ValueAnimator positionAnimator = ValueAnimator.ofFloat(start, dropArea.getHeight() + droppingItemsHeight);


        positionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                droppingItems.setTranslationY(value);
            }
        });
        positionAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (state == DroppingState.CLEARING) {
                    nextStep();
                } else if (state == DroppingState.DROPPING){
                    gameOver();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

        positionAnimator.setInterpolator(new LinearInterpolator());
        positionAnimator.setDuration(duration);
        return positionAnimator;
    }

    private String levelText(int level) {
        return "Достигнут уровень: " + level;
    }

    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Поражение");
        alertDialogBuilder
                .setMessage("Ты проиграл в этот раз. Попробуй сыграть ещё, но позже!")
                .setCancelable(false)
                .setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        ChooseDroppedWordActivity.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private long animationDurationForLevel(int level) {
        long duration = 10_000 - level * 200;
        if (duration < 1_000) {
            duration = 1_000;
        }
        return duration;
    }

}
