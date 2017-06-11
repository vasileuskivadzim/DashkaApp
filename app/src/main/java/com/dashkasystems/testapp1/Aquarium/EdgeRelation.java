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
        return super.verbalDescription() + " " + objectDescription.description() + edgesDescription();
    }

    private String edgesDescription () {
        if (edges.right) {
            if (edges.top) {
                return " в правый верхний угол";
            }
            else if (edges.bottom) {
                return " в правый нижний угол";
            }
            else {
                return " справа по центру";
            }
        }
        else if (edges.left) {
            if (edges.top) {
                return " в левый верхний угол";
            }
            else if (edges.bottom) {
                return " в левый нижний угол";
            }
            else {
                return " слева по центру";
            }
        }
        else if (edges.top) {
            return " сверху по центру";
        }
        else if (edges.bottom) {
            return " снизу по центру";
        }
        else {
            return " в центр";
        }


    }
}
