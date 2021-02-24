package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.XYChart;

public interface DataCharts {

     List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose);
     
     List<Date> getDateList();

     void deleteLastItem(XYChart chart);
     


}
