package com.dashkasystems.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class CompileSceneExercisePrepareActivity extends AppCompatActivity implements View.OnClickListener {
    CompileSceneExercise exercise = CompileSceneExercise.getExercise();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compile_scene_exercise_prepare);

        Button checkBtn = (Button) findViewById(R.id.checkBtn);
        checkBtn.setOnClickListener(this);

        ImageButton speakBtn = (ImageButton) findViewById(R.id.speakBtn);
        speakBtn.setOnClickListener(this);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(exercise.getText().getContent());

        TextView title = (TextView) findViewById(R.id.title);
        title.setText(exercise.getText().getTitle());
    }

    @Override
    protected void onPause() {
        super.onPause();
        exercise.stopVocalizing();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.checkBtn) {
            Intent intent = new Intent(this, CompileSceneExerciseActivity.class);
            this.startActivity(intent);
        } else if (v.getId() == R.id.speakBtn) {
            exercise.vocalize(this);
        }
    }
}
