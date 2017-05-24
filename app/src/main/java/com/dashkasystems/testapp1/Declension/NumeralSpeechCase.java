package com.dashkasystems.testapp1.Declension;

public class NumeralSpeechCase extends SpeechCase {
    public String plural;

    public NumeralSpeechCase(String masculine, String plural) {
        super(masculine);
        this.plural = plural;
    }

    public String decline(PartOfSpeechNumeral numeral) {
        switch (numeral) {
            case Plural:
                return plural;
            case Singular:
                return masculine;
        }
        return null;
    }
}

