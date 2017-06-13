package com.dashkasystems.testapp1;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pandasystems on 10/30/16.
 */
public class ReorderTextExercise {
    public String[] sentences;
    public int[] shuffledIndexes;

    private Text text;

    public ReorderTextExercise(Text text){
        this.text = text;

        Pattern regExp = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher regExpMatcher = regExp.matcher(text.getContent());
        ArrayList<String> sentList = new ArrayList<>();
        while (regExpMatcher.find()) {
            sentList.add(regExpMatcher.group());
        }
        this.sentences = new String[sentList.size()];
        this.sentences = sentList.toArray(this.sentences);
        this.formIndexes();
        this.mutate();
    }

    public String getText() {
        return text.getContent();
    }
    public String getTitle() { return text.getTitle(); }


    private void formIndexes() {
        shuffledIndexes = new int[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            shuffledIndexes[i] = i;
        }
    }

    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledIndexes);
    }

    public void vocalize(Context context) {text.vocalize(context);}
    public void stopVocalizing() {text.stopVocalizing();}

}
