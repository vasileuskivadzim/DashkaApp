package com.dashkasystems.testapp1.Declension;


public class Adjective extends PartOfSpeech {
    //Именительный
    KindNumeralSpeechCase nominative;
    //Родительный
    KindNumeralSpeechCase genitive;
    //Дательный
    KindNumeralSpeechCase dative;
    //Винительный
    KindNumeralSpeechCase accusative;
    //Творительный
    KindNumeralSpeechCase ablative;
    //Предложный
    KindNumeralSpeechCase prepositional;


    public Adjective(KindNumeralSpeechCase nominative,
                KindNumeralSpeechCase genitive,
                KindNumeralSpeechCase dative,
                KindNumeralSpeechCase accusative,
                KindNumeralSpeechCase ablative,
                KindNumeralSpeechCase prepositional) {

        this.nominative = nominative;
        this.genitive = genitive;
        this.dative = dative;
        this.accusative = accusative;
        this.ablative = ablative;
        this.prepositional = prepositional;
        this.type = PartOfSpeechType.Adjective;
    }

    public String decline(PartOfSpeechCase speechCase, PartOfSpeechNumeral numeral, PartOfSpeechKind kind) {
        switch (speechCase) {
            case Nominative:
                return nominative.decline(kind, numeral);
            case Genitive:
                return genitive.decline(kind, numeral);
            case Dative:
                return dative.decline(kind, numeral);
            case Accusative:
                return accusative.decline(kind, numeral);
            case Ablative:
                return ablative.decline(kind, numeral);
            case Prepositional:
                return prepositional.decline(kind, numeral);
        }
        return null;
    }
}

