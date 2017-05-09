package com.dashkasystems.testapp1.Aquarium;

import com.dashkasystems.testapp1.Color;

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
}
