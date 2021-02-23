package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import controller.Company;
import controller.CompanyImpl;
import controller.backupFile.SaveStatistics;
import model.Appointments;
import model.AppointmentsImpl;
import model.users.Clients;
import utility.ConstantsCleanSvc;
import utility.PopUp;

public class NewAppointmentView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private JComboBox<String> comboClients;
    private final JButton btnSubmit;
    private final JButton btnHome;
    private final DatePicker datepicker;
    private final TimePicker timepicker;
    private Company company = CompanyImpl.getInstance();
    private List<Clients> clientsList = company.getClients();
    private PopUp popUp = new PopUp();

    public NewAppointmentView() {

        setTitle(ConstantsCleanSvc.TITLE);
        setMinimumSize(new Dimension(ConstantsCleanSvc.WIDTH, ConstantsCleanSvc.HEIGHT));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_TITLE_HEIGHT));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        JLabel lblTitle = new JLabel("Nuovo Appuntamento");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(ConstantsCleanSvc.FONT_TITLE);
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(ConstantsCleanSvc.BTN_HOME_WIDTH, ConstantsCleanSvc.BTN_HOME_HEIGHT));
        btnHome.setFont(ConstantsCleanSvc.FONT);
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                HomeView hv = new HomeView();
                hv.display();
                dispose();
            }
        });
        panelTitle.add(btnHome, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(ConstantsCleanSvc.BORDERLAYOUT0, ConstantsCleanSvc.BORDERLAYOUT0));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setMinimumSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNL_SEARCH_HEIGHT));
        mainPanel.add(pnlSubmit, BorderLayout.NORTH);

        JLabel labelCliente = new JLabel("Cliente:");
        labelCliente.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(labelCliente);

        comboClients = new JComboBox<>();
        comboClients.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        comboClients.setToolTipText("Cliente");
        comboClients.setBackground(SystemColor.inactiveCaption);
        comboClients.setForeground(SystemColor.textText);
        comboClients.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(comboClients);
        Clients cc;
        for (int i = 0; i < clientsList.size(); i++) {
            cc = company.getClients().get(i);
            comboClients.addItem(cc.getName() + " " + cc.getCFPIVA().toUpperCase());
        }

        JLabel labelDatePicker = new JLabel("Data:");
        labelDatePicker.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(labelDatePicker);

        datepicker = new DatePicker();
        datepicker.getComponentToggleCalendarButton().setForeground(SystemColor.textText);
        datepicker.getComponentDateTextField().setToolTipText("Data dell'appuntamento");
        datepicker.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepicker.getComponentDateTextField().setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(datepicker);

        JLabel labelTimePicker = new JLabel("Orario:");
        labelTimePicker.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(labelTimePicker);

        timepicker = new TimePicker();
        timepicker.getComponentToggleTimeMenuButton().setForeground(SystemColor.textText);
        timepicker.getComponentTimeTextField().setToolTipText("Orario dell'appuntamento");
        timepicker.getComponentToggleTimeMenuButton().setBackground(SystemColor.activeCaption);
        timepicker.getComponentTimeTextField().setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(timepicker);

        btnSubmit = new JButton("Conferma");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(ConstantsCleanSvc.FONT);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Clients c = company.getClients().get(comboClients.getSelectedIndex());
                    Appointments a = new AppointmentsImpl(getDate(), getHour(), c);
                    if (company.searchAppointment(a.getDate(), a.getHour()).isEmpty()) {
                        if (datepicker.getDate().isBefore(LocalDate.now())
                                || ((datepicker.getDate().equals(LocalDate.now()) && (!timepicker.getTime().isAfter(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)))))) {
                            popUp.popUpErrorOrMissing();
                        } else {
                            company.addAppointment(a);
                            popUp.popUpInfo("Appuntamento inserito con successo.");
                            new AppointmentsView().display();
                            //Qui richiama la SaveStatistics.save()
                            /*
                             * testing
                                new SaveStatistics().save(datepicker.getDate(), timepicker.getTime().getMinute(), 100);
                            */
                            setVisible(false);
                        }
                    } else {
                        popUp.popUpError("Data e ora già prenotate");
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlSubmit.add(btnSubmit);
        mainPanel.add(pnlSubmit);

    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }

    public Boolean missingField() {
        return (getDate().isEmpty() || getHour().isEmpty());
    }

    public String getDate() {
        return datepicker.getDateStringOrEmptyString();
    }

    public String getHour() {
        return timepicker.getText();
    }
}
