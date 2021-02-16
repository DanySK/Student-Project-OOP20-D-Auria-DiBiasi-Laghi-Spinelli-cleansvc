package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

public class AppointmentsView extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 2089945830206989799L;
    private static final String TITLE = "APPUNTAMENTI";
    private final JButton btnSubmit;
    private final JButton btnHome;

    public AppointmentsView() {

        setTitle(AppointmentsView.TITLE);
        setMinimumSize(new Dimension(1200, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 60));
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Richieste sanificazione");
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
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }
        });
        panelTitle.add(btnHome, BorderLayout.EAST);

        JPanel mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.SOUTH);
        mainPanel.setLayout(new BorderLayout(0, 0));

        JPanel calendarPanel = new JPanel();
        getContentPane().add(calendarPanel, BorderLayout.CENTER);
        calendarPanel.setLayout(new BorderLayout(0, 0));

        String[] cols = new String[] {"Data", "Ora", "Nome", "Indirizzo"};
        Object[][] data = new Object[][] {
            {"16/02/2021", "15:30", "Mario Rossi", "Via degli omonimi 33"}, 
            {"22/02/2021", "16:30", "Luigi Bianchi", "Via dell'università 50"}
        };

        JTable table = new JTable(data, cols);
        table.setPreferredScrollableViewportSize(new Dimension(500, 310));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true); //sort by the column header clicked
        calendarPanel.add(table, BorderLayout.CENTER);
        calendarPanel.add(new JScrollPane(table));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Vai all'inserimento di un nuovo appuntamento", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(10, 100));
        pnlSubmit.setMinimumSize(new Dimension(1000, 100));
        mainPanel.add(pnlSubmit, BorderLayout.SOUTH);
        btnSubmit = new JButton("Inserisci un nuovo appuntamento >");
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
        mainPanel.add(pnlSubmit);

    }

    public void display() {
        setVisible(true);
        setResizable(true);
    }

}
