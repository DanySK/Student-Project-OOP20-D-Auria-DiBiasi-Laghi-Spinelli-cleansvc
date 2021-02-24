package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;

import controller.CompanyImpl;
import controller.ProcessImpl;
import controller.backupFile.SaveStatistics;
import model.Appointments;
import model.AppointmentsImpl;
import model.Products;
import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;
import utility.ConstantsCleanSvc;
import utility.PopUp;
import utility.InputValidator;

public class NewAppointmentView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private JComboBox<String> comboClients;
    private final JButton btnSubmit;
    private final JButton btnConfirm;
    private final JButton btnHome;
    private final DatePicker datepicker;
    private final TimePicker timepicker;
    private JCheckBox check;
    private List<JCheckBox> checkboxs = new ArrayList<>();
    private JTextField txtStaffs;
    private CompanyImpl company = CompanyImpl.getInstance();
    private ProcessImpl process = ProcessImpl.getInstance();
    private List<Clients> clientsList = company.getClients();
    private PopUp popUp = new PopUp();
    private double totTime;
    private double totEarn;


    private JLabel labelTime;
    private JLabel labelEarn;
    private JLabel labelCleaning;
    private JLabel labelCleansing;
    private JLabel labelDisinfection;
    private JLabel labelDisinfestation;
    private JLabel labelConclusion;
    private JLabel labelStaffOnWork;

    private InputValidator validator = new InputValidator();
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

        pnlSubmit.setLayout(new GridLayout(ConstantsCleanSvc.GRID2, ConstantsCleanSvc.GRID6, ConstantsCleanSvc.GRID_10_GAP, ConstantsCleanSvc.GRID_20_GAP));
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

        for (StepType stepType : process.getStepTypeList()) {
            check = new JCheckBox(stepType.getType());
            check.setBackground(SystemColor.window);
            checkboxs.add(check);
            pnlSubmit.add(check);
        }

        checkboxs.get(0).setSelected(true);
        checkboxs.get(4).setSelected(true);
        checkboxs.get(4).setEnabled(false);

        JLabel labelStaff = new JLabel("Dipendenti:");
        labelStaff.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(labelStaff);

        txtStaffs = new JTextField(10);
        txtStaffs.setFont(ConstantsCleanSvc.FONT);
        pnlSubmit.add(txtStaffs);

        btnSubmit = new JButton("Mostra Riepilogo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(ConstantsCleanSvc.FONT);
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Clients c = company.getClients().get(comboClients.getSelectedIndex());
                    Appointments a = new AppointmentsImpl(getDate(), getHour(), c, totTime, totEarn);
                    if (company.searchAppointment(a.getDate(), a.getHour()).isEmpty()) {
                        if (datepicker.getDate().isBefore(LocalDate.now())
                                || ((datepicker.getDate().equals(LocalDate.now()) && (!timepicker.getTime().isAfter(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)))))) {
                            popUp.popUpErrorOrMissing();
                        } else {
                            setSummary();
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

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Riepilogo", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(ConstantsCleanSvc.PNLS_FULL_WIDTH, ConstantsCleanSvc.PNLS_FULL_HEIGHT));

       labelCleaning = new JLabel("Tempo per la fase di PULIZIA:");
        labelCleaning .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelCleaning);

        labelCleansing = new JLabel("Tempo per la fase di RISCIACQUO:");
        labelCleansing .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelCleansing);

        labelDisinfection = new JLabel("Tempo per la fase di DISINFEZIONE:");
        labelDisinfection .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelDisinfection);

        labelDisinfestation = new JLabel("Tempo per la fase di DISINFESTAZIONE:");
        labelDisinfestation .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelDisinfestation);

        labelConclusion = new JLabel("Tempo per la fase di CONCLUSIONE:");
        labelConclusion .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelConclusion);

        labelStaffOnWork = new JLabel("Dipendenti affidati al processo:");
        labelStaffOnWork .setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelStaffOnWork);

        labelTime = new JLabel("Tempo totale stimato:");
        labelTime.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelTime);

        labelEarn = new JLabel("Costo totale stimato:");
        labelEarn.setFont(ConstantsCleanSvc.FONT);
        pnlSearch.add(labelEarn);

        btnConfirm = new JButton("Fine");
        btnConfirm.setForeground(SystemColor.textText);
        btnConfirm.setBackground(SystemColor.activeCaption);
        btnConfirm.setFont(ConstantsCleanSvc.FONT);
        btnConfirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                new SaveStatistics().save(datepicker.getDate(), totTime, totEarn);
                popUp.popUpInfo("Appuntamento inserito con successo.");
                new AppointmentsView().display();
                setVisible(false);
            }
        });
        pnlSearch.add(btnConfirm);

        getContentPane().add(pnlSubmit, BorderLayout.CENTER);
        getContentPane().add(pnlSearch, BorderLayout.SOUTH);

        GroupLayout layout = new GroupLayout(pnlSubmit);
        pnlSubmit.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                       .addComponent(labelCliente)
                       .addComponent(comboClients)
                       .addComponent(labelDatePicker)
                       .addComponent(datepicker)
                       .addComponent(labelTimePicker)
                       .addComponent(timepicker))
               .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                       .addComponent(checkboxs.get(0))
                       .addComponent(checkboxs.get(1))
                       .addComponent(checkboxs.get(2))
                       .addComponent(checkboxs.get(3))
                       .addComponent(checkboxs.get(4))
                       .addComponent(labelStaff)
                       .addComponent(txtStaffs))
               .addGap(10)
               .addComponent(btnSubmit));

       layout.setHorizontalGroup(layout.createSequentialGroup()
               .addGroup(layout.createParallelGroup(Alignment.CENTER)
                       .addGroup(layout.createSequentialGroup()
                               .addComponent(labelCliente)
                               .addComponent(comboClients)
                               .addComponent(labelDatePicker)
                               .addComponent(datepicker)
                               .addComponent(labelTimePicker)
                               .addComponent(timepicker))
                       .addGroup(layout.createSequentialGroup()
                               .addComponent(checkboxs.get(0))
                               .addGap(ConstantsCleanSvc.PANEL_50_GAP)
                               .addComponent(checkboxs.get(1))
                               .addGap(ConstantsCleanSvc.PANEL_50_GAP)
                               .addComponent(checkboxs.get(2))
                               .addGap(ConstantsCleanSvc.PANEL_50_GAP)
                               .addComponent(checkboxs.get(3))
                               .addGap(ConstantsCleanSvc.PANEL_50_GAP)
                               .addComponent(checkboxs.get(4))
                               .addGap(100)
                               .addComponent(labelStaff)
                               .addComponent(txtStaffs))
                       .addComponent(btnSubmit)));

       GroupLayout layout2 = new GroupLayout(pnlSearch);
       pnlSearch.setLayout(layout2);
       layout2.setAutoCreateGaps(true);
       layout2.setAutoCreateContainerGaps(true);

       layout2.setVerticalGroup(layout2.createSequentialGroup()
               .addComponent(labelCleaning)
               .addGap(10)
               .addComponent(labelCleansing)
               .addGap(10)
               .addComponent(labelDisinfection)
               .addGap(10)
               .addComponent(labelDisinfestation)
               .addGap(10)
               .addComponent(labelConclusion)
               .addGap(10)
               .addComponent(labelStaffOnWork)
               .addGap(10)
               .addComponent(labelTime)
               .addGap(10)
               .addComponent(labelEarn)
               .addGap(10)
               .addComponent(btnConfirm));

       layout2.setHorizontalGroup(layout2.createSequentialGroup()
               .addGroup(layout2.createParallelGroup(Alignment.LEADING)
                       .addComponent(labelCleaning)
                       .addComponent(labelCleansing)
                       .addComponent(labelDisinfection)
                       .addComponent(labelDisinfestation)
                       .addComponent(labelConclusion)
                       .addComponent(labelStaffOnWork)
                       .addComponent(labelTime)
                       .addComponent(labelEarn))
               .addGroup(layout2.createParallelGroup(Alignment.TRAILING)
                       .addComponent(btnConfirm)));
    }

    /**
     * Method that displays the view.
     */

    public void display() {
        setVisible(true);
        setResizable(true);
    }

    /**
     * 
     */
    public void setSummary() {
         int time = 0;
         double earn = 0;
         double totalTime = 0;
         double totalEarn = 0;
         double nProd = 0;
         List<Integer> partialTime = new ArrayList<>();

         for (JCheckBox check : checkboxs) {
             time = 0;
             earn = 0;
             nProd = 0;
             if (check.isSelected()) {
                 if (!process.getSubStepsByStepType(check.getText()).isEmpty()) {
                     List<SubSteps> list = process.getSubStepsByStepType(check.getText()).get();
                     for (SubSteps subSteps : list) {
                         time += subSteps.getTime();
                     }
                 }
                 if (!company.getProductsByStepType(check.getText()).isEmpty()) {
                     List<Products> list2 = company.getProductsByStepType(check.getText()).get(); 
                     for (Products p : list2) {
                         earn += p.getPricePerLitre();
                         nProd++;
                     }
                     totalEarn += (earn / nProd);
                     System.out.println(totalEarn);
                 }
             }

             totalTime += time;
             partialTime.add(time);
         }
         totTime = process.getProportialTime(totalTime,  company.getClients().get(comboClients.getSelectedIndex()), Integer.parseInt(txtStaffs.getText()));
         totEarn = process.getProportialEarn(totalEarn,  company.getClients().get(comboClients.getSelectedIndex())) + 100;
         labelCleaning.setText(labelCleaning.getText() + " " + String.valueOf(partialTime.get(0)));
         labelCleansing.setText(labelCleansing.getText() + " " + String.valueOf(partialTime.get(1)));
         labelDisinfection.setText(labelDisinfection.getText() + " " + String.valueOf(partialTime.get(2)));
         labelDisinfestation.setText(labelDisinfestation.getText() + " " + String.valueOf(partialTime.get(3)));
         labelConclusion.setText(labelConclusion.getText() + " " + String.valueOf(partialTime.get(4)));
         labelStaffOnWork.setText(labelStaffOnWork.getText() + " " + txtStaffs.getText());
         labelTime.setText(labelTime.getText() + " " + String.valueOf(totTime) + " minuti");
         labelEarn.setText(labelEarn.getText() + " " + String.valueOf(totEarn) + " €");
         Clients c = company.getClients().get(comboClients.getSelectedIndex());
         Appointments a = new AppointmentsImpl(getDate(), getHour(), c, totTime, totEarn);
         company.addAppointment(a);
    }

    /**
     * 
     * @return true if all fields are written
     */
    public Boolean missingField() {
        return (getDate().isEmpty() || getHour().isEmpty() || getStaff() == Integer.MIN_VALUE);
    }

    /**
     * 
     * @return appointment's date
     */
    public String getDate() {
        return datepicker.getDateStringOrEmptyString();
    }

    /**
     * 
     * @return appointment's hour
     */
    public String getHour() {
        return timepicker.getText();
    }

    /**
     * 
     * @return an integer value
     */
    public int getStaff() {
        return validator.isInteger(txtStaffs.getText()) ? Integer.parseInt(txtStaffs.getText()) : Integer.MIN_VALUE;
    }

}
