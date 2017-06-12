package com.dashkasystems.testapp1.Vocalizing;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.util.Log;

import com.dashkasystems.testapp1.Declension.Collocation;

/**
 * Created by pandasystems on 4/15/17.
 */

public class CollocationVocalizer {
    private Collocation collocation;
    private int vocalizeIndex = 0;
    private Context context;

    public CollocationVocalizer(Collocation collocation, Context context) {
        this.collocation = collocation;
        this.context = context;
    }

    public void vocalize() {
        if (collocation.words != null) {
            vocalizeNextWord();
        } else {
            Log.e("Vocalizer", "Empty");
        }
    }

    private void vocalizeNextWord() {
        Vocalizer.shared.vocalizeWord(collocation.words[vocalizeIndex], context, completionListener);
    }

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            vocalizeIndex++;
            if (vocalizeIndex < collocation.words.length) {
                vocalizeNextWord();
            }
        }
    };
}
