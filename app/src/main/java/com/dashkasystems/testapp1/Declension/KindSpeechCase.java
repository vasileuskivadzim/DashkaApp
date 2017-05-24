package com.dashkasystems.testapp1.Declension;

public class KindSpeechCase extends SpeechCase {
    public String feminine;
    public String neuter;

    public KindSpeechCase(String masculine, String feminine, String neuter) {
        super(masculine);
        this.feminine = feminine;
        this.neuter = neuter;
    }

    public String decline(PartOfSpeechKind kind) {
        switch (kind) {
            case Masculine:
                return masculine;
            case Feminine:
                return feminine;
            case Neuter:
                return neuter;
        }
        return null;
    }
}

