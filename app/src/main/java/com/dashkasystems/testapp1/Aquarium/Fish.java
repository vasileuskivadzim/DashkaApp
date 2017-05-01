package com.dashkasystems.testapp1.Aquarium;

import com.dashkasystems.testapp1.Color;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Fish extends Inhabitant {
    public Color color;

    Fish(Color color/*, int place*/) {
        this.color = color;
        //this.rootPlace = place;

        this.spot = Spot.ANY;
    }

}
