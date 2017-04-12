package com.dashkasystems.testapp1.Declension;

public class KindNumeralSpeechCase extends KindSpeechCase {
    public String plural;

    public KindNumeralSpeechCase(String masculine, String feminine, String neuter, String plural) {
        super(masculine, feminine, neuter);
        this.plural = plural;
    }
}
