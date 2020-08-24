package Test;

import java.time.LocalDate;

import javax.swing.JPanel;

import org.junit.jupiter.api.Test;
import com.github.lgooddatepicker.components.DatePicker;
import view.AdministratorChartsView;
import controllers.AdministratorChartsControllerImpl;
public class TestAdministrator{
    @Test 
    public void isVisible(){
       new AdministratorChartsView().display();
    }
    
    @Test
    public void dateControllerDateStartBefore() {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
     
        dateStart.setDate(LocalDate.now());
        dateEnd.setDate(LocalDate.of(2020, 8, 18));
         new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 1,panel);
    }
    
    @Test
    public void dateControllerDateEmpty() {
        DatePicker dateStart = new DatePicker();
        DatePicker dateEnd = new DatePicker();
        JPanel panel = new JPanel();
        
        new AdministratorChartsControllerImpl().onButtonPressed(dateStart, dateEnd, 2, panel);
    }
}
