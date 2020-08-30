package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.time.LocalDate;
import java.util.Date;

import model.DateException;
import model.DatiDaVisualizzareEnum;
import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;
import controllers.AdministratorChartsControllerImpl;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;

public class AdministratorChartsView extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -800518568134501851L;
    private static final double PERCENT = 0.6;
    private static final String TITLE = "Grafici amministratore";
    final AdministratorChartsControllerImpl ctrl;
    
    public AdministratorChartsView() {   
       
        this.ctrl = new AdministratorChartsControllerImpl();
        setTitle(AdministratorChartsView.TITLE);
        setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(10, 100));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));
        
        JLabel lblTitle = new JLabel("Finestra Grafici");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(Color.DARK_GRAY);
        lblTitle.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
        panelTitle.add(lblTitle, BorderLayout.NORTH);
        
        JLabel lblNewLabel = new JLabel("Seleziona la data e il tipo di dato d'interesse per visualizzare l'andamento nell'intervallo di tempo richiesto.");
        lblNewLabel.setForeground(Color.DARK_GRAY);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        panelTitle.add(lblNewLabel, BorderLayout.SOUTH);
        
        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        final JPanel datePanel = new JPanel();
        datePanel.setBorder(new TitledBorder(null, "Data e tipo dato", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        datePanel.setBackground(SystemColor.window);
        datePanel.setPreferredSize(new Dimension(10, 100));
        datePanel.setMinimumSize(new Dimension(10, 100));
        mainPanel.add(datePanel, BorderLayout.NORTH);
        
        JLabel labelDatePickerFirst = new JLabel("Seleziona una data di partenza:");
        labelDatePickerFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePanel.add(labelDatePickerFirst);
        
        DatePicker datepickerFirst = new DatePicker();
        datepickerFirst.getComponentToggleCalendarButton().setForeground(UIManager.getColor("text"));
        datepickerFirst.getComponentDateTextField().setToolTipText("Seleziona data di partenza per dati");
        datepickerFirst.getComponentToggleCalendarButton().setBackground(SystemColor.textHighlight);
        datepickerFirst.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(datepickerFirst);
        
        JLabel labelDatePickerLast = new JLabel("Seleziona una data di arrivo:");
        labelDatePickerLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePanel.add(labelDatePickerLast);
        
        DatePicker datepickerLast = new DatePicker();
        datepickerLast.getComponentToggleCalendarButton().setForeground(UIManager.getColor("text"));
        datepickerLast.getComponentDateTextField().setToolTipText("Seleziona data d'arrivo per i dati");
        datepickerLast.getComponentToggleCalendarButton().setBackground(SystemColor.textHighlight);
        datepickerLast.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(datepickerLast);
        
        JComboBox<String> comboBoxDatiGrafico = new JComboBox<>();
        comboBoxDatiGrafico.setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(comboBoxDatiGrafico);
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex()+ " " + DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName());
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.ENTRATE.getIndex()+ " " + DatiDaVisualizzareEnum.ENTRATE.getItemName());

        JButton btnVisualizzaDati = new JButton("Visualizza Dati");
        btnVisualizzaDati.setForeground(UIManager.getColor("text"));
        btnVisualizzaDati.setBackground(new Color(51, 153, 255));
        btnVisualizzaDati.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
       
        datePanel.add(btnVisualizzaDati);
        
        JButton btnReset = new JButton("Elimina ultima linea");
        btnReset.setForeground(SystemColor.text);
        btnReset.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnReset.setBackground(new Color(51, 153, 255));
        datePanel.add(btnReset);
        
        
        XYChart chart = new XYChartBuilder().title("DATI VISUALIZZATI").theme(ChartTheme.GGPlot2).build();
        this.setChartStyle(chart);
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        
        btnVisualizzaDati.addActionListener(e->{
               
            try {
                ctrl.onButtonPressed(datepickerFirst, datepickerLast, (comboBoxDatiGrafico.getSelectedIndex())+1, mainPanel,chart);
            } catch (DateException dateExc) {
                // TODO Auto-generated catch block
                   System.out.println(dateExc.message());
                   }
       
        });
        
        btnReset.addActionListener(e->{
            ctrl.resetChart(chart, chartPanel);
        });
    }
    
    public void display() {
       Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
       setSize((int) (dimension.getWidth()*(AdministratorChartsView.PERCENT)) , (int)(dimension.getHeight() * AdministratorChartsView.PERCENT));
       setVisible(true);
       setResizable(true);
    }
    
    private void setChartStyle(XYChart chart) {
        chart.setXAxisTitle("Data");
        chart.getStyler().setChartBackgroundColor(SystemColor.window)
                            .setChartTitleBoxBackgroundColor(SystemColor.activeCaption)
                            .setChartTitlePadding(10)
                            .setLegendBackgroundColor(SystemColor.activeCaption)
                            .setLegendPosition(LegendPosition.OutsideE);
        chart.getStyler().setAxisTickLabelsColor(Color.lightGray)
                            .setAxisTickPadding(10); 
        chart.getStyler().setPlotBackgroundColor(SystemColor.window);  
        chart.getStyler().setPlotGridLinesColor(Color.lightGray);
        chart.getStyler().setAxisTicksMarksVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setYAxisTicksVisible(false);
        chart.getStyler().setDecimalPattern("#");
        chart.getStyler().setDatePattern("dd/MMM/yyyy");
 }
}
