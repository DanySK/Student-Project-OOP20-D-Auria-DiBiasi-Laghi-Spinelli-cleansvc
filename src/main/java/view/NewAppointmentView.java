package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

public class NewAppointmentView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private static final String TITLE = "Inserimento nuovo appuntamento";
    private JComboBox<String> users;
    private final JButton btnSubmit;
    private final JButton btnHome;

    public NewAppointmentView() {

        setTitle(NewAppointmentView.TITLE);
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

        users = new JComboBox<>();
        users.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        users.setToolTipText("CF o Partita IVA");
        users.setBackground(SystemColor.inactiveCaption);
        users.setForeground(SystemColor.textText);
        users.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(users);
        users.addItem("Mario Rossi Via degli omonimi 33");
        users.addItem("Luigi Bianchi Via dell'Università 50");


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
    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }

}