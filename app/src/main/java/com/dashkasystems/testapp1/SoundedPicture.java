package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;

/**
 * Created by pandasystems on 4/17/17.
 */

public class SoundedPicture {
    public @RawRes int sound;
    public @DrawableRes int picture;

    public SoundedPicture(@RawRes int sound, @DrawableRes int picture) {
        this.sound = sound;
        this.picture = picture;
    }
}
