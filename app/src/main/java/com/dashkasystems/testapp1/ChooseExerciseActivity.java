package com.dashkasystems.testapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dashkasystems.testapp1.Aquarium.Aquarium;


public class ChooseExerciseActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected String[] exercises = {"Составь текст из предложений", "Выбери картинку", "Собери предложение из слов",
            "Соотнеси число с картинкой", "Раскрась фигуры", "Собери картину",
            "Составь предложение из слов", "Бытовые звуки", "Звуки природы",
            "Музыкальные интсрументы", "Засели аквариум", "Выбери слово"};

    /*
    Назови музыкальный инструмент
Назови бытовые звуки
Назови животное
Назови звуки природы
Соотнеси число с картинкой
Раскрась фигуры
Составь предложение из слов В1
Составь предложение из слов В2
Засели аквариум
Выбери слово
Составь текст из предложений
Собери картинку по тексту
на гласные после бытовых звуков (назові звук)
     */

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
            Intent intent = new Intent(this, RelateTapableExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 4) {
            Intent intent = new Intent(this, ShapeColoringExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 5) {
            Intent intent = new Intent(this, CompileSceneExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 6) {
            Intent intent = new Intent(this, ReorderWordsTapableExerciseActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 7) {
            Intent intent = new Intent(this, ChooseImageExerciseActivity.class);
            intent.putExtra("Sounds", "Domestic");
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 8) {
            Intent intent = new Intent(this, ChooseImageExerciseActivity.class);
            intent.putExtra("Sounds", "Nature");
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 9) {
            Intent intent = new Intent(this, ChooseImageExerciseActivity.class);
            intent.putExtra("Sounds", "Music");
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 10) {
            Intent intent = new Intent(this, AquariumActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        } else if (position == 11) {
            Intent intent = new Intent(this, ChooseDroppedWordActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, CompileSceneExercisePrepareActivity.class);
            intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
            this.startActivity(intent);
        }
    }
}
