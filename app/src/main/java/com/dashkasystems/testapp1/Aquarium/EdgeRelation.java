package com.dashkasystems.testapp1.Aquarium;

import com.dashkasystems.testapp1.Declension.Collocation;

import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Accusative;

/**
 * Created by pandasystems on 5/9/17.
 */

public class EdgeRelation extends Relation {
    Aquarium.Edges edges;

    public EdgeRelation(Inhabitant inhabitant, Aquarium.Edges edges) {
        this.object = inhabitant;
        this.edges = edges;
    }

    @Override
    public String verbalDescription() {
        Collocation objectDescription = new Collocation(object.verbalDescription(), Accusative);
        return objectDescription.description() + " в " + edgesDescription();
    }

    private String edgesDescription () {
        if (edges.right) {
            if (edges.top) {
                return "правый верхний угол";
            }
            else if (edges.bottom) {
                return "правый нижний угол";
            }
            else {
                return "центр левой грани";
            }
        }
        else if (edges.left) {
            if (edges.top) {
                return "левый верхний угол";
            }
            else if (edges.bottom) {
                return "левый нижний угол";
            }
            else {
                return "центр левой грани";
            }
        }
        else if (edges.top) {
            return "центр верхней грани";
        }
        else if (edges.bottom) {
            return "центр нижней грани";
        }
        else {
            return "центр";
        }


    }
}
