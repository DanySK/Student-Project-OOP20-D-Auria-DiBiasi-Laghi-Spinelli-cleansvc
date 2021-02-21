package test;

import java.time.LocalDate;
import javax.swing.JPanel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import com.github.lgooddatepicker.components.DatePicker;
import controller.AdministratorChartsControllerImpl;
import model.DataChartsImpl;
import model.DateException;
import view.AdministratorChartsView;

public class TestAdministrator {
    private final JPanel panel = new JPanel();
    private final XYChart chart = new XYChartBuilder().build();
    private DatePicker dateStart = new DatePicker();
    private DatePicker dateEnd = new DatePicker();
    @Test
    public void dateControllerDateStartBefore() throws DateException {
        
        dateStart.setDate(LocalDate.now());
        dateEnd.setDate(LocalDate.of(2031,8,18));
        Assertions.assertThrows(DateException.class, ()->{
            new AdministratorChartsControllerImpl().addLine(dateStart, dateEnd, 1, panel,chart);
        });
    }    /*Ok*/

    @Test
    public void dateControllerDateEmpty() throws DateException{
        dateStart.setDate(null);
        dateEnd.setDate(null);
        Assertions.assertThrows(DateException.class, ()->{
            new AdministratorChartsControllerImpl().addLine(dateStart, dateEnd, 2, panel,chart);
        });
    }   /*Ok*/
    
    @Test
    public void dateListTest() { 
        DataChartsImpl datRange = new DataChartsImpl();
        System.out.println(datRange.getDaysDate(LocalDate.of(2020,8,20), LocalDate.of(2020, 9,1)));
    } /*Ok*/
    
    @Test
    public void dataChartTest() {
        DataChartsImpl data = new DataChartsImpl();
        System.out.println(data.buildChartsFromData(LocalDate.of(2020,8,20), LocalDate.of(2020,8, 24),2));
    }/*Ok*/

    @Test 
    public void isVisible() {
       new AdministratorChartsView().display();
    }
}
