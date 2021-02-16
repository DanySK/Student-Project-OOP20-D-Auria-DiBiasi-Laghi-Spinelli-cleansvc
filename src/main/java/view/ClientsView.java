package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;

import controller.Company;
import controller.CompanyImpl;
import model.users.Clients;
import model.users.ClientsImpl;

public class ClientsView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3375687914483476432L;
    private static final String TITLE ="CLIENTI";
    
    JTextField txtCFPIVA;
    JTextField txtName;
    JTextField txtAddress;
    JTextField txtCity;
    JTextField txtCAP;
    JTextField txtmq;
    JTextField txttel;
    JTextField txtemail;
    JTextField txtsearchCFPIVA;
    final JButton btnSearch;
    final JButton btnSubmit;
    final JButton btnChange;
    final JButton btnRemove;
    final JButton btnHome;
    Company c = CompanyImpl.getInstance();
    List<Clients> clientsList = new ArrayList<>();//c.getClient();

    public ClientsView() {
        
        setTitle(ClientsView.TITLE);
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
        
        JLabel lblTitle = new JLabel("Elenco clienti");
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
        
        /*
         * test
         */
        clientsList.add(new ClientsImpl("a", "b", "c", "d", "f", "f", "f", 22));
        clientsList.add(new ClientsImpl("b", "b", "b", "b", "f", "f", "f", 22));
        final String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Struttura_mq", "Telefono", "Email", "CF_PIVA"};
        Object[][] data = new Object[clientsList.size()][cols.length];
        
        for (int i = 0; i < clientsList.size(); i++) {
            data[i][0] = clientsList.get(i).getName();
            data[i][1] = clientsList.get(i).getAddress();
            data[i][2] = clientsList.get(i).getCity();
            data[i][3] = clientsList.get(i).getCAP();
            data[i][4] = String.valueOf(clientsList.get(i).getMqStructure());
            data[i][5] = clientsList.get(i).getTel();
            data[i][6] = clientsList.get(i).getEmail();
            data[i][7] = clientsList.get(i).getCFPIVA();
        }

        JTable table = new JTable(data,cols);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table,BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati clienti", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 40));
        pnlSearch.setMinimumSize(new Dimension(1000, 40));
        
        JLabel lblsearchCFPIVA = new JLabel("CF/P.IVA:");
        lblsearchCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblsearchCFPIVA);

        txtsearchCFPIVA = new JTextField(20);
        txtsearchCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(txtsearchCFPIVA);
        
        JLabel lblNoFound = new JLabel("Cliente non trovato!");
        lblNoFound.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblNoFound);
        
        btnSearch = new JButton("Cerca");
        btnSearch.setForeground(SystemColor.textText);
        btnSearch.setBackground(SystemColor.activeCaption);
        btnSearch.setPreferredSize(new Dimension(120,20));
        btnSearch.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSearch.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
        pnlSearch.add(btnSearch);
        
        
        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo cliente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
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
        
        JLabel labelCFPIVA = new JLabel("CF/P.IVA:");
        labelCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelCFPIVA);
        
        txtCFPIVA = new JTextField(20);
        txtCFPIVA.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtCFPIVA);
        
        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelName);
        
        txtName = new JTextField(10);
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
        
        JLabel labelmq = new JLabel("Struttura (mq):");
        labelmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelmq);
        
        txtmq = new JTextField(5);
        txtmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtmq);
        
        JLabel labeltel = new JLabel("Telefono:");
        labeltel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labeltel);
        
        txttel = new JTextField(10);
        txttel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txttel);
        
        JLabel labelemail = new JLabel("Email:");
        labelemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelemail);
        
        txtemail = new JTextField(15);
        txtemail.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtemail);
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
                // TODO Auto-generated method stub
                
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
    
    public int getMq() {
        return Integer.parseInt(txtmq.getText());
    }
    
    public String getTel() {
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
