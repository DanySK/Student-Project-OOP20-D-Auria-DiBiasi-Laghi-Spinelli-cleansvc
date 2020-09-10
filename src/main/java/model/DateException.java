package model;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DateException extends IllegalArgumentException {

    /**
     * 
     */
    private static final long serialVersionUID = 908412516388107764L;
    /**
     * 
     */
    
    private final static String ERROR = "Errore formato data";
    
    public DateException() {
        super();
    }
    
    public void warning(JPanel panel){
        JOptionPane.showMessageDialog(panel,"Formato data non valido!");
    }
    
    public String message() {
        return DateException.ERROR;
    }
    
}
