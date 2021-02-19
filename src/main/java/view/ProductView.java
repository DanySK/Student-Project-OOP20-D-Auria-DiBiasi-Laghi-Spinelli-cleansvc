package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import model.Products;
import model.ProductsImpl;
import model.users.ClientsImpl;

public class ProductView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3438738368807932420L;
    private static final String TITLE = "PRODOTTI";
    
    final JButton btnHome;
    JTextField txtSearch;
    final JButton btnSearch;
    JTextField txtName;
    JTextField txtPrice;
    JTextField txtUsage;
    final JButton btnChange;
    Company company = CompanyImpl.getInstance();
    List<Products> productsList = new ArrayList<>(); //comapany.getProducts();
    
    public ProductView() {
        
        setTitle(ProductView.TITLE);
        setMinimumSize(new Dimension(1200, 450));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(1000, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(0, 0));
        
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(0, 0));
        
        JLabel lblTitle = new JLabel("Elenco prodotti");
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
        productsList.add(new ProductsImpl("a", "b", 3.3, 22));
        productsList.add(new ProductsImpl("b", "b", 5.5, 22));
        final String[] cols = new String[] {"Nome", "Descrizione", "Prezzo/Litro", "Utilizzo L/500mq"};
        Object[][] data = new Object[productsList.size()][cols.length];
        
        for (int i = 0; i < productsList.size(); i++) {
            data[i][0] = productsList.get(i).getName();
            data[i][1] = productsList.get(i).getDescription();
            data[i][2] = String.valueOf(productsList.get(i).getPricePerLitre());
            data[i][3] = String.valueOf(productsList.get(i).getLitersPer500Mq());
        }

        JTable table = new JTable(data,cols);
        table.setPreferredScrollableViewportSize(new Dimension(1000, 200));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        panelTable.add(table,BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSearch = new JPanel();
        pnlSearch.setBorder(new TitledBorder(null, "Recupera dati prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSearch.setBackground(SystemColor.window);
        pnlSearch.setPreferredSize(new Dimension(1000, 35));
        pnlSearch.setMinimumSize(new Dimension(1000, 35));
        
        JLabel lblsearch = new JLabel("Step:");
        lblsearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblsearch);

        txtSearch = new JTextField(20);
        txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(txtSearch);
        
        JLabel lblNoFound = new JLabel("Prodotto non trovato!");
        lblNoFound.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlSearch.add(lblNoFound);
        
        btnSearch = new JButton("Estrai dati");
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
        
        
        final JPanel pnlData = new JPanel();
        pnlData.setBorder(new TitledBorder(null, "Dati nuovo prodotto", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlData.setBackground(SystemColor.window);
        pnlData.setPreferredSize(new Dimension(900, 40));
        pnlData.setMinimumSize(new Dimension(900, 40));
        pnlData.setLayout(new FlowLayout());
        
        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelName);
        
        txtName = new JTextField(20);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtName);

        JLabel labelPrice = new JLabel("Prezzo al litro:");
        labelPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelPrice);
        
        txtPrice = new JTextField(10);
        txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtPrice);
        
        JLabel labelUsage = new JLabel("Utilizzo L/500mq:");
        labelUsage.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelUsage);
        
        txtUsage = new JTextField(10);
        txtUsage.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtUsage);
        
        btnChange = new JButton("Modifica esistente");
        btnChange.setForeground(SystemColor.textText);
        btnChange.setBackground(SystemColor.activeCaption);
        btnChange.setPreferredSize(new Dimension(200,20));
        btnChange.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnChange.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                
            }
            
        });
        pnlData.add(btnChange);
        

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
                .addComponent(pnlData)
                .addGap(0));
        
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTable)
                        .addComponent(pnlSearch)
                        .addComponent(pnlData))
                .addGap(0));
    }
    
    public String getName() {
        return txtName.getText();
    }
    
    public double getPrice() {
        return Double.parseDouble(txtPrice.getText());
    }
    
    public double getUsage() {
        return Double.parseDouble(txtUsage.getText());
    }
    
    public void display() {
        setVisible(true);
        setResizable(true);
    }
    
}
