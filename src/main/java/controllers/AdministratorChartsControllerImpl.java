package controllers;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.knowm.xchart.XYChart;

import com.github.lgooddatepicker.components.DatePicker;

import model.DataCharts;

public class AdministratorChartsControllerImpl implements AdministratorChartsController{
    
    public AdministratorChartsControllerImpl() {
        
    }
        /*Ricordati di gestire quando la data di arrivo e maggiore della data di partenza!*/
    
    @Override
    public void onButtonPressed(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) {
      
        // TODO Auto-generated method stub 
        LocalDate dataPartenza, dataArrivo;
        Integer scelta = choice;      
            if((!dateStart.getText().isBlank() || !dateStart.getText().isEmpty())
                    || (!dateEnd.getText().isBlank() || !dateEnd.getText().isBlank())) {
                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();
                    if(dataArrivo.isBefore(dataPartenza))  
                        JOptionPane.showMessageDialog(panel, "Impossibile viaggiare nel tempo, la data di partenza è prima della data di arrivo");
        
                DataCharts dataChart = new DataCharts();
                chart.addSeries(new String("Da:" + String.valueOf(dataPartenza.getDayOfMonth())  + ", a: " + String.valueOf(dataArrivo.getDayOfMonth())),
                                    dataChart.getDaysDate(dataPartenza, dataArrivo), dataChart.buildChartsFromData(dataPartenza, dataArrivo, scelta));
            
               } else
                JOptionPane.showMessageDialog(panel, "Inserisci data valida!");
    }


    
    
}
