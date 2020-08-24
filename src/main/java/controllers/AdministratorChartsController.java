package controllers;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;

import com.github.lgooddatepicker.components.DatePicker;

public interface AdministratorChartsController {
    
    public void onButtonPressed(DatePicker dateStart,DatePicker dateEnd, int choice, JPanel panel, XYChart chart);
}
