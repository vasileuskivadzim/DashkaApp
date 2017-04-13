package com.dashkasystems.testapp1.Declension;

/**
 * Created by pandasystems on 4/12/17.
 */

enum PartOfSpeechNumeral {
    Singular, Plural
}

enum PartOfSpeechType {
    Noun, Adjective, Verb, Numeral
}

enum PartOfSpeechKind {
    Masculine, Feminine, Neuter
}

enum PartOfSpeechCase {
    Nominative, Genitive, Dative, Accusative, Ablative, Prepositional;
}


public class PartOfSpeech {
    PartOfSpeechType type;
}
