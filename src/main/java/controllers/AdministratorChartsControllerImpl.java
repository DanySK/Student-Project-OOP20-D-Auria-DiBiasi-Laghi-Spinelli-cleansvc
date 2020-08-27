package controllers;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import com.github.lgooddatepicker.components.DatePicker;
import model.DataCharts;
public class AdministratorChartsControllerImpl implements AdministratorChartsController{
    
    public AdministratorChartsControllerImpl() {
        
    }
    
    @Override
    public void onButtonPressed(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) {
      
        // TODO Auto-generated method stub 
        LocalDate dataPartenza, dataArrivo;
        Integer scelta = choice; try {
            if((!dateStart.getText().isBlank() || !dateStart.getText().isEmpty())
                    || (!dateEnd.getText().isBlank() || !dateEnd.getText().isBlank())) {
                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();
                    if(dataArrivo.isBefore(dataPartenza))  
                        JOptionPane.showMessageDialog(panel, "Impossibile viaggiare nel tempo, la data di partenza è prima della data di arrivo");
        
                DataCharts dataChart = new DataCharts();
                chart.addSeries( this.newLegendString(dataArrivo.toString(), dataPartenza.toString(), scelta), dataChart.getDaysDate(dataPartenza, dataArrivo), dataChart.buildChartsFromData(dataPartenza, dataArrivo, scelta));
                panel.revalidate();
                panel.repaint();
            } else
                JOptionPane.showMessageDialog(panel, "Inserisci data valida!");
        }
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(panel, "Formato non valido, riprova.");
            }
        }

    private String newLegendString(String dataArr, String dataPar, int scelta) {
        return new String("Da: " + dataArr + " a: " + dataPar + ", "+ scelta);
    }
    
}
