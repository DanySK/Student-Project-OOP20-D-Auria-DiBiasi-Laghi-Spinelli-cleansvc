package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import model.users.Clients;
import model.users.ClientsImpl;
import model.users.Staff;

import controller.Company;
import controller.CompanyImpl;
import model.users.Staff;
import model.users.StaffImpl;

public class StaffView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6791011571687868971L;
    private static final String TITLE = "DIPENDENTI";
    
    private JTextField txtCFPIVA;
    private JTextField txtName;
    private JTextField txtAddress;
    private JTextField txtCity;
    private JTextField txtCAP;
    private JTextField txtTel;
    private JTextField txtEmail;
    private JCheckBox checkAdmin;
    private JTextField txtSearch;
    private final JButton btnSearch;
    private final JButton btnSubmit;
    private final JButton btnChange;
    private final JButton btnRemove;
    private final JButton btnHome;
    /*
     * List<Staff> create for testing table
     *
     * private List<Staff> staffList = new ArrayList<>(); 
     */
    private Company company = CompanyImpl.getInstance();
    private List<Staff> staffList = company.getStaff();
    private final String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Amministratore", "Telefono", "Email", "CF_PIVA"};
    private Object[][] data = new Object[staffList.size()][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data,cols);
    private JTable table = new JTable(model);
    
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
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE,20));
        panelTitle.add(lblTitle, BorderLayout.WEST);
        
        btnHome = new JButton("BACK HOME");
        btnHome.setForeground(SystemColor.textText);
        btnHome.setBackground(SystemColor.activeCaption);
        btnHome.setPreferredSize(new Dimension(120,20));
        btnHome.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnHome.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
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
        
        
        Staff s;
        for (int i = 0; i < staffList.size(); i++) {
            s = company.getStaff().get(i);
            model.insertRow(i, new Object[] {s.getName(), s.getAddress(), s.getCity(), s.getCAP(), s.getIsAdmin(), s.getTel(), s.getEmail(), s.getCFPIVA()});
        }
        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table,BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 40));
        pnlSearch.setMinimumSize(new Dimension(1000, 40));
        
        JLabel lblsearchCFPIVA = new JLabel("CF/P.IVA:");
        lblsearchCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblsearchCFPIVA);

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(txtSearch);
        
        btnSearch = new JButton("Estrai dati");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(120,20));
        btnSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(rootPane, "Dipendente non trovato!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            
        });
        pnlSearch.add(btnSearch);
        
        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(900, 140));
        pnlSubmit.setMinimumSize(new Dimension(900, 140));
        pnlSubmit.setLayout(new BorderLayout(0,0));
        
        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setPreferredSize(new Dimension(900, 60));
        pnlData.setMinimumSize(new Dimension(900, 60));
        pnlData.setLayout(new GridLayout(4,4,20,2));
        
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
        pnlButtons.setLayout(new GridLayout(1,3,20,20));
        
        btnSubmit = new JButton("Inserisci nuovo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setPreferredSize(new Dimension(120,20));
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                company.addStaff(new StaffImpl(getCFPIVA(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(), getIsAdmin()));
                Staff s = company.getStaff().get(staffList.size()-1);
                JOptionPane.showMessageDialog(rootPane, "Dipendente inserito con successo.");
                model.insertRow(staffList.size()-1, new Object[] {s.getName(), s.getAddress(), s.getCity(), s.getCAP(), s.getIsAdmin(), s.getTel(), s.getEmail(), s.getCFPIVA()});
                clearInsertField();
            }
            
        });
        pnlButtons.add(btnSubmit);
        
        btnChange = new JButton("Modifica esistente");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setPreferredSize(new Dimension(120,20));
        btnChange.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                Staff changed = new StaffImpl(getCFPIVA(), getName(), getAddress(), getCity(), getCAP(), getTel(), getEmail(),  getIsAdmin());
                //TODO if cf exist remove and add, else alert pop-up
                JOptionPane.showMessageDialog(rootPane, "Dipendente modificato con successo.");
                JOptionPane.showMessageDialog(rootPane, "Ci sono dati mancanti o errati!", "Alert", JOptionPane.WARNING_MESSAGE);
            }
            
        });
        pnlButtons.add(btnChange);
        
        btnRemove = new JButton("Elimina");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setPreferredSize(new Dimension(120,20));
        btnRemove.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String email = JOptionPane.showInputDialog(rootPane, "Inserisci la email per verificare se possiedi i permessi:");
                if (email!=null) {
                    Optional<Staff> ss = company.searchStaff(email);
                    if (ss.isEmpty()) {
                        JOptionPane.showMessageDialog(rootPane, "L'email non esiste!", "Alert", JOptionPane.ERROR_MESSAGE);
                    } else if (ss.isPresent()) {
                        if (ss.get().getIsAdmin() == "si") {
                            JOptionPane.showMessageDialog(rootPane, "Dipendente eliminato con successo.");
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Non hai i permessi necessari!", "Alert", JOptionPane.ERROR_MESSAGE);
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
                        .addComponent(panelTable)
                        .addComponent(pnlSearch)
                        .addComponent(pnlSubmit))
                .addGap(0));
        
    }
    
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

   
    public String getCFPIVA() {
        return txtCFPIVA.getText();
    }
    
    public String getName() {
        return txtName.getText();
    }
    
    public String getAddress() {
        return txtAddress.getText();
    }
    
    public String getCity() {
        return txtCity.getText();
    }
    
    public String getCAP() {
        return txtCAP.getText();
    }
    
    public String getIsAdmin() {
        return checkAdmin.isSelected() ? "si" : "no";
    }
    
    public String getTel() {
        return txtTel.getText();
    }
    
    public String getEmail() {
        return txtEmail.getText();
    }
    
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
