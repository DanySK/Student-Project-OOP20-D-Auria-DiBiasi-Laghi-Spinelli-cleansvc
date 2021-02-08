package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HomeView extends JFrame{

    /**
     * 
     */
    private static final long serialVersionUID = -1440993813136999810L;
    private static final String TITLE = "CLEAN SERVICE MANAGER";
    final JButton btnClienti;
    final JButton btnGrafici;
    
    public HomeView() {
        
        setTitle(HomeView.TITLE);
        setLayout(new GridLayout(3,3));
        setMinimumSize(new Dimension(1000, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(10, 100));
        //panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.CENTER);
        panelTitle.setLayout(new FlowLayout());

        btnClienti = new JButton("Area Clienti");
        btnClienti.setForeground(SystemColor.textText);
        btnClienti.setBackground(SystemColor.activeCaption);
        btnClienti.setPreferredSize(new Dimension(280,70));
        btnClienti.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnClienti.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                ClientsView cv = new ClientsView();
                cv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnClienti);

        btnGrafici = new JButton("Area Grafici");
        btnGrafici.setForeground(SystemColor.textText);
        btnGrafici.setBackground(SystemColor.activeCaption);
        btnGrafici.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnGrafici.setPreferredSize(new Dimension(280,70));
        btnGrafici.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AdministratorChartsView cv = new AdministratorChartsView();
                cv.display();
                dispose();
            }
            
        });
        panelTitle.add(btnGrafici);
    }
    
    //public void addClientsListener(ActionListener ClientsListener) {
    //    btnClienti.addActionListener(ClientsListener);
    //}

    public void display() {
        final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setVisible(true);
        setResizable(true);
    }
}
