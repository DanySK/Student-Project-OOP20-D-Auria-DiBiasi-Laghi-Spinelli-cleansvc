package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Appointments;
import model.AppointmentsImpl;
import model.Products;
import model.step.SubSteps;
import model.step.enumerations.StepType;
import model.users.Clients;
import utility.PopUp;

public class NewAppointmentView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";
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
    public NewAppointmentView() {

        setTitle(NewAppointmentView.TITLE);
        setMinimumSize(new Dimension(1200, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Nuovo Appuntamento");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE, 20));
        panelTitle.add(lblTitle, BorderLayout.WEST);

        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(120, 20));
        btnHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                HomeView hv = new HomeView();
                hv.display();
                dispose();
            }
        });
        panelTitle.add(btnHome, BorderLayout.EAST);

        JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(1000, 100));
        pnlSubmit.setMinimumSize(new Dimension(1000, 100));
        pnlSubmit.setLayout(new GridLayout(2, 6, 10, 20));

        JLabel labelCliente = new JLabel("Cliente:");
        labelCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCliente);

        comboClients = new JComboBox<>();
        comboClients.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        comboClients.setToolTipText("Cliente");
        comboClients.setBackground(SystemColor.inactiveCaption);
        comboClients.setForeground(SystemColor.textText);
        comboClients.setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(comboClients);
        Clients cc;
        for (int i = 0; i < clientsList.size(); i++) {
            cc = company.getClients().get(i);
            comboClients.addItem(cc.getCFPIVA() + " " + cc.getName());
        }

        JLabel labelDatePicker = new JLabel("Data:");
        labelDatePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelDatePicker);

        datepicker = new DatePicker();
        datepicker.getComponentToggleCalendarButton().setForeground(SystemColor.textText);
        datepicker.getComponentDateTextField().setToolTipText("Data dell'appuntamento");
        datepicker.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepicker.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        pnlSubmit.add(datepicker);

        JLabel labelTimePicker = new JLabel("Orario:");
        labelTimePicker.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelTimePicker);

        timepicker = new TimePicker();
        timepicker.getComponentToggleTimeMenuButton().setForeground(SystemColor.textText);
        timepicker.getComponentTimeTextField().setToolTipText("Orario dell'appuntamento");
        timepicker.getComponentToggleTimeMenuButton().setBackground(SystemColor.activeCaption);
        timepicker.getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
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
        labelStaff.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelStaff);

        txtStaffs = new JTextField(10);
        txtStaffs.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtStaffs);

        btnSubmit = new JButton("Conferma");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Clients c = company.getClients().get(comboClients.getSelectedIndex());
                    Appointments a = new AppointmentsImpl(getDate(), getHour(), c);
                    if (company.searchAppointment(a.getDate(), a.getHour()).isEmpty()) {
                        company.addAppointment(a);
                        popUp.popUpInfo("Appuntamento inserito con successo.");
                        setSummary();
//                        new AppointmentsView().display();
//                        setVisible(false);
                    } else {
                        popUp.popUpError("Data e ora già prenotate");
                    }
                } else {
                    popUp.popUpWarning("Ci sono dati mancanti o errati");
                }
            }
        });
        pnlSubmit.add(btnSubmit);

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Riepilogo", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 300));
        pnlSearch.setMinimumSize(new Dimension(1000, 300));

       labelCleaning = new JLabel("Tempo per la fase di PULIZIA:");
        labelCleaning .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelCleaning);

        labelCleansing = new JLabel("Tempo per la fase di RISCIACQUO:");
        labelCleansing .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelCleansing);

        labelDisinfection = new JLabel("Tempo per la fase di DISINFEZIONE:");
        labelDisinfection .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelDisinfection);

        labelDisinfestation = new JLabel("Tempo per la fase di DISINFESTAZIONE:");
        labelDisinfestation .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelDisinfestation);

        labelConclusion = new JLabel("Tempo per la fase di CONCLUSIONE:");
        labelConclusion .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelConclusion);

        labelStaffOnWork = new JLabel("Dipendenti affidati al processo:");
        labelStaffOnWork .setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelStaffOnWork);

        labelTime = new JLabel("Tempo totale stimato:");
        labelTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelTime);

        labelEarn = new JLabel("Costo totale stimato:");
        labelEarn.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(labelEarn);

        btnConfirm = new JButton("Fine");
        btnConfirm.setForeground(SystemColor.textText);
        btnConfirm.setBackground(SystemColor.activeCaption);
        btnConfirm.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnConfirm.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //TODO salvare statistiche
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
                               .addGap(50)
                               .addComponent(checkboxs.get(1))
                               .addGap(50)
                               .addComponent(checkboxs.get(2))
                               .addGap(50)
                               .addComponent(checkboxs.get(3))
                               .addGap(50)
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
     * 
     */

    public void display() {
        setVisible(true);
        setResizable(true);
    }

    /**
     * 
     * @return
     */
    public void setSummary() {
         int time = 0;
         double earn = 0;
         double totalTime = 0;
         double totalEarn = 0;
         List<Integer> partialTime = new ArrayList<>();

         for (JCheckBox check : checkboxs) {
             time = 0;
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
                     }
                 }
             }
             totalEarn += earn;
             totalTime += time;
             partialTime.add(time);
         }
         totTime = process.getProportialValue(totalTime,  company.getClients().get(comboClients.getSelectedIndex()), Integer.parseInt(txtStaffs.getText()));
         totEarn = process.getProportialValue(totalEarn,  company.getClients().get(comboClients.getSelectedIndex()), Integer.parseInt(txtStaffs.getText()));
         labelCleaning.setText(labelCleaning.getText() + " " + String.valueOf(partialTime.get(0)));
         labelCleansing.setText(labelCleansing.getText() + " " + String.valueOf(partialTime.get(1)));
         labelDisinfection.setText(labelDisinfection.getText() + " " + String.valueOf(partialTime.get(2)));
         labelDisinfestation.setText(labelDisinfestation.getText() + " " + String.valueOf(partialTime.get(3)));
         labelConclusion.setText(labelConclusion.getText() + " " + String.valueOf(partialTime.get(4)));
         labelStaffOnWork.setText(labelStaffOnWork.getText() + " " + txtStaffs.getText());
         labelTime.setText(labelTime.getText() + " " + String.valueOf(totTime) + " minuti");
         labelEarn.setText(labelEarn.getText() + " " + String.valueOf(totEarn) + " €");
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
