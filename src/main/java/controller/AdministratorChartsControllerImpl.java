package controller;

import java.time.LocalDate;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.markers.SeriesMarkers;
import com.github.lgooddatepicker.components.DatePicker;
import model.ChartException;
import model.DataChartsImpl;
import model.DateException;
import model.DatiDaVisualizzareEnum;


public class AdministratorChartsControllerImpl implements AdministratorChartsController{
    private final ChartException chExc;
    private final DateException dateExc;


    public AdministratorChartsControllerImpl() {
        this.chExc = new ChartException();
        this.dateExc= new DateException();
    }
    
    @Override
    public void onButtonPressed(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel, XYChart chart) throws DateException {
      
        // TODO Auto-generated method stub 
        final LocalDate dataPartenza, dataArrivo;
        final Integer scelta = choice;
        try {
                if((dateStart.getText().isBlank() || dateStart.getText().isEmpty())
                        || (dateEnd.getText().isBlank() || dateEnd.getText().isBlank())) {  //lancia un warning se le date sono vuote
                    this.dateExc.warning(panel);
                    throw this.dateExc;
                }    
                
                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();
                    
                if(dataArrivo.isBefore(dataPartenza)) {
                        this.dateExc.warning(panel);            //lancia un warning se la data di arrivo è prima della data di partenza
                        throw this.dateExc;
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
                JOptionPane.showMessageDialog(panel, "Formato dati non valido, riprova.");
                throw e;
            }
        }
    
    
    public void resetChart(XYChart chart, JPanel panel) throws ChartException {        //elimina il grafico.   
        if(chart.getSeriesMap().isEmpty()) {
            this.chExc.warning(panel); 
            throw chExc;
        }
        
                        chart.getSeriesMap().clear();
                        panel.revalidate();
                        panel.repaint();
     
    }


    public void deleteLast(XYChart chart, JPanel panel) throws ChartException {
        if(chart.getSeriesMap().isEmpty()) {
            this.chExc.warning(panel); 
            throw chExc;
            
        }
            new DataChartsImpl().deleteLastItem(chart);
               panel.revalidate();
               panel.repaint();   
    }
       

    private String newLegendString(String dataArr, String dataPar, Integer scelta) {
        String choose = scelta.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex()) ?  DatiDaVisualizzareEnum.ENTRATE.getItemName()
                                                                                 : DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName();
        return new String("Da: " + dataPar + " a: " + dataArr + ", "+ choose);
    }
    
}
