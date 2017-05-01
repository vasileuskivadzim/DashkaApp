package com.dashkasystems.testapp1.Aquarium;

import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pandasystems on 5/1/17.
 */

public class Aquarium {

    int dimension;
    Inhabitant[] inhabitants;

    Aquarium(int dimension) {
        this.dimension = dimension;
        inhabitants = new Inhabitant[dimension * dimension];
    }

    public List<Integer> occupiedPlacesByInhabitant(Inhabitant inhabitant) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < inhabitants.length; i++ ){
            if (inhabitant == inhabitants[i]) {
                Log.d("ADD", "index" + i);
                result.add(i);
            }
        }
        return result;
    }

    public void addInhabitant(Inhabitant inhabitant, int rootIndex) {
        inhabitants[rootIndex] = inhabitant;
        Log.d("ADD inh " + rootIndex, "index" + rootIndex);
        if (inhabitant.spot == Inhabitant.Spot.BOTTOM_DOUBLE) {
            inhabitants[rootIndex - dimension] = inhabitant;
            Log.d("ADD inh " + rootIndex, "index " + (rootIndex - dimension));
        }
    }


    public List<Integer> freePlacesForSpot(Inhabitant.Spot spot) {
        List<Integer> result;
        switch (spot) {
            case ANY:
                result = freePlacesForAnySpot();
                break;
            case BOTTOM:
                result = freePlacesForBottomSpot();
                break;
            case BOTTOM_DOUBLE:
                result = freePlacesForBottomDoubledSpot();
                break;
            default:
                result = new ArrayList<>();
                break;
        }

        Log.e("FREE", result.toString());
        return result;
    }

    private List<Integer> freePlacesForAnySpot() {
        List<Integer> free = new ArrayList<>();
        for (int i = 0; i < inhabitants.length; i++) {
            if (inhabitants[i] == null) {
                free.add(i);
            }
        }

        return free;
    }

    private List<Integer> freePlacesForBottomSpot() {
        List<Integer> free = new ArrayList<>();
        int lastLineStartIndex = inhabitants.length - dimension;
        for (int i = lastLineStartIndex; i < inhabitants.length; i++) {
            if (inhabitants[i] == null) {
                free.add(i);
            }
        }

        return free;
    }

    private List<Integer> freePlacesForBottomDoubledSpot() {
        List<Integer> free = new ArrayList<>();
        int lastLineStartIndex = inhabitants.length - dimension;
        Log.e("LAST Line", "" + lastLineStartIndex);
        for (int i = lastLineStartIndex; i < inhabitants.length; i++) {
            if (inhabitants[i] == null && inhabitants[i - dimension] == null) {
                free.add(i);
            }
        }

        return free;
    }

}
