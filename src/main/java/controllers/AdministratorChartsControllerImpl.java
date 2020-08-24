package controllers;

import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;

import com.github.lgooddatepicker.components.DatePicker;

public class AdministratorChartsControllerImpl implements AdministratorChartsController{
    
    public AdministratorChartsControllerImpl() {
        
    }
        /*Ricordati di gestire quando la data di arrivo e maggiore della data di partenza!*/
    
    @Override
    public void onButtonPressed(DatePicker dateStart, DatePicker dateEnd, int choice, JPanel panel) {
      
        // TODO Auto-generated method stub 
        LocalDate dataPartenza, dataArrivo;
        Integer scelta = choice;
        try {       
            if((!dateStart.getText().isBlank() || !dateStart.getText().isEmpty())
                    || (!dateEnd.getText().isBlank() || !dateEnd.getText().isBlank())) {
                dataPartenza = dateStart.getDate();
                dataArrivo = dateEnd.getDate();
                    if(dataArrivo.isBefore(dataPartenza))  
                        JOptionPane.showMessageDialog(panel, "Impossibile viaggiare nel tempo, la data di partenza è prima della data di arrivo");
                
            } else
                JOptionPane.showMessageDialog(panel, "Inserisci data valida!");

        }catch(DataValidationException e) {
            e.printStackTrace();
        }
    }


    
    
}
