package com.dashkasystems.testapp1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;


public class ReorderTextExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private DragListView dragListView;
    private ArrayList<Pair<Long, String>> mItemArray;

    protected ReorderTextExercise exercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupExercise();
        setContentView(R.layout.activity_reorder_text_exercise);

        mItemArray = new ArrayList<>();
        for (int i = 0; i < exercise.sentences.length; i++) {
            mItemArray.add(new Pair<>(Long.valueOf(exercise.shuffledIndexes[i]), exercise.sentences[exercise.shuffledIndexes[i]]));
        }

        dragListView = (DragListView) findViewById(R.id.reorder_text_list_view);

        dragListView.setLayoutManager(new LinearLayoutManager(dragListView.getContext()));

        ItemAdapter listAdapter = new ItemAdapter(this.mItemArray, R.layout.list_item, R.id.text, false);
        listAdapter.fontSize = 18;
        dragListView.setAdapter(listAdapter, true);
        dragListView.setCanDragHorizontally(false);

        TextView titleLabel = (TextView) findViewById(R.id.titleTextView);
        titleLabel.setText(exercise.getTitle());

        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.reorder_text_exercise_speak_btn);
        vocalizeBtn.setOnClickListener(this);

        Button checkBtn = (Button) findViewById(R.id.reorder_text_exercise_check_btn);
        checkBtn.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        exercise.stopVocalizing();
    }

    private void setupExercise() {
        this.exercise = ExerciseFactory.reorderTextExercise(0);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.reorder_text_exercise_check_btn:
               this.checkResult();
               break;
           case R.id.reorder_text_exercise_speak_btn:
               this.vocalize();
               break;
           default:
               break;
       }
    }

    private void vocalize() {
        exercise.vocalize(this);
    }

    private void checkResult() {
        ItemAdapter adapter = (ItemAdapter) dragListView.getAdapter();
        long prevId = -1;

        for (int i = 0; i < exercise.sentences.length; i++) {
            long currentId = adapter.getItemId(i);
            if (prevId > currentId) {
                ToastHelper.showToast(this, false);
                return;
            }
            prevId = currentId;
        }

        gameOver();
        //ToastHelper.showToast(this, true);
    }


    private void gameOver() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        alertDialogBuilder.setTitle("Молодец!");
        alertDialogBuilder
                .setMessage("Ты правильно составил текст! Можешь попробовать ещё раз позже.")
                .setCancelable(false)
                .setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        ReorderTextExerciseActivity.this.finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
