package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


public class ClientsView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3694166450488400402L;
    private static final String TITLE = "Clienti";
    

    JTextField txtCFPIVA;
    JTextField txtName;
    JTextField txtAddress;
    JTextField txtCity;
    JTextField txtCAP;
    JTextField txtmq;
    JTextField txttel;
    JTextField txtemail;
    final JButton btnSubmit;

    public ClientsView() {

        setTitle(ClientsView.TITLE);
        setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(10, 100));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco clienti");
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.CENTER_BASELINE,20));
        panelTitle.add(lblTitle, BorderLayout.NORTH);

        String[] cols = new String[] {"Nome", "Indirizzo", "Città", "CAP", "Struttura_mq", "Telefono", "Email", "CF_PIVA"};
        Object[][] data = new Object[][] {
            {"Unibo", "Via dell'università 50", "Cesena", "47522", "900", "0088338550", "unibo@unibo.it", "0385719047300"}, 
            {"Mario Rossi", "Via degli omonimi 33", "Meldola", "47014", "150", "95947524022", "mario.rossi@oulook.it", "MRORSS77M45D706Y"}
        };
        JTable table = new JTable(data,cols);
        table.setPreferredScrollableViewportSize(new Dimension(500, 310));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTitle.add(table,BorderLayout.CENTER);
        panelTitle.add(new JScrollPane(table));

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati nuovo cliente", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 100));
        pnlSubmit.setMinimumSize(new Dimension(10, 100));
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
        
        txtName = new JTextField(10);
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
        
        JLabel labelmq = new JLabel("Struttura (mq):");
        labelmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(labelmq);
        
        txtmq = new JTextField(5);
        txtmq.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSubmit.add(txtmq);
        
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
        
        btnSubmit = new JButton("Submit");
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
    
    public int getmq() {
        return Integer.parseInt(txtmq.getText());
    }
    
    public String gettel() {
        return txttel.getText();
    }
    
    public String getemail() {
        return txtemail.getText();
    }
    
    public void display() {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setVisible(true);
        setResizable(true);
    }

}
