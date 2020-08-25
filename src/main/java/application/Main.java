package application;

import java.time.LocalDate;

import model.DataCharts;
import view.AdministratorChartsView;

/**
 * This class represent the Main class of the JavaFX-based application.
 */
public final class Main{

    public static void main(final String[] args) {
		//Cancellate questo newl, scrivete il vostro se avete bisogno, in fase di test non vi tiene la finestra aperta, se valutate un metodo diverso da display per presentare 
		//la finestra va bene, si scrive in chat e ne parliamo tutti :)
        new AdministratorChartsView().display();
        
    }

}
