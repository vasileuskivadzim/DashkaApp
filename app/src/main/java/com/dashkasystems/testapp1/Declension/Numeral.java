package com.dashkasystems.testapp1.Declension;

public class Numeral extends PartOfSpeech {
    PartOfSpeechNumeral numeral;

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
                KindSpeechCase prepositional, PartOfSpeechNumeral numeral) {

        this.nominative = nominative;
        this.genitive = genitive;
        this.dative = dative;
        this.accusative = accusative;
        this.ablative = ablative;
        this.prepositional = prepositional;

        this.numeral = numeral;
        this.type = PartOfSpeechType.Numeral;
    }
}

