package controllers;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;

import com.github.lgooddatepicker.components.DatePicker;

import model.DateException;

public interface AdministratorChartsController {
    
    public void onButtonPressed(DatePicker dateStart,DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException;
    
    public void resetChart(XYChart chart, JPanel panel);
    
    
}
