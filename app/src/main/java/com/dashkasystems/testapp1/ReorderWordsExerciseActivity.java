package com.dashkasystems.testapp1;

import android.media.MediaPlayer;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dashkasystems.testapp1.Vocalizing.Vocalizer;
import com.woxthebox.draglistview.DragListView;

import java.util.ArrayList;

public class ReorderWordsExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    //private BoardView boardView;
    private DragListView gridListView;
    private ArrayList<Pair<Long, String>> mItemArray;

    protected ReorderWordsExerciseSequence exerciseSequence;
    private Button checkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_words_exercise);

        this.setupExercise();

        gridListView = (DragListView) findViewById(R.id.reorder_words_list_view);

        setupViews();

        TextView captionLabel = (TextView) findViewById(R.id.titleTextView);
        captionLabel.setText(getIntent().getStringExtra("CHOSEN_EXERCISE_INTENT_KEY"));


        ImageButton vocalizeBtn = (ImageButton) findViewById(R.id.reorder_words_exercise_speak_btn);
        vocalizeBtn.setOnClickListener(this);

        checkBtn = (Button) findViewById(R.id.reorder_words_exercise_check_btn);
        checkBtn.setOnClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Vocalizer.shared.stop();
    }

    protected void setupViews() {
        ReorderWordsExercise exercise = exerciseSequence.getCurrent();
        gridListView.setLayoutManager(new GridLayoutManager(this, 3));
        mItemArray = new ArrayList<>();
        for (int i = 0; i < exercise.words.length; i++) {
            mItemArray.add(new Pair<>(Long.valueOf(exercise.shuffledIndexes[i]), exercise.words[exercise.shuffledIndexes[i]]));
        }
        ItemAdapter listAdapter = new ItemAdapter(mItemArray, R.layout.column_item, R.id.item_layout, false);
        listAdapter.fontSize = 20;
        gridListView.setAdapter(listAdapter, true);
    }


    protected void setupExercise() {
        this.exerciseSequence = ExerciseFactory.simpleSentences();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.reorder_words_exercise_check_btn:
                String title = (String) checkBtn.getText();
                if (title.equals("Дальше")) {
                    getNext();
                } else if (title.equals("Проверить")) {
                    this.checkResult();
                }
                break;
            case R.id.reorder_words_exercise_speak_btn:
                this.vocalize();
                break;
            default:break;
        }
    }

    private void vocalize() {
        if (!exerciseSequence.isCompleted()) {
            Vocalizer.shared.vocalizeSentence(exerciseSequence.getCurrent().getSentence(), this, null);
        }
    }

    private void checkResult() {
        ItemAdapter adapter = (ItemAdapter) gridListView.getAdapter();
        ReorderWordsExercise exercise = exerciseSequence.getCurrent();
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
        exerciseSequence.next();
        if (!exerciseSequence.isCompleted()) {
            checkBtn.setText("Дальше");
        } else {
            checkBtn.setText("Выполнено");
        }
    }

    private void getNext() {
        checkBtn.setText("Проверить");
        vocalize();
        //gridListView.removeAllViewsInLayout();
        setupViews();
    }

}
