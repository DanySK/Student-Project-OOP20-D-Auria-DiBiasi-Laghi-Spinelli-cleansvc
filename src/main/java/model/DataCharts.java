package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataCharts {
     
    private final static Integer DAY = 1;
    public DataCharts() {
        
    }
    
    //Utilizzo localDate perché non mi interessa l'ora.
    public List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose) {
        if(choose.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex())){
           final List<Double> entrateList;
        return entrateList = this.getEntrate(dateStart, dateEnd);
        }
        else if(choose.equals(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex())) {
        return null;
    }
   return null;
}

private List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd){
    List<Double> entrateList = new ArrayList<>();
    
    for(int i= 1; i<10; i++) {
        entrateList.add((double)i);
    }
    
    return entrateList;
}

private List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd){
    
    return null;
}

public List<LocalDate> getDaysDate(LocalDate dateStart, LocalDate dateEnd){
    
    List<LocalDate> days = new ArrayList<>();
    days.add(dateStart);
    while(dateStart.equals(dateEnd)== false) {
        dateStart.plusDays(DataCharts.DAY);
        days.add(dateStart);
    }
    return days;
}

}