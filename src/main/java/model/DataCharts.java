package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataCharts {
     
    private final static Integer DAY = 1;
    
    public DataCharts() {
        
    }
    
    //Utilizzo localDate perché non mi interessa l'ora. 
    public List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose) {
        try {
            
          if(choose.equals(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex())) {
            
            return null;
        }
        
          else if(choose.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex())){
           final List<Double> entrateList = this.getEntrate(dateStart, dateEnd);
        return entrateList;
        }
        
      return null;
        } catch(IllegalArgumentException e) {
          JOptionPane.showMessageDialog(new JPanel(), "Input non valido");
      }
        return null;
}

private List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd){
    List<Double> entrateList = new ArrayList<>();
    Double money = 0.0;
    LocalDate auxDate = dateStart;
    while(!auxDate.isEqual(dateEnd)) {
        auxDate = auxDate.plusDays(DataCharts.DAY);
        money++;
        entrateList.add(money);
    }
    
    return entrateList;
}

private List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd){
    
    return null;
}

public List<Date> getDaysDate(LocalDate dateStart, LocalDate dateEnd){
    
    List<Date> successiveDate = new LinkedList<>();    //necessita di convertire da localDate a te perché XYChart non accetta LocalDate;
    LocalDate auxDate = dateStart;
    successiveDate.add(Date.valueOf(auxDate));

    while(!auxDate.isEqual((dateEnd))) {
       auxDate = auxDate.plusDays(DataCharts.DAY);
       successiveDate.add(Date.valueOf(auxDate));
    }
    return successiveDate;
}

}