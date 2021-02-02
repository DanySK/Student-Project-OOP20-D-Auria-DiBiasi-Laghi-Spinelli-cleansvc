package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import model.users.*;
public class ClientsView extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3694166450488400402L;
    private static final String TITLE = "Clienti";
    
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
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        panelTitle.add(lblTitle, BorderLayout.NORTH);
        
        String[] cols = new String[] {"Nome","Indirizzo","Città","CAP","Struttura_mq","Telefono","Email","CF_PIVA"};
        Object[][] data = new Object[][] {
            {"Unibo","Via dell'università 50","Cesena","47522","900","0088338550","unibo@unibo.it","0385719047300"},
            {"Mario Rossi","Via degli omonimi 33","Meldole","47014","150","95947524022","mario.rossi@oulook.it","MRORSS77M45D706Y"}
        };
        JTable table = new JTable(data,cols);
        //table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        this.add(new JScrollPane(table));
        
        
    }
    
    public void display() {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setVisible(true);
        setResizable(true);
     }

}
