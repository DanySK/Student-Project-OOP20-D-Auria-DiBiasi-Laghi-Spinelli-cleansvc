package Test;

import java.time.LocalDate;

import javax.swing.JPanel;

import org.junit.jupiter.api.Test;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import com.github.lgooddatepicker.components.DatePicker;
import controllers.AdministratorChartsControllerImpl;
import model.DataCharts;
public class TestAdministrator{
   /* @Test 
    public void isVisible(){
       new AdministratorChartsView().display();
    }*/
    
    @Test
    public void dateControllerDateStartBefore() {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
        XYChart chart = new XYChartBuilder().build();

     
        dateStart.setDate(LocalDate.now());
        dateEnd.setDate(LocalDate.of(2020, 8, 18));
         new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 1,panel,chart);
    }    /*Ok*/
    
    @Test
    public void dateControllerDateEmpty() {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
        XYChart chart = new XYChartBuilder().build();
        
        new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 2, panel,chart);
    }   /*Ok*/
    
    @Test
    public void dateListTest() { 
        DataCharts datRange = new DataCharts();
        System.out.println(datRange.getDaysDate(LocalDate.of(2020,8,20), LocalDate.of(2020, 9,1)));
    } /*Ok*/
    
    @Test
    public void dataChartTest() {
        DataCharts data = new DataCharts();
        System.out.println(data.buildChartsFromData(LocalDate.of(2020,8,20), LocalDate.of(2020,8, 24),2));
    }
}
