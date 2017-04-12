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
                KindNumeralSpeechCase prepositional, PartOfSpeechKind kind) {

        this.nominative = nominative;
        this.genitive = genitive;
        this.dative = dative;
        this.accusative = accusative;
        this.ablative = ablative;
        this.prepositional = prepositional;
        this.type = PartOfSpeechType.Adjective;
    }
}

