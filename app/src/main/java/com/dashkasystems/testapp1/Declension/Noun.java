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
}



