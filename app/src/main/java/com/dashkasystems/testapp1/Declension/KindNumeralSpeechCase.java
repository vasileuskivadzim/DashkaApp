package com.dashkasystems.testapp1.Declension;

public class KindNumeralSpeechCase extends KindSpeechCase {
    public String plural;

    public KindNumeralSpeechCase(String masculine, String feminine, String neuter, String plural) {
        super(masculine, feminine, neuter);
        this.plural = plural;
    }

    public String decline(PartOfSpeechKind kind, PartOfSpeechNumeral numeral) {
        if (numeral == PartOfSpeechNumeral.Plural) {
            return plural;
        } else {
            switch (kind) {
                case Masculine:
                    return masculine;
                case Feminine:
                    return feminine;
                case Neuter:
                    return neuter;
            }
        }
        return null;
    }
}
