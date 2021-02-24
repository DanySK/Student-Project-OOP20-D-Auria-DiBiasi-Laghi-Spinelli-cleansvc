package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.XYChart;

public interface DataCharts {
    /**
     * 
     * @param dateStart
     * @param dateEnd
     * @param choose
     * @return
     */
     List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose);
     /**
      * 
      * @param dateStart
      * @param dateEnd
      * @return
      */
     List<Double> getEntrate(LocalDate dateStart, LocalDate dateEnd);
     /**
      * 
      * @param dateStart
      * @param dateEnd
      * @return
      */
     List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd);
     /**
      * 
      * @return
      */
     List<Date> getDateList();
     /**
      * 
      * @param chart
      */
     void deleteLastItem(XYChart chart);
     
}
