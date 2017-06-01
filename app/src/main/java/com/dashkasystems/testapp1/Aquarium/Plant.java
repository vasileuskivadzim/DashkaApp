package com.dashkasystems.testapp1.Aquarium;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.dashkasystems.testapp1.R;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Plant extends Inhabitant {
    Plant(Spot spot) {
        this.spot = spot;
    }

    @Override
    public Drawable getInhabitantDrawable(Context context) {
        if (this.spot == Spot.BOTTOM_DOUBLE) {
            return context.getDrawable(R.drawable.big_aqua_plant);
        } else {
            return context.getDrawable(R.drawable.small_aqua_plant);
        }
    }

    @Override
    public String verbalDescription() {
        if (this.spot == Spot.BOTTOM_DOUBLE) {
            return "большой растение";
        } else {
            return "маленький растение";
        }
    }


}
