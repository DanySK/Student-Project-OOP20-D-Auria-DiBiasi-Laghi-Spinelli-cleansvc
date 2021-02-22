package utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class StringUtils {

    private StringUtils() { }
    /**
     * Method converts a string of words separated by underscore.
     * For example from "PULIZIA_magazzino" to "PULIZIA Magazzino".
     * @param change the underscore separated string to convert.
     * @return a readable string.
     */
    public static String underscoreWithWords(final String change) {
        return Arrays.stream(change.split("_")).map(StringUtils::uppercase).collect(Collectors.joining(" "));
    }

     /**
      * Method converts a string to all lower-case with first letter to upper case.
      * This method is used by the first one.
      * @param change string to be capitalized.
      * @return the capitalized string.
      */

    public static String uppercase(final String change) {
        return change.substring(0, 1).toUpperCase() + change.substring(1).toLowerCase();
    }

}
