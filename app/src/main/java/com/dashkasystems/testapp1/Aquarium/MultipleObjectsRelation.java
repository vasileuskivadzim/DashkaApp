package com.dashkasystems.testapp1.Aquarium;

import com.dashkasystems.testapp1.Declension.Collocation;
import com.dashkasystems.testapp1.Declension.PartOfSpeechCase;
import com.dashkasystems.testapp1.Declension.SpeechCase;

import java.util.List;

import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Ablative;
import static com.dashkasystems.testapp1.Declension.PartOfSpeechCase.Accusative;


/**
 * Created by pandasystems on 5/9/17.
 */

public class MultipleObjectsRelation extends Relation {
    public enum Kind {BETWEEN}

    Kind kind;
    List<Inhabitant> others;

    MultipleObjectsRelation(Inhabitant inhabitant, Kind kind, List<Inhabitant> others) {
        this.object = inhabitant;
        this.kind = kind;
        this.others = others;
    }

    @Override
    public String verbalDescription() {
        StringBuilder builder = new StringBuilder();
        Collocation objectDescription = new Collocation(object.verbalDescription(), Accusative);
        builder.append(objectDescription.description());
        builder.append(" ");
        builder.append("между");
        builder.append(" ");
        for (int i = 0; i < others.size(); i++) {
            Collocation otherDescription = new Collocation(others.get(i).verbalDescription(), Ablative);
            builder.append(otherDescription.description());
            if ( i != others.size() - 1) {
                builder.append(" и ");
            }
        }

        return super.verbalDescription() + " " + builder.toString();
    }
}
