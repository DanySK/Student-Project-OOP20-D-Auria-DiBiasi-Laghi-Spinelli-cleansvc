package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import model.DatiDaVisualizzareEnum;
import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;

import controllers.AdministratorChartsControllerImpl;

import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.ChartTheme;
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
    private static final AdministratorChartsControllerImpl ctrl = new AdministratorChartsControllerImpl();
    public AdministratorChartsView() {
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
        
        JPanel DatePanel = new JPanel();
        DatePanel.setBorder(new TitledBorder(null, "Data e tipo dato", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        DatePanel.setBackground(SystemColor.window);
        DatePanel.setPreferredSize(new Dimension(10, 100));
        DatePanel.setMinimumSize(new Dimension(10, 100));
        mainPanel.add(DatePanel, BorderLayout.NORTH);
        
        JLabel labelDatePickerFirst = new JLabel("Seleziona una data di partenza:");
        labelDatePickerFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        DatePanel.add(labelDatePickerFirst);
        
        DatePicker datepickerFirst = new DatePicker();
        datepickerFirst.getComponentToggleCalendarButton().setForeground(UIManager.getColor("text"));
        datepickerFirst.getComponentDateTextField().setToolTipText("Seleziona data di partenza per dati");
        datepickerFirst.getComponentToggleCalendarButton().setBackground(SystemColor.textHighlight);
        datepickerFirst.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        DatePanel.add(datepickerFirst);
        
        JLabel labelDatePickerLast = new JLabel("Seleziona una data di arrivo:");
        labelDatePickerLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        DatePanel.add(labelDatePickerLast);
        
        DatePicker datepickerLast = new DatePicker();
        datepickerLast.getComponentToggleCalendarButton().setForeground(UIManager.getColor("text"));
        datepickerLast.getComponentDateTextField().setToolTipText("Seleziona data d'arrivo per i dati");
        datepickerLast.getComponentToggleCalendarButton().setBackground(SystemColor.textHighlight);
        datepickerLast.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        DatePanel.add(datepickerLast);
        
        JComboBox<String> comboBoxDatiGrafico = new JComboBox<>();
        comboBoxDatiGrafico.setFont(new Font("Tahoma", Font.PLAIN, 13));
        DatePanel.add(comboBoxDatiGrafico);
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex()+ " " + DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName());
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.ENTRATE.getIndex()+ " " + DatiDaVisualizzareEnum.ENTRATE.getItemName());

        JButton btnVisualizzaDati = new JButton("Visualizza Dati");
        btnVisualizzaDati.setForeground(UIManager.getColor("text"));
        btnVisualizzaDati.setBackground(SystemColor.textHighlight);
        btnVisualizzaDati.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnVisualizzaDati.addActionListener(e->{
            ctrl.onButtonPressed(datepickerFirst, datepickerLast, 0, mainPanel); //////CONTROLLA 0, bisogna passare la scelta del combobox!
        });
        DatePanel.add(btnVisualizzaDati);
        
        
        XYChart chart = new XYChartBuilder().title("Dati visualizzati").theme(ChartTheme.GGPlot2).build();
        this.setChartStyle(chart);
        JPanel chartPanel = new XChartPanel<XYChart>(chart);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        
    }
    
    public void display() {
       Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
       setSize((int) (dimension.getWidth()*(AdministratorChartsView.PERCENT)) , (int)(dimension.getHeight() * AdministratorChartsView.PERCENT));
       setVisible(true);
       setResizable(true);
    }
    
    private void setChartStyle(XYChart chart) {
        final double minValue= 0;
        final int padding = 10;
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setPlotGridLinesColor(Color.LIGHT_GRAY)
                            .setXAxisTickLabelsColor(Color.DARK_GRAY)
                              .setYAxisTickLabelsColor(Color.DARK_GRAY)
                                 .setXAxisTickMarksColor(Color.LIGHT_GRAY)
                                   .setYAxisTickMarksColor(Color.LIGHT_GRAY);
        chart.getStyler().setXAxisMin(minValue)
                            .setYAxisMin(minValue);
        chart.getStyler().setChartTitleBoxBackgroundColor(SystemColor.activeCaption)
                            .setChartTitleBoxBorderColor(Color.WHITE)
                                .setChartTitlePadding(padding);
        
        chart.getStyler().setChartFontColor(Color.BLACK);
        return;
    }
}
