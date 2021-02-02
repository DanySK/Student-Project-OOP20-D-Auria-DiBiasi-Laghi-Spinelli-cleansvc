package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class StringUtils {

    private StringUtils() { }
    /*
     * First method converts a string of words separated by underscore
     * For example from "PULIZIA_magazzino" to "PULIZIA Magazzino"
     * 
     * Second method converts a string to all lowercase with first letter to upper case
     * This method is used by the first one.
     */
    public static String underscoreWithWords(final String change) {
        return Arrays.stream(change.split("_")).map(StringUtils::maiuscola).collect(Collectors.joining(" "));
    }
    
    public static String maiuscola(final String change) {
        return change.substring(0, 1).toUpperCase() + change.substring(1).toLowerCase();
    }

}
