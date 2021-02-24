package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.knowm.xchart.XYChart;

public class DataChartsImpl implements DataCharts {
     
    private static final String SEP = File.separator;
    private static final String FILE = "doc" + SEP + "Statistics.txt";
    private ArrayList<LocalDate> listToReturn;
    
    public DataChartsImpl() {
        this.listToReturn = new ArrayList<>();
    }
    
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
        
        if(!this.listToReturn.isEmpty()) {
            this.listToReturn.clear();
        }
        List<LocalDate> auxLocalDateList = new ArrayList<>();
        List<Double> entrateList = new ArrayList<>();
        LocalDate auxDate = null;
        String line = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(DataChartsImpl.FILE))){
            while( (line = reader.readLine()) != null) {
                auxDate = LocalDate.parse(line.substring(line.indexOf("Date: ")+7, line.indexOf(" Minuti")));
                System.out.println(auxDate + "from getEntrate");
                if(dateStart.equals(auxDate) || (auxDate.isAfter(dateStart) && auxDate.isBefore(dateEnd)) || auxDate.isEqual(dateEnd)) {
                    //Debug System.out.println("entro in aggiunta");
                    entrateList.add(Double.parseDouble(line.substring(line.indexOf("Entrate: ")+9)));
                    auxLocalDateList.add(auxDate);
                    //Debug System.out.println("Aggiunto");
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.listToReturn.addAll(auxLocalDateList);
        return entrateList;
    }
    
    private List<Double> getTempoLavoro(LocalDate dateStart, LocalDate dateEnd){
        if(!this.listToReturn.isEmpty()) {
            this.listToReturn.clear();
        }
        List<LocalDate> auxLocalDateList = new ArrayList<>();
        List<Double> lavoroList = new ArrayList<>();
        LocalDate auxDate = null;
        String line = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(DataChartsImpl.FILE))){
            while( (line = reader.readLine()) != null) {
                auxDate = LocalDate.parse(line.substring(line.indexOf("Date: ")+7, line.indexOf(" Minuti")));
                System.out.println(auxDate + "from getTempoLavoro");
            if(dateStart.equals(auxDate) || (auxDate.isAfter(dateStart) && auxDate.isBefore(dateEnd)) || auxDate.isEqual(dateEnd)) {
                    //Debug System.out.println("entro in aggiunta");
                    lavoroList.add(Double.parseDouble(line.substring(line.indexOf("Minuti: ")+8, line.indexOf(" Entrate: "))));
                    auxLocalDateList.add(auxDate);
                    /* Debug*/ System.out.println("Aggiunto");
                }

            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        this.listToReturn.addAll(auxLocalDateList);
        return lavoroList;
    }
    
    @Override
    public List<Date> getDateList() {
        List<Date> successiveDate = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date;
        for(LocalDate localDate : this.listToReturn) {
            try {
                date = sdf.parse(new String(localDate.toString()));
                successiveDate.add(date);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return successiveDate;
    }
    
    @Override
    public void deleteLastItem(XYChart chart) {
        // TODO Auto-generated method stub
        List<String> list = new ArrayList<>();
        System.out.println(chart.getSeriesMap().keySet());
        list.addAll(chart.getSeriesMap().keySet().stream().collect(Collectors.toList()));
        list.remove(list.size()-1);
        chart.getSeriesMap().keySet().retainAll(list);
        if(chart.getSeriesMap().isEmpty()) {
            chart.getStyler().setXAxisTicksVisible(false);
            chart.getStyler().setYAxisTicksVisible(false);
        }
    }
    
    public String newLegendString(String dataArr, String dataPar, Integer scelta) {
        String choose = scelta.equals(DatiDaVisualizzareEnum.ENTRATE.getIndex()) ?  DatiDaVisualizzareEnum.ENTRATE.getItemName()
                                                                                 : DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName();
        return new String("Da: " + dataPar + " a: " + dataArr + ", "+ choose);
    }
}