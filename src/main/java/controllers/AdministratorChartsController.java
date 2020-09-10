package controllers;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;
import com.github.lgooddatepicker.components.DatePicker;
import model.ChartException;
import model.DateException;

public interface AdministratorChartsController {
    
   void onButtonPressed(DatePicker dateStart,DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException;
    
   void resetChart(XYChart chart, JPanel panel) throws ChartException;
    
   void deleteLast(XYChart chart, JPanel panel) throws ChartException;
    
    
}
