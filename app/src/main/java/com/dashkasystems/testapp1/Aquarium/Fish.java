package com.dashkasystems.testapp1.Aquarium;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import com.dashkasystems.testapp1.Color;
import com.dashkasystems.testapp1.R;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Fish extends Inhabitant {
    public Color color;

    Fish(Color color) {
        this.color = color;
        this.spot = Spot.ANY;
    }

    @Override
    public String verbalDescription() {
        return color.name + " рыбка";
    }

    @Override
    public Drawable getInhabitantDrawable(Context context) {
        Drawable fish = context.getDrawable(R.drawable.gray_aqua_fish).mutate();
        int color = context.getColor(this.color.color);
        fish.setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        return fish;
    }
}
