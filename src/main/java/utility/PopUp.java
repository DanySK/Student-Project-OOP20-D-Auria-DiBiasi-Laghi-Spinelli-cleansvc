package utility;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp extends JFrame {

    private static final long serialVersionUID = 3647943346532131731L;

    /**
     * Alert of info.
     * @param msg
     */
    public void popUpInfo(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Alert of warning.
     * @param msg
     */
    public void popUpWarning(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Attenzione", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Alert of error.
     * @param msg
     */
    public void popUpError(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Alert of error or empty field in input.
     */
    public void popUpErrorOrMissing() {
        this.popUpWarning("Dati mancanti o errati. Riprovare.");
    }

    /**
     * Alert of input required.
     * @param msg
     * @return the user's input
     */
    public String popUpInput(final String msg) {
        return JOptionPane.showInputDialog(rootPane, msg, "Richiesta dati", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Alert of confirm operation.
     * @param msg
     * @return true if the user confirm
     */
    public Boolean popUpConfirm(final String msg) {
        return JOptionPane.showConfirmDialog(rootPane, msg, "Conferma scelta", JOptionPane.YES_NO_OPTION) == 0;
    }
}
