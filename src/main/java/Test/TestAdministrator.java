package Test;

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
    
    public void dateController() {
    }
}
