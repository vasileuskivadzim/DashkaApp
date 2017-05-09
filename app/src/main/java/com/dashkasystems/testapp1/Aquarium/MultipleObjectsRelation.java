package com.dashkasystems.testapp1.Aquarium;

import java.util.List;

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
        builder.append(object.verbalDescription());
        builder.append(" ");
        builder.append("между");
        builder.append(" ");
        for (int i = 0; i < others.size(); i++) {
            builder.append(others.get(i).verbalDescription());
            if ( i != others.size() - 1) {
                builder.append(" и ");
            }
        }

        return builder.toString();
    }
}
