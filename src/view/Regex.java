package view;

/**
 * An interface to hold regular expression constants
 */
public interface Regex {
    /**
     * SENTENCE_DELIMETERS - used to parse text into sentences
     * WORDS_DELIMETERS - used to parse text into words
     */
    String SENTENCE_DELIMETERS = "[.!?]+";
    String WORDS_DELIMETERS = "[-\\[\\]\",;:/ ()\n]+";
}
