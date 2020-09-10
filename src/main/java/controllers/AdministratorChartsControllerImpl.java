package controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.markers.SeriesMarkers;
import com.github.lgooddatepicker.components.DatePicker;
import model.DataCharts;
import model.DataChartsImpl;
import model.DateException;
import model.DatiDaVisualizzareEnum;


public class AdministratorChartsControllerImpl implements AdministratorChartsController{
    
    public AdministratorChartsControllerImpl() {
        
    }
    
    @Override
    public void onButtonPressed(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException {
      
        // TODO Auto-generated method stub 
        LocalDate dataPartenza, dataArrivo;
        Integer scelta = choice;
        DateException dateExc = new DateException(panel);
        try {
            if((dateStart.getText().isBlank() || dateStart.getText().isEmpty())
                    || (dateEnd.getText().isBlank() || dateEnd.getText().isBlank())) {
                dateExc.warning(panel);
                throw dateExc;
            }    
                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();
                    
                    if(dataArrivo.isBefore(dataPartenza)) {
                        dateExc.warning(panel);
                        throw dateExc;
                    }
        
                DataChartsImpl dataChart = new DataChartsImpl();
                
                chart.addSeries( this.newLegendString(dataArrivo.toString(), dataPartenza.toString(), scelta),
                                        dataChart.getDaysDate(dataPartenza, dataArrivo), 
                                            dataChart.buildChartsFromData(dataPartenza, dataArrivo, scelta)).setMarker(SeriesMarkers.NONE);
                chart.getStyler().setXAxisTicksVisible(true);
                chart.getStyler().setYAxisTicksVisible(true);

                panel.revalidate();
                panel.repaint();
        }       
            catch(IllegalArgumentException e){
                JOptionPane.showMessageDialog(panel, "Formato non valido, riprova.");
                throw e;
            }
        }
    
    public void resetChart(XYChart chart, JPanel panel) {           
                        chart.getSeriesMap().clear();
                        panel.revalidate();
                        panel.repaint();
    }


    public void deleteLast(XYChart chart, JPanel panel) {
            try{    
           new DataChartsImpl().deleteLastItem(chart);
           panel.revalidate();
           panel.repaint();
            } catch(IndexOutOfBoundsException e) {
                if(chart.getSeriesMap().isEmpty()) {
                    new JOptionPane();
                    JOptionPane.showMessageDialog(panel, "Nessun elemento da cancellare nel grafico!");
                }
            }
    }
       

    private String newLegendString(String dataArr, String dataPar, Integer scelta) {
        String choose = scelta.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex()) ?  DatiDaVisualizzareEnum.ENTRATE.getItemName()
                                                                                     : DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName();
        return new String("Da: " + dataPar + " a: " + dataArr + ", "+ choose);
    }
    
}
