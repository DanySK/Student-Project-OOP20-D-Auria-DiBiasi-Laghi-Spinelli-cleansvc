package Test;

import java.time.LocalDate;
import javax.swing.JPanel;
import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import com.github.lgooddatepicker.components.DatePicker;
import controllers.AdministratorChartsControllerImpl;
import model.DataChartsImpl;
import model.DateException;
import view.AdministratorChartsView;
public class TestAdministrator{
    
    @Test
    public void dateControllerDateStartBefore() throws DateException {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
        XYChart chart = new XYChartBuilder().build();

     
        dateStart.setDate(LocalDate.now());
        dateEnd.setDate(LocalDate.of(2020, 8, 18));
         new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 1,panel,chart);
    }    /*Ok*/
    
    @Test
    public void dateControllerDateEmpty() throws DateException {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
        XYChart chart = new XYChartBuilder().build();
        
        new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 2, panel,chart);
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
    }/*Ok*///Non girava perché mettevo un numero in meno nelle Y
    
    @Test 
    public void isVisible(){
       new AdministratorChartsView().display();
    }
}
