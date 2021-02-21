package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;

public class DataChartsImpl implements DataCharts {
     
    private final static Integer DAY = 1;
    //private static final String SEP = System.getProperties().getProperty(File.pathSeparator);
    
    public List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose) {       
        try {
                if(choose.equals(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex())) {
                return this.getTempoLavoro(dateStart, dateEnd);
        }
        
        else if(choose.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex())){
              return this.getEntrate(dateStart, dateEnd);
        }
          
        } catch(IllegalArgumentException e) {
          JOptionPane.showMessageDialog(new JPanel(), "Input non valido");
          }
        return null;
    }
     
    //Qui devi implementare delle query, conta i giorni dall'inizio alla fine e per ogni giorno assegna la sua entrata,
    //aggiungendola ad una lista che verrà aggiunta al grafico
    private List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd){
        
        List<Double> entrateList = new ArrayList<>();
        Double money = 13.70;
        LocalDate auxDate = dateStart;
        entrateList.add(money);
        while(!auxDate.isEqual(dateEnd)) {
            auxDate = auxDate.plusDays(DataChartsImpl.DAY);
            money++;
            entrateList.add(money);
        }
        
        return entrateList;
    }
    
    private List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd){
        
        List<Double> lavoroList = new ArrayList<>();
        LocalDate auxDate = dateStart;
        Double job = 8.0;
        lavoroList.add(job);
        
        while(!auxDate.isEqual(dateEnd)) {
            auxDate = auxDate.plusDays(DataChartsImpl.DAY);
            job++;
            lavoroList.add(job);
        }
        return lavoroList;
    }
    
    //Siccome ad ogni giorno corrisponde un tipo di dato che bisogna tracciare è necessario quindi calcolarsi ogni giorno fino allda dateEnd
    // per poterli poi associare al tipo di dato nel rispettivo giorno
    public List<Date> getDaysDate(final LocalDate dateStart, final LocalDate dateEnd){
        List<Date> successiveDate = new ArrayList<>();    //necessita di convertire da localDate a date perché XYChart non accetta LocalDate;
        LocalDate auxDate = dateStart;
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        
        Date date;
        try {
                date = sdf.parse(new String(auxDate.toString()));
                successiveDate.add(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        while(!auxDate.isEqual((dateEnd))) {                        //Creo una lista di giorni da aggiungere poi al grafico
           auxDate = auxDate.plusDays(DataChartsImpl.DAY);
           try {
               date = sdf.parse(new String(auxDate.toString()));
               successiveDate.add(date);
           } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          
        }
        return successiveDate;
    }

    @Override
    public void deleteLastItem(XYChart chart) {   //cancella l'ultimo elemento, da migliorare
        // TODO Auto-generated method stub
        List<String> list = new ArrayList<>();
        System.out.println(chart.getSeriesMap().keySet());
        list.addAll(chart.getSeriesMap().keySet().stream().collect(Collectors.toList()));
        list.remove(list.size()-1);
        chart.getSeriesMap().keySet().retainAll(list);
    
    }

}