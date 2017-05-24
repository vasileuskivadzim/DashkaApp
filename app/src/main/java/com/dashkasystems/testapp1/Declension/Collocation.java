package com.dashkasystems.testapp1.Declension;

/**
 * Created by pandasystems on 4/13/17.
 */

public class Collocation {
    public String[] words;
    private String first;
    private String second;


    public Collocation(String first, String second) {
        init(first, second, null);
    }

    public Collocation(String collocation, PartOfSpeechCase forceSpeechCase) {
        String[] words = collocation.split(" ");
        init(words[0], words[1], forceSpeechCase);
    }

    private void init(String first, String second, PartOfSpeechCase forceSpeechCase){
        this.first = first;
        this.second = second;
        PartOfSpeech word1 = Dictionary.getWord(first);
        switch (word1.type) {
            case Noun:
                nounLeadingCollocation((Noun) word1, second, forceSpeechCase);
                break;
            case Adjective:
                adjectiveLeadingCollocation((Adjective) word1, second, forceSpeechCase);
                break;
            case Numeral:
                numeralLeadingCollocation((Numeral) word1, second, forceSpeechCase);
                break;
        }
    }


    public String description() {
        if (words != null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < words.length; i++) {
                if (i > 0) {
                    builder.append(" ");
                }
                builder.append(words[i]);
            }
            return builder.toString();
        }
        return "Failed: " + first + " " + second + "!!!!";
    }

    private void nounLeadingCollocation(Noun noun, String second, PartOfSpeechCase forceSpeechCase) {
        PartOfSpeech word2 = Dictionary.getWord(second);
        switch (word2.type) {
            case Adjective:
                Adjective adjective = (Adjective) word2;
                words = new String[2];
                if (forceSpeechCase == null) {
                    words[0] = noun.decline(PartOfSpeechCase.Nominative, PartOfSpeechNumeral.Singular);
                    words[1] = adjective.decline(PartOfSpeechCase.Nominative, PartOfSpeechNumeral.Singular, noun.kind);
                }
                else {
                    words[0] = noun.decline(forceSpeechCase, PartOfSpeechNumeral.Singular);
                    words[1] = adjective.decline(forceSpeechCase, PartOfSpeechNumeral.Singular, noun.kind);
                }
                break;
            case Numeral:
                Numeral numeral = (Numeral) word2;
                words = new String[2];
                if (forceSpeechCase == null) {
                    words[0] = noun.decline(numeral.forceCase, numeral.numeral);
                    words[1] = numeral.decline(PartOfSpeechCase.Nominative, noun.kind);
                }
                else {
                    words[0] = noun.decline(forceSpeechCase, numeral.numeral);
                    words[1] = numeral.decline(forceSpeechCase, noun.kind);
                }
                break;
        }
    }

    private void adjectiveLeadingCollocation(Adjective adjective, String second, PartOfSpeechCase forceSpeechCase) {
        PartOfSpeech word2 = Dictionary.getWord(second);
        switch (word2.type) {
            case Noun:
                Noun noun = (Noun) word2;
                words = new String[2];
                if (forceSpeechCase == null) {
                    words[0] = adjective.decline(PartOfSpeechCase.Nominative, PartOfSpeechNumeral.Singular, noun.kind);
                    words[1] = noun.decline(PartOfSpeechCase.Nominative, PartOfSpeechNumeral.Singular);
                }
                else {
                    words[0] = adjective.decline(forceSpeechCase, PartOfSpeechNumeral.Singular, noun.kind);
                    words[1] = noun.decline(forceSpeechCase, PartOfSpeechNumeral.Singular);
                }
                break;
        }
    }

    private void numeralLeadingCollocation(Numeral numeral, String second, PartOfSpeechCase forceSpeechCase) {
        PartOfSpeech word2 = Dictionary.getWord(second);
        switch (word2.type) {
            case Noun:
                Noun noun = (Noun) word2;
                words = new String[2];
                if (forceSpeechCase == null) {
                    words[0] = numeral.decline(PartOfSpeechCase.Nominative, noun.kind);
                    words[1] = noun.decline(numeral.forceCase, numeral.numeral);
                } else {
                    words[0] = numeral.decline(forceSpeechCase, noun.kind);
                    words[1] = noun.decline(forceSpeechCase, numeral.numeral);
                }
                break;
        }
    }

}
