package com.dashkasystems.testapp1.Vocalizing;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.util.Log;

import com.dashkasystems.testapp1.Declension.Collocation;

/**
 * Created by pandasystems on 6/13/17.
 */

public class SequenceVocalizer {

    private @RawRes int[] soundRes;
    private int vocalizeIndex = 0;
    private Context context;

    public SequenceVocalizer(@RawRes int[] soundRes, Context context) {
        this.soundRes = soundRes;
        this.context = context;
    }

    public void vocalize() {
        if (soundRes.length > 0) {
            vocalizeNextRes();
        } else {
            Log.e("Vocalizer", "Empty");
        }
    }

    private void vocalizeNextRes() {
        Vocalizer.shared.playSound(soundRes[vocalizeIndex], context, completionListener);
    }

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            vocalizeIndex++;
            if (vocalizeIndex < soundRes.length) {
                vocalizeNextRes();
            }
        }
    };
}
