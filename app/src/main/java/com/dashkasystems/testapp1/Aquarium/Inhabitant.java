package com.dashkasystems.testapp1.Aquarium;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by pandasystems on 5/1/17.
 */




public abstract class Inhabitant {
    public abstract Drawable getInhabitantDrawable(Context context);

    public enum Spot {
        ANY, BOTTOM, BOTTOM_DOUBLE
    };

    public Spot spot;

    public String verbalDescription() {return "";}

}
