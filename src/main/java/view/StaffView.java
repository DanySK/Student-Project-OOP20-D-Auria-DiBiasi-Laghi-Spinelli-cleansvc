package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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

import model.users.Clients;
import model.users.Staff;
import controller.Company;
import controller.CompanyImpl;
import model.users.StaffImpl;
import utility.InputValidator;
import utility.PopUp;

public class StaffView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6791011571687868971L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";
    private JTextField txtCFPIVA;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtCAP;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JCheckBox checkAdmin;
    private final JButton btnSearch;
    private final JButton btnSubmit;
    private final JButton btnChange;
    private final JButton btnRemove;
    private final JButton btnHome;
    private JComboBox<String> staffCFs;
    /*
     * List<Staff> create for testing table
     *
     * private List<Staff> staffList = new ArrayList<>(); 
     */
    private Company company = CompanyImpl.getInstance();
    private final String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Amministratore", "Telefono", "Email", "CF/PIVA"};
    private Object[][] data = new Object[0][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);
    private PopUp popUp = new PopUp();
    private InputValidator validator = new InputValidator();

    public StaffView() {
        setTitle(StaffView.TITLE);
        setMinimumSize(new Dimension(1200, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(1000, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(0, 0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco dipendenti");
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

        /*
         * testing:
         *
         * staffList.add(new StaffImpl("a", "b", "c", "d", "f", "f", "f", "no"));
         *
         *   for (int i = 0; i < staffList.size(); i++) {
         *       data[i][0] = staffList.get(i).getName();
         *       data[i][1] = staffList.get(i).getAddress();
         *       data[i][2] = staffList.get(i).getCity();
         *       data[i][3] = staffList.get(i).getCAP();
         *       data[i][4] = staffList.get(i).isAdmin();
         *       data[i][5] = staffList.get(i).getTel();
         *       data[i][6] = staffList.get(i).getEmail();
         *       data[i][7] = staffList.get(i).getCFPIVA();
         *   }
         */

        for (int i = 0; i < company.getStaff().size(); i++) {
            Staff staff = company.getStaff().get(i);
            String admin = staff.isAdmin() ? "si" : "no";
            model.insertRow(i, new Object[] {staff.getName(), staff.getAddress(), staff.getCity(), staff.getCAP(), admin, staff.getTel(), staff.getEmail(), staff.getCFPIVA()});
        }
        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 40));
        pnlSearch.setMinimumSize(new Dimension(1000, 40));

        JLabel lblsearchCFPIVA = new JLabel("CF dipendenti: ");
        lblsearchCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblsearchCFPIVA);

        staffCFs = new JComboBox<>();
        staffCFs.setPreferredSize(new Dimension(400, 20));
        staffCFs.setBackground(SystemColor.activeCaption);
        staffCFs.setForeground(SystemColor.textText);
        staffCFs.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        updateSearchingCFs(staffCFs);
        pnlSearch.add(staffCFs);

        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(200, 20));
        btnSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (getIndexStaffSearched() == -1) {
                    popUp.popUpErrorOrMissing();
                } else {
                    writeField(company.getStaff().get(getIndexStaffSearched()));
                    btnChange.setEnabled(true);
                    btnRemove.setEnabled(true);
                }
            }
        });
        pnlSearch.add(btnSearch);

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(900, 140));
        pnlSubmit.setMinimumSize(new Dimension(900, 140));
        pnlSubmit.setLayout(new BorderLayout(0, 0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setPreferredSize(new Dimension(900, 60));
        pnlData.setMinimumSize(new Dimension(900, 60));
        pnlData.setLayout(new GridLayout(4, 4, 20, 2));

        JLabel labelCFPIVA = new JLabel("CF:");
        labelCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelCFPIVA);

        txtCFPIVA = new JTextField(18);
        txtCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtCFPIVA);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelName);

        txtName = new JTextField(14);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtName);

        JLabel labelAddress = new JLabel("Indirizzo:");
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelAddress);

        txtAddress = new JTextField(20);
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtAddress);

        JLabel labelCity = new JLabel("Città:");
        labelCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelCity);

        txtCity = new JTextField(10);
        txtCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtCity);

        JLabel labelCAP = new JLabel("CAP:");
        labelCAP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelCAP);

        txtCAP = new JTextField(5);
        txtCAP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtCAP);

        JLabel labelmq = new JLabel("Amministratore:");
        labelmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelmq);

        checkAdmin = new JCheckBox("");
        checkAdmin.setBackground(SystemColor.window);
        pnlData.add(checkAdmin);

        JLabel labeltel = new JLabel("Telefono:");
        labeltel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labeltel);

        txtTel = new JTextField(10);
        txtTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtTel);

        JLabel labelemail = new JLabel("Email:");
        labelemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelemail);

        txtEmail = new JTextField(15);
        txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtEmail);
        pnlSubmit.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setPreferredSize(new Dimension(900, 30));
        pnlButtons.setMinimumSize(new Dimension(900, 30));
        pnlButtons.setLayout(new GridLayout(1, 3, 20, 20));

        btnSubmit = new JButton("Inserisci nuovo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setPreferredSize(new Dimension(120, 20));
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Staff staff = new StaffImpl(getCFPIVA().toUpperCase(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(), isAdmin());
                    if (company.searchStaffbyCF(staff.getCFPIVA()).isEmpty() && company.searchStaffbyEmail(staff.getEmail()).isEmpty()) {
                        popUp.popUpInfo("Dipendente inserito con successo.");
                        company.addStaff(staff);
                        addStaffToTable(company.getStaff().get(company.getStaff().size() - 1));
                        updateSearchingCFs(staffCFs);
                        clearInsertField();
                    } else {
                        popUp.popUpError("Dipendente già esistente!");
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnSubmit);

        btnChange = new JButton("Aggiorna modifiche");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setPreferredSize(new Dimension(120, 20));
        btnChange.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnChange.setEnabled(false);
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    Staff changed = new StaffImpl(getCFPIVA().toUpperCase(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(),  isAdmin());
                    Optional<Staff> toModify = company.searchStaffbyCF(changed.getCFPIVA());
                    if (toModify.isEmpty()) {
                        popUp.popUpWarning("Codice Fiscale inesistente tra i dipendenti. Non puoi modificare il Codice Fiscale.");
                    } else {
                        popUp.popUpInfo("Dipendente modificato con successo.");
                        company.removeStaff(toModify.get());
                        removeStaffToTable(toModify.get());
                        company.addStaff(changed);
                        addStaffToTable(changed);
                        updateSearchingCFs(staffCFs);
                        clearInsertField();
                        btnChange.setEnabled(false);
                    }
                } else {
                    popUp.popUpErrorOrMissing();
                }
            }
        });
        pnlButtons.add(btnChange);

        btnRemove = new JButton("Elimina dipendente");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setPreferredSize(new Dimension(120, 20));
        btnRemove.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnRemove.setEnabled(false);
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (missingField()) {
                    popUp.popUpErrorOrMissing();
                } else {
                    Optional<Staff> staffToRemove = company.searchStaffbyCF(getCFPIVA().toUpperCase());
                    if (staffToRemove.isEmpty()) {
                        popUp.popUpWarning("Dipendente non trovato.");
                    } else {
                        String email = popUp.popUpInput("Inserisci email:");
                        if (validator.isEmail(email)) {
                            Optional<Staff> staffAdmin = company.searchStaffbyEmail(email);
                            if (staffAdmin.isEmpty()) {
                                popUp.popUpWarning("L'email non esiste!");
                            } else if (staffAdmin.isPresent()) {
                                if (staffAdmin.get().isAdmin()) {
                                    Boolean confirm = popUp.popUpConfirm("Vuoi eliminare il dipendente " + staffToRemove.get().getName() + "?");
                                    if (confirm) {
                                        popUp.popUpInfo("Dipendente eliminato con successo.");
                                        company.removeStaff(staffToRemove.get());
                                        removeStaffToTable(staffToRemove.get());
                                        updateSearchingCFs(staffCFs);
                                        clearInsertField();
                                        btnRemove.setEnabled(false);
                                    } else {
                                        popUp.popUpInfo("Eliminazione annullata.");
                                    }
                                } else {
                                    popUp.popUpError("Non hai i permessi necessari!");
                                }
                            }
                        } else {
                            popUp.popUpErrorOrMissing();
                        }
                    }
                }
            }
        });
        pnlButtons.add(btnRemove);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);

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
                .addComponent(pnlSubmit)
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addGap(0)
                        .addComponent(panelTable)
                        .addGap(0)
                        .addComponent(pnlSearch)
                        .addGap(0)
                        .addComponent(pnlSubmit)
                        .addGap(0))
                .addGap(0));
    }

    /**
     * 
     * @param s
     */
    public void addStaffToTable(final Staff s) {
        String admin = s.isAdmin() ? "si" : "no";
        model.insertRow(company.getStaff().size() - 1, new Object[] {s.getName(), s.getAddress(), s.getCity(), s.getCAP(), admin, s.getTel(), s.getEmail(), s.getCFPIVA()});
    }

    /**
     * 
     * @param s
     */
    public void removeStaffToTable(final Staff s) {
        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getDataVector().elementAt(i).elementAt(7).equals(s.getCFPIVA())) {
                model.removeRow(i);
            }
        }
    }

    /**
     * 
     */
    public void clearInsertField() {
        txtCFPIVA.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtCAP.setText("");
        checkAdmin.setSelected(false);
        txtTel.setText("");
        txtEmail.setText("");
    }

    /**
     * Check if all input field are fill
     * @return true
     */
    public Boolean missingField() {
        return (getCFPIVA().isEmpty() || getName().isEmpty() || getAddress().isEmpty() || getCity().isEmpty() || getCAP() == Integer.MIN_VALUE || getTel().isEmpty() || getEmail().isEmpty());
    }

    /**
     * 
     * @param s
     */
    public void writeField(final Staff s) {
        txtCFPIVA.setText(s.getCFPIVA());
        txtName.setText(s.getName());
        txtAddress.setText(s.getAddress());
        txtCity.setText(s.getCity());
        txtCAP.setText(String.valueOf(s.getCAP()));
        checkAdmin.setSelected(s.isAdmin());
        txtTel.setText(s.getTel());
        txtEmail.setText(s.getEmail());
    }

    /**
     * 
     * @return true if is validate
     */
    public String getCFPIVA() {
        return validator.isCFPIVA(txtCFPIVA.getText().toUpperCase()) ? txtCFPIVA.getText().toUpperCase() : "";
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return validator.isName(txtName.getText()) ? txtName.getText() : "";
    }

    /**
     * 
     * @return
     */
    public String getAddress() {
        return validator.isNameAndNum(txtAddress.getText()) ? txtAddress.getText() : "";
    }

    /**
     * 
     * @return
     */
    public String getCity() {
        return validator.isName(txtCity.getText()) ? txtCity.getText() : "";
    }

    /**
     * 
     * @return
     */
    public int getCAP() {
        return validator.isCAP(txtCAP.getText()) ? Integer.parseInt(txtCAP.getText()) : Integer.MIN_VALUE;
    }

    /**
     * 
     * @return
     */
    public Boolean isAdmin() {
        return checkAdmin.isSelected();
    }

    /**
     * 
     * @return
     */
    public String getTel() {
        return validator.isPhone(txtTel.getText()) ? txtTel.getText() : "";
    }

    /**
     * 
     * @return
     */
    public String getEmail() {
        return validator.isEmail(txtEmail.getText()) ? txtEmail.getText() : "";
    }

    /**
     * 
     * @return
     */
    public int getIndexStaffSearched() {
        return staffCFs.getSelectedIndex();
    }

    /**
     * 
     * @param staffCFs
     */
    public void updateSearchingCFs(final JComboBox<String> staffCFs) {
        staffCFs.removeAllItems();
        for (Staff staff : company.getStaff()) {
            staffCFs.addItem(staff.getCFPIVA() + " - " + staff.getName());
        }
    }
    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
