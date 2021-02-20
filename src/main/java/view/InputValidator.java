package view;

import java.util.regex.Pattern;

/**
 * This class validates input data
 * 
 * @author Vanessa Di Biasi
 *
 */

public class InputValidator {

    private String code = "^[A-Za-z0-9]*$";
    private String name = "^[A-Za-z ]*$";
    private String phone = "^[0-9]{3,12}$";
    private String email = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String CF = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";
    private String PIVA = "^[0-9]{11}$";
    private String CAP = "^[0-9]{5}$";
    /**
     * Check if the parameter is in alphanumeric format
     * @param txtField
     * @return
     */
    public Boolean isCode(String txtField) {
        return Pattern.matches(this.code, txtField);
    }
    /**
     * Check if the parameter is in alphabetic format
     * @param txtField
     * @return
     */
    public Boolean isName(String txtField) {
        return Pattern.matches(name, txtField);
    }
    
    /**
     * Check if the parameter is in telephone format
     * @param txtField
     * @return
     */
    public Boolean isPhone(String txtField) {
        return Pattern.matches(phone, txtField);
    }
    
    /**
     * Check if the parameter is in email format
     * @param txtField
     * @return
     */
    public Boolean isEmail(String txtField) {
        return Pattern.matches(email, txtField);
    }
    
    /**
     * Check if the parameter is a integer number
     * @param txtField
     * @return
     */
    public Boolean isInteger(String txtField) {
        try {
            Integer i = Integer.parseInt(txtField);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Check if the parameter is a double number
     * @param txtField
     * @return
     */
    public Boolean isDouble(String txtField) {
        try {
            Double d = Double.parseDouble(txtField);
            return true;
        } catch (NullPointerException | NumberFormatException e) {
            return false;
        }
    }
    
    public Boolean isCFPIVA(String txtField) {
        return (Pattern.matches(this.CF, txtField) || Pattern.matches(this.PIVA, txtField));
    }
    
    public Boolean isCAP(String txtField) {
        return Pattern.matches(this.CAP, txtField);
    }
}
