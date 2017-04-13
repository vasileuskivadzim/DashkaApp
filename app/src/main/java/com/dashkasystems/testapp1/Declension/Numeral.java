package com.dashkasystems.testapp1.Declension;

public class Numeral extends PartOfSpeech {
    PartOfSpeechNumeral numeral;
    PartOfSpeechCase forceCase;

    //Именительный
    KindSpeechCase nominative;
    //Родительный
    KindSpeechCase genitive;
    //Дательный
    KindSpeechCase dative;
    //Винительный
    KindSpeechCase accusative;
    //Творительный
    KindSpeechCase ablative;
    //Предложный
    KindSpeechCase prepositional;


    public Numeral(KindSpeechCase nominative,
                KindSpeechCase genitive,
                KindSpeechCase dative,
                KindSpeechCase accusative,
                KindSpeechCase ablative,
                KindSpeechCase prepositional, PartOfSpeechNumeral numeral, PartOfSpeechCase forceCase) {

        this.nominative = nominative;
        this.genitive = genitive;
        this.dative = dative;
        this.accusative = accusative;
        this.ablative = ablative;
        this.prepositional = prepositional;

        this.numeral = numeral;

        this.forceCase = forceCase;
        this.type = PartOfSpeechType.Numeral;
    }

    public String decline(PartOfSpeechCase speechCase, PartOfSpeechKind kind) {
        switch (speechCase) {
            case Nominative:
                return nominative.decline(kind);
            case Genitive:
                return genitive.decline(kind);
            case Dative:
                return dative.decline(kind);
            case Accusative:
                return accusative.decline(kind);
            case Ablative:
                return ablative.decline(kind);
            case Prepositional:
                return prepositional.decline(kind);
        }
        return null;
    }
}

