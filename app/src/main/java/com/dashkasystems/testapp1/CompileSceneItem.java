package com.dashkasystems.testapp1;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.DrawableRes;
import android.util.Size;

/**
 * Created by pandasystems on 12/13/16.
 */

public class CompileSceneItem {
    public @DrawableRes int drawable;
    public String name;
    public DARect[] frames;


    public CompileSceneItem(int drawableId, String name, DARect[] frames) {
        this.drawable = drawableId;
        this.name = name;
        this.frames = frames;
    }
}
