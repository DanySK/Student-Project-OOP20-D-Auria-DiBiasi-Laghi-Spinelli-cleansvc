package application;
import controller.backupFile.*;
import view.HomeView;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main {

    public static void main(final String[] args) {
        
        SaveAndLoadClients backupC = new SaveAndLoadClients();
        SaveAndLoadStaff backupS = new SaveAndLoadStaff();
        SaveAndLoadProducts backupP = new SaveAndLoadProducts();
        
        backupC.load();
        backupS.load();
        backupP.load();
        new HomeView().display();
    }

}
