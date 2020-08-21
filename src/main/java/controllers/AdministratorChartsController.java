package controllers;
import javax.swing.JPanel;

import com.github.lgooddatepicker.components.DatePicker;

public interface AdministratorChartsController {
    
    public void onButtonPressed(DatePicker dateStart,DatePicker dateEnd, int choice, JPanel panel);
}
