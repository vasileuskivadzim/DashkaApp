package com.dashkasystems.testapp1;

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
    private String initialText;
    private String title;

    public ReorderTextExercise(String text, String title){
        this.initialText = text;
        this.title = title;

        Pattern regExp = Pattern.compile("[^.!?\\s][^.!?]*(?:[.!?](?!['\"]?\\s|$)[^.!?]*)*[.!?]?['\"]?(?=\\s|$)", Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher regExpMatcher = regExp.matcher(text);
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
        return initialText;
    }
    public String getTitle() { return title; }


    private void formIndexes() {
        shuffledIndexes = new int[sentences.length];
        for (int i = 0; i < sentences.length; i++) {
            shuffledIndexes[i] = i;
        }
    }

    public void mutate() {
        RandomHelper.shuffleIndexArray(this.shuffledIndexes);
    }

}
