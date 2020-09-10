package model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.knowm.xchart.XYChart;

public interface DataCharts {

    public List<Double> buildChartsFromData(LocalDate dateStart, LocalDate dateEnd, Integer choose);
    
    public List<Date> getDaysDate(LocalDate dateStart, LocalDate dateEnd);

    public void deleteLastItem(XYChart chart);

}
