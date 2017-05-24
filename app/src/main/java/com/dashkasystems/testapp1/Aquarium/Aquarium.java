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

    class Edges {
        boolean top;
        boolean left;
        boolean right;
        boolean bottom;
    }

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
                result.add(i);
            }
        }
        return result;
    }

    public void addInhabitant(Inhabitant inhabitant, int rootIndex) {
        inhabitants[rootIndex] = inhabitant;
        if (inhabitant.spot == Inhabitant.Spot.BOTTOM_DOUBLE) {
            inhabitants[rootIndex - dimension] = inhabitant;
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
        for (int i = lastLineStartIndex; i < inhabitants.length; i++) {
            if (inhabitants[i] == null && inhabitants[i - dimension] == null) {
                free.add(i);
            }
        }

        return free;
    }

    private List<Integer> neighborsForInhabitant(Inhabitant inhabitant) {
        List<Integer> places = occupiedPlacesByInhabitant(inhabitant);
        int maxIndex = dimension * dimension - 1;
        List<Integer> neighbors = new ArrayList<>();
        for (int place: places) {
            int placeColumn = place % dimension;

            int[] candidates = new int[4];
            candidates[0] = place - dimension;
            candidates[1] = place - 1;
            if (candidates[1] % dimension != placeColumn - 1) {
                candidates[1] = -1;
            }
            candidates[2] = place + 1;
            if (candidates[2] % dimension != placeColumn + 1) {
                candidates[2] = -1;
            }
            candidates[3] = place + dimension;

            for (int candidate: candidates) {
                if (0 <= candidate && candidate <= maxIndex) {
                    if (!places.contains(candidate)) {
                        neighbors.add(candidate);
                    }
                }
            }
        }

        Log.d("NNNNNeighbors", neighbors.toString());
        return neighbors;
    }

    private Edges neighborEdgesForInhabitant(Inhabitant inhabitant) {
        List<Integer> places = occupiedPlacesByInhabitant(inhabitant);
        Edges neighbors = new Edges();

        if (places.get(0) < dimension ) {
            neighbors.top = true;
        }
        Integer rootPlace = places.get(places.size()-1);
        if (rootPlace % dimension == 0) {
            neighbors.left = true;
        }
        if (rootPlace % dimension == dimension - 1) {
            neighbors.right = true;
        }
        if (rootPlace / dimension == dimension - 1) {
            neighbors.bottom = true;
        }
        return neighbors;
    }

    public List<Relation> relationsForInhabitant(Inhabitant inhabitant) {
        List<Relation> relations = new ArrayList<>();

        Edges edges = neighborEdgesForInhabitant(inhabitant);
        Relation edgeRelation = new EdgeRelation(inhabitant, edges);
        relations.add(edgeRelation);

        List<Integer> places = occupiedPlacesByInhabitant(inhabitant);
        int rootPlace = places.get(places.size()-1);
        List<Integer> neighborIndexes = neighborsForInhabitant(inhabitant);

        for (Integer neighbor: neighborIndexes) {
            if (inhabitants[neighbor] != null) {
                ObjectRelation.Kind kind = null;
                if (rootPlace == neighbor + 1) {
                    kind = ObjectRelation.Kind.toRIGHT;
                }
                else if (rootPlace == neighbor - 1) {
                    kind = ObjectRelation.Kind.toLEFT;
                }
                else if (rootPlace == neighbor - dimension) {
                    kind = ObjectRelation.Kind.toTOP;
                }
                else if (places.get(0) == neighbor + dimension) {
                    kind = ObjectRelation.Kind.toBOTTOM;
                }

                if (kind != null) {
                    ObjectRelation relation = new ObjectRelation(inhabitant, kind, inhabitants[neighbor]);
                    relations.add(relation);
                }
            }
        }

        for (Integer neighbor: neighborIndexes) {
            if (inhabitants[neighbor] != null) {

                Integer superpositionIndex = null;
                int rightSuperposition = neighbor + 2;
                int bottomSuperposition = neighbor + dimension;
                if (neighborIndexes.contains(rightSuperposition)) {
                    if (neighbor % dimension < dimension - 2) {
                        if (inhabitants[rightSuperposition] != null) {
                            superpositionIndex = rightSuperposition;
                        }
                    }
                }
                else if (neighborIndexes.contains(bottomSuperposition)) {
                    if (inhabitants[bottomSuperposition] != null) {
                        superpositionIndex = bottomSuperposition;
                    }
                }

                if (superpositionIndex != null) {
                    List<Inhabitant> others = new ArrayList<>(2);
                    others.add(inhabitants[neighbor]);
                    others.add(inhabitants[superpositionIndex]);

                    MultipleObjectsRelation relation = new MultipleObjectsRelation(inhabitant, MultipleObjectsRelation.Kind.BETWEEN, others);
                    relations.add(relation);
                }
            }
        }

        return relations;
    }





}
