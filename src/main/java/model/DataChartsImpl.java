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
    
    public DataChartsImpl() {
        
    }
    
    //Utilizzo localDate perché non mi interessa l'ora. 
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

    private List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd){
        
        List<Double> entrateList = new ArrayList<>();
        Double money = 0.0;
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
        Double job = 8.5;
        lavoroList.add(job);
        while(!auxDate.isEqual(dateEnd)) {
            auxDate = auxDate.plusDays(DataChartsImpl.DAY);
            job++;
            lavoroList.add(job);
        }
        return lavoroList;
    }
    
    public List<Date> getDaysDate(LocalDate dateStart, LocalDate dateEnd){
        List<Date> successiveDate = new ArrayList<>();    //necessita di convertire da localDate a te perché XYChart non accetta LocalDate;
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
        //successiveDate.add(Date.valueOf(auxDate));
        
        while(!auxDate.isEqual((dateEnd))) {
           auxDate = auxDate.plusDays(DataChartsImpl.DAY);
           try {
               date = sdf.parse(new String(auxDate.toString()));
               successiveDate.add(date);
           } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
          
           //successiveDate.add(Date.valueOf(auxDate));
        }
        return successiveDate;
    }

    @Override
    public void deleteLastItem(XYChart chart) {
        // TODO Auto-generated method stub
        List<String> list = new ArrayList<String>();
        System.out.println(chart.getSeriesMap().keySet());
        list.addAll(chart.getSeriesMap().keySet().stream().collect(Collectors.toList()));
        list.remove(list.size()-1);
        chart.getSeriesMap().keySet().retainAll(list);
    }

}