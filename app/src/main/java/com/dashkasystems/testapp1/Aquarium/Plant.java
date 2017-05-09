package com.dashkasystems.testapp1.Aquarium;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Plant extends Inhabitant {
    Plant(Spot spot) {
        this.spot = spot;
    }

    @Override
    public String verbalDescription() {
        if (this.spot == Spot.BOTTOM_DOUBLE) {
            return "большое растение";
        } else {
            return "маленькое растение";
        }
    }
}
