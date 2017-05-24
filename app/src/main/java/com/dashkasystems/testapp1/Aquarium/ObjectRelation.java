package com.dashkasystems.testapp1.Aquarium;

import com.dashkasystems.testapp1.Declension.Collocation;
import com.dashkasystems.testapp1.Declension.PartOfSpeechCase;

import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Ablative;
import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Accusative;
import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Genitive;

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
        StringBuilder builder = new StringBuilder();
        Collocation objectDescription = new Collocation(object.verbalDescription(), Accusative);
        builder.append(objectDescription.description());
        builder.append(" ");
        builder.append(kindDescription());
        builder.append(" ");

        PartOfSpeechCase forceCase;
        if (kind == Kind.toLEFT || kind == Kind.toRIGHT) {
            forceCase = Genitive;
        }
        else {
            forceCase = Ablative;
        }

        Collocation otherDescription = new Collocation(other.verbalDescription(), forceCase);
        builder.append(otherDescription.description());

        return super.verbalDescription() + " " + builder.toString();
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
