package com.dashkasystems.testapp1;

import android.content.Context;
import android.support.annotation.RawRes;

import com.dashkasystems.testapp1.Vocalizing.SequenceVocalizer;
import com.dashkasystems.testapp1.Vocalizing.Vocalizer;

/**
 * Created by pandasystems on 6/11/17.
 */

public class Text {
    private String title;
    private String content;
    private @RawRes int soundRes;
    private @RawRes int[] soundResSequence;


    public Text(String title, String content, @RawRes int soundRes) {
        this.title = title;
        this.content = content;
        this.soundRes = soundRes;
    }

    public Text(String title, String content, @RawRes int[] soundResSequence) {
        this.title = title;
        this.content = content;
        this.soundResSequence = soundResSequence;
    }

    public String getTitle() { return title; }

    public String getContent() { return content; }


    public void vocalize(Context context) {
        if ( soundResSequence != null ) {
            SequenceVocalizer vocalizer = new SequenceVocalizer(soundResSequence, context);
            vocalizer.vocalize();
        }
        else {
            Vocalizer.shared.playSound(soundRes, context, null);
        }
    }

    public void stopVocalizing() {
        Vocalizer.shared.stop();
    }

}
