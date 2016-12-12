package com.dashkasystems.testapp1;

import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

import ru.yandex.speechkit.Vocalizer;

public class RelateExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private DragListView leftListView;

    private DragListView rightListView;

    protected RelateExercise exercise;

    private int vocalizeIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relate_exercise);
        this.setupExercise();
        ArrayList<Pair<Long, String>> leftItemArray = new ArrayList<>();
        for (int i = 0; i < exercise.lefts.length; i++) {
            leftItemArray.add(new Pair<>(Long.valueOf(exercise.shuffledLeftIndexes[i]), exercise.lefts[exercise.shuffledLeftIndexes[i]]));
        }

        ArrayList<Pair<Long, String>> rightItemArray = new ArrayList<>();
        for (int i = 0; i < exercise.rights.length; i++) {
            rightItemArray.add(new Pair<>(Long.valueOf(exercise.shuffledRightIndexes[i]), exercise.rights[exercise.shuffledRightIndexes[i]]));
        }

        this.leftListView = (DragListView) findViewById(R.id.relate_left_list_view);
        this.rightListView = (DragListView) findViewById(R.id.relate_right_list_view);

        leftListView.setLayoutManager(new LinearLayoutManager(leftListView.getContext()));
        ItemAdapter leftListAdapter = new ItemAdapter(leftItemArray, R.layout.list_item, R.id.text, false);
        leftListView.setAdapter(leftListAdapter, true);
        leftListView.setCanDragHorizontally(false);

        rightListView.setLayoutManager(new LinearLayoutManager(rightListView.getContext()));
        ItemAdapter rightListAdapter = new ItemAdapter(rightItemArray, R.layout.list_item, R.id.text, false);
        rightListView.setAdapter(rightListAdapter, true);
        rightListView.setCanDragHorizontally(false);


        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.relate_exercise_speak_btn);
        vocalizeBtn.setOnClickListener(this);

        Button checkBtn = (Button) findViewById(R.id.relate_exercise_check_btn);
        checkBtn.setOnClickListener(this);
    }

    protected void setupExercise() {
        String[] lefts = {"Кот", "Собака", "Мяч", "Кролик", "Лялька"};
        String[] rights = {"5", "2", "3", "1", "4"};

        this.exercise = new RelateExercise(lefts, rights);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.relate_exercise_check_btn:
                this.checkResult();
                break;
            case R.id.relate_exercise_speak_btn:
                this.vocalize();
                break;
            default:
                break;
        }
    }

    private void vocalize() {

        if (vocalizeIndex >= exercise.lefts.length) {
            vocalizeIndex = 0;
        }
        String pairText = exercise.lefts[vocalizeIndex] + " -- " + exercise.rights[vocalizeIndex];
        Log.d("af", pairText);
        vocalizeIndex++;

        Vocalizer vocalizer = Vocalizer.createVocalizer("ru-RU", pairText, true);
        vocalizer.start();
    }

    private void checkResult() {
        ItemAdapter leftAdapter = (ItemAdapter) leftListView.getAdapter();
        ItemAdapter rightAdapter = (ItemAdapter) rightListView.getAdapter();

        for (int i = 0; i < exercise.lefts.length; i++) {
            long leftStartIndex = leftAdapter.getItemId(i);
            long rightStartIndex = rightAdapter.getItemId(i);
            if (leftStartIndex != rightStartIndex) {
                ToastHelper.showToast(this, false);
                return;
            }
        }

        ToastHelper.showToast(this, true);
    }
}
