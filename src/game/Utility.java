package game;

public class Utility {
    /**
     * Utility method to determine if a word starts with a vowel.
     *
     * @param word The word to check.
     * @return true if the word starts with a vowel, false otherwise.
     */
    public static boolean startsWithVowel(String word) {
        char firstChar = Character.toLowerCase(word.charAt(0));
        return firstChar == 'a' || firstChar == 'e' || firstChar == 'i' || firstChar == 'o' || firstChar == 'u';
    }
}
