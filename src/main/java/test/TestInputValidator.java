package test;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class TestInputValidator {

    private final static String CF = "DBSVSS96S62D000Y";
    private final static String PIVA = "12345678901";
    private final static String NAME = "Vanessa Di Biasi";
    private final static String ADDRESS = "Via dell'artigianato 3/a";
    private final static String CAP = "47001";
    private final static String TEL = "3331234588";
    private final static String EMAIL = "vanessa@unibo.it";
    private final static String DOUBLE = "3.5";
    private final static String REGEX_NAME_AND_NUM = "^[A-Za-z0-9-_./\\',\s]+$";
    private final static String REGEX_NAME = "^[A-Za-z-.'\s]+$";
    private final static String REGEX_PHONE = "^[0-9]{3,12}$";
    private final static String REGEX_EMAIL = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$";
    private final static String REGEX_CF = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
    private final static String REGEX_PIVA = "^[0-9]{11}$";
    private final static String REGEX_CAP = "^[0-9]{5}$";
    
    
    @Test
    public void testValidator() {
        assertTrue(Pattern.matches(REGEX_NAME_AND_NUM, ADDRESS));
        assertTrue(Pattern.matches(REGEX_NAME, NAME));
        assertTrue(Pattern.matches(REGEX_PHONE, TEL));
        assertTrue(Pattern.matches(REGEX_EMAIL, EMAIL));
        assertTrue(Pattern.matches(REGEX_CAP, CAP));
        assertTrue(Pattern.matches(REGEX_CF, CF));
        assertTrue(Pattern.matches(REGEX_PIVA, PIVA));
        try {
            Integer.parseInt(CAP);
            assertTrue(true);
        } catch (NullPointerException | NumberFormatException e) {
            assertTrue(false);
        }
        try {
            Double.parseDouble(DOUBLE);
            assertTrue(true);
        } catch (NullPointerException | NumberFormatException e) {
            assertTrue(false);
        }
    }
}
