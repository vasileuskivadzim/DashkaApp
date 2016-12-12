package com.dashkasystems.testapp1;

import android.support.annotation.DrawableRes;

/**
 * Created by pandasystems on 10/30/16.
 */
public class NamedPicture {
    public String name;
    public @DrawableRes int picture;

    public NamedPicture(String name, @DrawableRes int picture) {
        this.name = name;
        this.picture = picture;
    }
}
