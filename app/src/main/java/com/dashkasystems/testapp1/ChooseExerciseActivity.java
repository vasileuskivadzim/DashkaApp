package com.dashkasystems.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ChooseExerciseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected String[] exercises = {"Упражнение 1", "Упражнение 2", "Упражнение 3",
            "Упражнение 1", "Упражнение 2", "Упражнение 3",
            "Упражнение 1", "Упражнение 2", "Упражнение 3",
            "Упражнение 1", "Упражнение 2", "Упражнение 3",
            "Упражнение 1", "Упражнение 2", "Упражнение 3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_exercise);

        ListView chooseExerciseListView = (ListView) findViewById(R.id.chooseExerciseListView);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.exercises);
        chooseExerciseListView.setAdapter(arrAdapter);

        chooseExerciseListView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            Intent intent = new Intent(this, ReorderTextExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 1) {
            Intent intent = new Intent(this, ChooseImageExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 2) {
            Intent intent = new Intent(this, ReorderWordsExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 3) {
            Intent intent = new Intent(this, RelateExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 4) {
            Intent intent = new Intent(this, ShapeColoringExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else {
            Intent intent = new Intent(this, CompileSceneExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        }
    }
}
