package com.dashkasystems.testapp1.Declension;



public class Noun extends PartOfSpeech {
    PartOfSpeechKind kind;

    //Именительный
    NumeralSpeechCase nominative;
    //Родительный
    NumeralSpeechCase genitive;
    //Дательный
    NumeralSpeechCase dative;
    //Винительный
    NumeralSpeechCase accusative;
    //Творительный
    NumeralSpeechCase ablative;
    //Предложный
    NumeralSpeechCase prepositional;


    public Noun(NumeralSpeechCase nominative,
                NumeralSpeechCase genitive,
                NumeralSpeechCase dative,
                NumeralSpeechCase accusative,
                NumeralSpeechCase ablative,
                NumeralSpeechCase prepositional, PartOfSpeechKind kind) {

        this.nominative = nominative;
        this.genitive = genitive;
        this.dative = dative;
        this.accusative = accusative;
        this.ablative = ablative;
        this.prepositional = prepositional;

        this.kind = kind;
        this.type = PartOfSpeechType.Noun;
    }

    public String decline(PartOfSpeechCase speechCase, PartOfSpeechNumeral numeral) {
        switch (speechCase) {
            case Nominative:
                return nominative.decline(numeral);
            case Genitive:
                return genitive.decline(numeral);
            case Dative:
                return dative.decline(numeral);
            case Accusative:
                return accusative.decline(numeral);
            case Ablative:
                return ablative.decline(numeral);
            case Prepositional:
                return prepositional.decline(numeral);
        }
        return null;
    }

}



