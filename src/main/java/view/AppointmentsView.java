package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Company;
import controller.CompanyImpl;
import model.Appointments;
import utility.PopUp;

public class AppointmentsView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6203076048044842383L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";

    private JTextField txtClient;
    private JTextField txtDate;
    private JTextField txtHour;
    private final JButton btnSubmit;
    private final JButton btnHome;
    private final JButton btnSearch;
    //private final JButton btnChange;
    private final JButton btnRemove;
    private JComboBox<String> appDateHour;

    private Company company = CompanyImpl.getInstance();
    private List<Appointments> appointmentsList = company.getAppointment();
    private final String[] cols = new String[] {"Data", "Ora", "Nome", "CF o Partita IVA"};
    private Object[][] data = new Object[0][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private PopUp popUp = new PopUp();

    public AppointmentsView() {

        setTitle(AppointmentsView.TITLE);
        setMinimumSize(new Dimension(1200, 600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(1000, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(0, 0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco appuntamenti");
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
                model.setRowCount(0);
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }

        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        panelTable.add(panelTitle, BorderLayout.NORTH);

        Appointments a;
        for (int i = 0; i < appointmentsList.size(); i++) {
             a = company.getAppointment().get(i);
             model.insertRow(i, new Object[] {a.getDate(), a.getHour(), a.getClient().getName(), a.getClient().getCFPIVA().toUpperCase()});
         }

        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 40));
        pnlSearch.setMinimumSize(new Dimension(1000, 40));

        JLabel lblsearchDataOra = new JLabel("Data e ora appuntamenti: ");
        lblsearchDataOra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblsearchDataOra);

        appDateHour = new JComboBox<>();
        appDateHour.setPreferredSize(new Dimension(400, 20));
        appDateHour.setBackground(SystemColor.activeCaption);
        appDateHour.setForeground(SystemColor.textText);
        appDateHour.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        updateSearchingDateHour(appDateHour);
        pnlSearch.add(appDateHour);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(200, 20));
        btnSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexAppointmentsSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getAppointment().get(getIndexAppointmentsSearched()));
                    //btnChange.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlDelete = new JPanel();
        pnlDelete.setBorder(new TitledBorder(null, "Elimina appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlDelete.setBackground(SystemColor.window);
        pnlDelete.setPreferredSize(new Dimension(10, 60));
        pnlDelete.setMinimumSize(new Dimension(1000, 60));

        JLabel labelCFPIVA = new JLabel("Cliente:");
        labelCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlDelete.add(labelCFPIVA);

        txtClient = new JTextField(20);
        txtClient.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtClient.setEditable(false);
        pnlDelete.add(txtClient);

        JLabel labelName = new JLabel("Data:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlDelete.add(labelName);

        txtDate = new JTextField(14);
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlDelete.add(txtDate);

        JLabel labelAddress = new JLabel("Orario:");
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlDelete.add(labelAddress);

        txtHour = new JTextField(20);
        txtHour.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlDelete.add(txtHour);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setPreferredSize(new Dimension(900, 30));
        pnlButtons.setMinimumSize(new Dimension(900, 30));
        //pnlButtons.setLayout(new GridLayout(1, 3, 20, 20));

        /*btnChange = new JButton("Aggiorna Modifiche");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnChange.setEnabled(false);
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!missingField()) {

                    String s = getClientName();
                    Clients c = company.searchClient(s).get();
                    Appointments changed = new AppointmentsImpl(getDate(), getHour(), c);
                    Optional<Appointments> toModify = company.searchAppointment(changed.getDate(), changed.getHour());
                    popUp.popUpInfo("Appuntamento modificato con successo.");
                    company.removeAppointment(toModify.get());
                    removeAppointmentsToTable(toModify.get());
                    company.addAppointment(changed);
                    addAppointmentsToTable(changed);
                    updateSearchingDateHour(appDateHour);
                    clearInsertField();
                    btnChange.setEnabled(false);
                }
            }
        });
        pnlButtons.add(btnChange);*/

        btnRemove = new JButton("Elimina appuntamento");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (missingField()) {
                    popUp.popUpErrorOrMissing();
                } else {
                    Optional<Appointments> appointmentsToRemove = company.searchAppointment(getDate(), getHour());
                    if (appointmentsToRemove.isEmpty()) {
                        popUp.popUpWarning("Appuntamento non trovato.");
                    } else {
                        Boolean confirm = popUp.popUpConfirm("Vuoi eliminare l'appuntamento di " + appointmentsToRemove.get().getClient().getName() + "?");
                        if (confirm) {
                            popUp.popUpInfo("Appuntamento eliminato con successo.");
                            company.removeAppointment(appointmentsToRemove.get());
                            removeAppointmentsToTable(appointmentsToRemove.get());
                            updateSearchingDateHour(appDateHour);
                            clearInsertField();
                            btnRemove.setEnabled(false);
                        } else {
                            popUp.popUpInfo("Eliminazione annullata.");
                        }
                    }
                }
            }
        });
        pnlButtons.add(btnRemove);
        pnlDelete.add(pnlButtons, BorderLayout.SOUTH);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Inserisci nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 60));
        pnlSubmit.setMinimumSize(new Dimension(1000, 60));
        btnSubmit = new JButton("Vai all'inserimento di un nuovo appuntamento >");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                NewAppointmentView nv = new NewAppointmentView();
                nv.display();
                dispose();
            }
        });
        pnlSubmit.add(btnSubmit);

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(panelTable)
                .addGap(0)
                .addComponent(pnlSearch)
                .addGap(0)
                .addComponent(pnlDelete)
                .addGap(0)
                .addComponent(pnlSubmit)
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTable)
                        .addComponent(pnlSearch)
                        .addComponent(pnlDelete)
                        .addComponent(pnlSubmit))
                .addGap(0));
    }

    public void addAppointmentsToTable(final Appointments a) {
        model.insertRow(company.getAppointment().size() - 1, new Object[] {a.getDate(), a.getHour(), a.getClient()});
    }

    public void removeAppointmentsToTable(final Appointments a) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getDataVector().elementAt(i).elementAt(0).equals(a.getDate()) &&
                    model.getDataVector().elementAt(i).elementAt(1).equals(a.getHour())) {
                model.removeRow(i);
            }
        }
    }

    /**
     * 
     */
    public void clearInsertField() {
        txtClient.setText("");
        txtDate.setText("");
        txtHour.setText("");
    }

    public Boolean missingField() {
        return (getClientName().isEmpty() || getDate().isEmpty() || getHour().isEmpty());
    }

    public void writeField(final Appointments a) {
        txtClient.setText(a.getClient().getName() + " " + a.getClient().getCFPIVA().toUpperCase());
        txtDate.setText(a.getDate());
        txtHour.setText(a.getHour());
    }

    public String getClientName() {
        return txtClient.getText();
    }

    public String getDate() {
        return txtDate.getText();
    }

    public String getHour() {
        return txtHour.getText();
    }

    public int getIndexAppointmentsSearched() {
        return appDateHour.getSelectedIndex();
    }

    public void updateSearchingDateHour(final JComboBox<String> appDateHour) {
        appDateHour.removeAllItems();
        for (Appointments appointments : company.getAppointment()) {
            appDateHour.addItem(appointments.getDate() + " - " + appointments.getHour());
        }
    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
