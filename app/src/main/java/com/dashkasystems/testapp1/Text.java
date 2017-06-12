package com.dashkasystems.testapp1;

import android.support.annotation.RawRes;

/**
 * Created by pandasystems on 6/11/17.
 */

public class Text {
    private String title;
    private String content;
    private @RawRes int soundRes;


    public Text(String title, String content, @RawRes int soundRes) {
        this.title = title;
        this.content = content;
        this.soundRes = soundRes;
    }

    public String getTitle() { return title; }

    public String getContent() { return content; }

    public @RawRes int getSoundRes() { return soundRes; }
}
