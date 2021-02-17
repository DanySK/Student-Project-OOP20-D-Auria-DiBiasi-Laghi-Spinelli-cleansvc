package application;
import controller.backupFile.SaveAndLoadClients;
import view.HomeView;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main {

    public static void main(final String[] args) {
        
        SaveAndLoadClients backup = new SaveAndLoadClients();
        backup.load();
        new HomeView().display();
    }

}
