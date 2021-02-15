package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class AppointmentsView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private static final String TITLE = "Appuntamenti";

    private JTextField txtCFPIVA;
    private JTextField txtName;
    /*JTextField txtAddress;
    JTextField txtCity;
    JTextField txtCAP;
    JTextField txtmq;
    JTextField txttel;
    JTextField txtemail;*/
    private final JButton btnSubmit;
    private final JButton btnHome;

    public AppointmentsView() {

        setTitle(AppointmentsView.TITLE);
        setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 20));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Richieste sanificazione");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE,20));
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(120,20));
        btnHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }
        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        //panelTable.add(panelTitle, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 100));
        pnlSubmit.setMinimumSize(new Dimension(1000, 100));
        mainPanel.add(pnlSubmit, BorderLayout.NORTH);

        JLabel labelCFPIVA = new JLabel("CF/P.IVA:");
        labelCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCFPIVA);

        txtCFPIVA = new JTextField(20);
        txtCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtCFPIVA);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelName);

        txtName = new JTextField(20);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtName);

        JLabel labelDatePicker = new JLabel("Seleziona una data:");
        labelDatePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelDatePicker);

        DatePicker datepicker = new DatePicker();
        datepicker.getComponentToggleCalendarButton().setForeground(SystemColor.textText);
        datepicker.getComponentDateTextField().setToolTipText("Seleziona data dell'appuntamento");
        datepicker.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepicker.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(datepicker);

        JLabel labelTimePicker = new JLabel("Seleziona un orario:");
        labelTimePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelTimePicker);

        TimePicker timepicker = new TimePicker();
        timepicker.getComponentToggleTimeMenuButton().setForeground(SystemColor.textText);
        timepicker.getComponentTimeTextField().setToolTipText("Seleziona orario dell'appuntamento");
        timepicker.getComponentToggleTimeMenuButton().setBackground(SystemColor.activeCaption);
        timepicker.getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(timepicker);

        btnSubmit = new JButton("Inserisci");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                // TODO Auto-generated method stub
            }
        });
        pnlSubmit.add(btnSubmit);
        mainPanel.add(pnlSubmit);

        JPanel calendarPanel = new JPanel();
        getContentPane().add(calendarPanel, BorderLayout.SOUTH);
        calendarPanel.setLayout(new BorderLayout(0, 0));

        /*String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Struttura_mq", "Telefono", "Email", "CF_PIVA"};
        Object[][] data = new Object[][] {
            {"Unibo", "Via dell'università 50", "Cesena", "47522", "900", "0088338550", "unibo@unibo.it", "0385719047300"}, 
            {"Mario Rossi", "Via degli omonimi 33", "Meldola", "47014", "150", "95947524022", "mario.rossi@oulook.it", "MRORSS77M45D706Y"}
        };

        JTable table = new JTable(data, cols);
        table.setPreferredScrollableViewportSize(new Dimension(500, 310));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        calendarPanel.add(table, BorderLayout.CENTER);
        calendarPanel.add(new JScrollPane(table));*/
    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }

}
