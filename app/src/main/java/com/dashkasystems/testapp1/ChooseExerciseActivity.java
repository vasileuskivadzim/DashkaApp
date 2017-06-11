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

    protected String[] exercises = {"Назови музыкальный инструмент", "Назови бытовые звуки", "Назови звук", "Назови животное",
            "Назови звуки природы", "Соотнеси число с картинкой", "Раскрась фигуры",
            "Составь предложение из слов В1", "Составь предложение из слов В2", "Засели аквариум",
            "Выбери слово", "Составь текст из предложений", "Собери картинку по тексту"};

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
        Intent intent = new Intent();

        switch (position) {
            case 0:
                intent = new Intent(this, ChooseImageExerciseActivity.class);
                intent.putExtra("Sounds", "Music");
                break;
            case 1:
                intent = new Intent(this, ChooseImageExerciseActivity.class);
                intent.putExtra("Sounds", "Domestic");
                break;
            case 2:
                intent = new Intent(this, CompileSceneExercisePrepareActivity.class);
                break;
            case 3:
                intent = new Intent(this, ChooseImageExerciseActivity.class);
                break;
            case 4:
                intent = new Intent(this, ChooseImageExerciseActivity.class);
                intent.putExtra("Sounds", "Nature");
                break;
            case 5:
                intent = new Intent(this, RelateTapableExerciseActivity.class);
                break;
            case 6:
                intent = new Intent(this, ShapeColoringExerciseActivity.class);
                break;
            case 7:
                intent = new Intent(this, ReorderWordsExerciseActivity.class);
                break;
            case 8:
                intent = new Intent(this, ReorderWordsTapableExerciseActivity.class);
                break;
            case 9:
                intent = new Intent(this, AquariumActivity.class);
                break;
            case 10:
                intent = new Intent(this, ChooseDroppedWordActivity.class);
                break;
            case 11:
                intent = new Intent(this, ReorderTextExerciseActivity.class);
                break;
            case 12:
                intent = new Intent(this, CompileSceneExerciseActivity.class);
                break;
            default:
                intent = new Intent(this, CompileSceneExercisePrepareActivity.class);
                break;
        }
        intent.putExtra("CHOSEN_EXERCISE_INTENT_KEY", exercises[position]);
        this.startActivity(intent);
    }
}
