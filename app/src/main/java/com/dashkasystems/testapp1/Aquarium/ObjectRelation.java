package com.dashkasystems.testapp1.Aquarium;

/**
 * Created by pandasystems on 5/9/17.
 */

public class ObjectRelation extends Relation {
    public enum Kind {toLEFT, toRIGHT, toTOP, toBOTTOM}

    Inhabitant other;
    Kind kind;

    ObjectRelation(Inhabitant inhabitant, ObjectRelation.Kind kind, Inhabitant other) {
        this.object = inhabitant;
        this.kind = kind;
        this.other = other;
    }

    @Override
    public String verbalDescription() {
        return object.verbalDescription()+ " " + kindDescription() + " " + other.verbalDescription();
    }

    private String kindDescription() {
        switch (kind) {
            case toLEFT:
                return "слева от";
            case toBOTTOM:
                return "под";
            case toRIGHT:
                return "справа от";
            case toTOP:
                return "над";
            default:
                return "";
        }
    }
}
