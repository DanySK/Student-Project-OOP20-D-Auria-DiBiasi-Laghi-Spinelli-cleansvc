package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import model.users.CompanyImpl;
import model.users.Staff;

public class StaffView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -6791011571687868971L;
    private static final String TITLE = "Dipendenti";
    
    JTextField txtCFPIVA;
    JTextField txtName;
    JTextField txtAddress;
    JTextField txtCity;
    JTextField txtCAP;
    JTextField txttel;
    JTextField txtemail;
    JCheckBox checkAdmin;
    final JButton btnSubmit;
    final JButton btnHome;
    
    public StaffView() {
        setTitle(StaffView.TITLE);
        setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(1000, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTable, BorderLayout.NORTH);
        panelTable.setLayout(new BorderLayout(0, 0));
        
        
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 20));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.CENTER);
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
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        panelTable.add(panelTitle, BorderLayout.NORTH);
        
        String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Telefono", "Email", "CF_PIVA", "Amministratore"};
        Object[][] data = new Object[][] {
            {"Luigi Bianchi", "Via dell'università 50", "Cesena", "47522", "0088338550", "unibo@unibo.it", "0385719047300", "no"}, 
            {"Mario Rossi", "Via degli omonimi 33", "Meldola", "47014", "95947524022", "mario.rossi@oulook.it", "MRORSS77M45D706Y", "si"}
        };
        JTable table = new JTable(data,cols);
        table.setPreferredScrollableViewportSize(new Dimension(500, 310));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table,BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo dipendente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 100));
        pnlSubmit.setMinimumSize(new Dimension(1000, 100));
        mainPanel.add(pnlSubmit, BorderLayout.SOUTH);
        
        JLabel labelCFPIVA = new JLabel("CF:");
        labelCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCFPIVA);
        
        txtCFPIVA = new JTextField(18);
        txtCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtCFPIVA);
        
        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelName);
        
        txtName = new JTextField(14);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtName);
        
        JLabel labelAddress = new JLabel("Indirizzo:");
        labelAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelAddress);
        
        txtAddress = new JTextField(20);
        txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtAddress);
        
        JLabel labelCity = new JLabel("Città:");
        labelCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCity);
        
        txtCity = new JTextField(10);
        txtCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtCity);
        
        JLabel labelCAP = new JLabel("CAP:");
        labelCAP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelCAP);
        
        txtCAP = new JTextField(5);
        txtCAP.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtCAP);
        
        JLabel labelmq = new JLabel("Amministratore:");
        labelmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelmq);
        
        checkAdmin = new JCheckBox("");
        pnlSubmit.add(checkAdmin);
        
        JLabel labeltel = new JLabel("Telefono:");
        labeltel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labeltel);
        
        txttel = new JTextField(10);
        txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txttel);
        
        JLabel labelemail = new JLabel("Email:");
        labelemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelemail);
        
        txtemail = new JTextField(15);
        txtemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtemail);
        
        btnSubmit = new JButton("Inserisci");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
            }
            
        });
        pnlSubmit.add(btnSubmit);
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
    
    public boolean getIsAdmin() {
        return checkAdmin.isSelected();
    }
    
    public String geTel() {
        return txttel.getText();
    }
    
    public String getEmail() {
        return txtemail.getText();
    }
    
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
