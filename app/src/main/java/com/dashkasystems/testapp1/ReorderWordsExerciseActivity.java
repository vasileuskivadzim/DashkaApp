package com.dashkasystems.testapp1;

import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.woxthebox.draglistview.BoardView;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

import ru.yandex.speechkit.Vocalizer;

public class ReorderWordsExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    //private BoardView boardView;
    private DragListView gridListView;
    private ArrayList<Pair<Long, String>> mItemArray;

    protected ReorderWordsExercise exercise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_words_exercise);

        this.setupExercise();


        mItemArray = new ArrayList<>();
        for (int i = 0; i < exercise.words.length; i++) {
            mItemArray.add(new Pair<>(Long.valueOf(exercise.shuffledIndexes[i]), exercise.words[exercise.shuffledIndexes[i]]));
        }
        gridListView = (DragListView) findViewById(R.id.reorder_words_list_view);
        gridListView.setLayoutManager(new GridLayoutManager(this, 4));

        ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, false);
        gridListView.setAdapter(listAdapter, true);


        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.reorder_words_exercise_speak_btn);
        vocalizeBtn.setOnClickListener(this);

        Button checkBtn = (Button) findViewById(R.id.reorder_words_exercise_check_btn);
        checkBtn.setOnClickListener(this);

    }

    protected void setupExercise() {
        this.exercise = new ReorderWordsExercise("Кошка съела воробья и теперь не голодна.");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reorder_words_exercise_check_btn:
                this.checkResult();
                break;
            case R.id.reorder_words_exercise_speak_btn:
                this.vocalize();
                break;
            default:break;
        }
    }

    private void vocalize() {
        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", exercise.getSentence(), true);
        vocalizer.start();
    }

    private void checkResult() {
        ItemAdapter adapter = (ItemAdapter) gridListView.getAdapter();
        long prevId = -1;

        for (int i = 0; i < exercise.words.length; i++) {
            long currentId = adapter.getItemId(i);
            if (prevId > currentId) {
                ToastHelper.showToast(this, false);
                return;
            }
            prevId = currentId;
        }

        ToastHelper.showToast(this, true);
    }
}
