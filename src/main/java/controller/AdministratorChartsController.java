package controller;
import javax.swing.JPanel;

import org.knowm.xchart.XYChart;
import com.github.lgooddatepicker.components.DatePicker;
import model.ChartException;
import model.DateException;

public interface AdministratorChartsController {
    /**
     * 
     * @param dateStart
     * @param dateEnd
     * @param choice
     * @param panel
     * @param chart
     * @throws DateException
     */
   void addLine(DatePicker dateStart,DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException;
    /**
     * 
     * @param chart
     * @param panel
     * @throws ChartException
     */
   void resetChart(XYChart chart, JPanel panel) throws ChartException;
    /**
     * 
     * @param chart
     * @param panel
     * @throws ChartException
     */
   void deleteLast(XYChart chart, JPanel panel) throws ChartException;
   
    
}
