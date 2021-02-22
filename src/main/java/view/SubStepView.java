package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
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


import controller.Process;
import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;
import model.users.Clients;
import utility.PopUp;


public class SubStepView extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3375687914483476432L;
    private static final String TITLE = "Inserimento nuova sottofase";
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtDescription;
    private JTextField txtTime;
    List<JTextField> tfList = new ArrayList<>();
    private JComboBox<String> comboSteps;
    private PopUp popUp = new PopUp();

    private final JButton btnSubmit;
    private final JButton btnHome;

    private Process process = ProcessImpl.getInstance();
    private List<SubSteps> subStepsList = process.getSubStepsList();

    private final String[] cols = new String[] {"Codice", "Nome", "Descrizione", "Fase", "Tempo"};
    private Object[][] data = new Object[subStepsList.size()][cols.length];
    private DefaultTableModel model = new DefaultTableModel(data, cols);
    private JTable table = new JTable(model);

    public SubStepView() {

        setTitle(SubStepView.TITLE);
        setMinimumSize(new Dimension(1200, 600));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panelTable = new JPanel();
        panelTable.setMinimumSize(new Dimension(600, 200));
        panelTable.setBackground(SystemColor.activeCaption);
        panelTable.setLayout(new BorderLayout(0, 0));

        JPanel panelTitle = new JPanel();
        panelTitle.setMinimumSize(new Dimension(1000, 40));
        panelTitle.setBackground(SystemColor.activeCaption);
        panelTitle.setLayout(new BorderLayout(0, 0));

        JLabel lblTitle = new JLabel("Elenco SottoFasi");
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
            public void actionPerformed(final ActionEvent e) {
                model.setRowCount(0);
                HomeView cv = new HomeView();
                cv.display();
                dispose();
            }

        });
        panelTitle.add(btnHome, BorderLayout.EAST);
        panelTable.add(panelTitle, BorderLayout.NORTH);

        table.setPreferredScrollableViewportSize(new Dimension(1000, 500));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        panelTable.add(table, BorderLayout.CENTER);
        panelTable.add(new JScrollPane(table));

        final JPanel pnlSubmit = new JPanel();
        pnlSubmit.setBorder(new TitledBorder(null, "Dati Sottofase", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlSubmit.setBackground(SystemColor.window);
        pnlSubmit.setPreferredSize(new Dimension(400, 500));
        pnlSubmit.setMinimumSize(new Dimension(400, 400));
        pnlSubmit.setLayout(new BorderLayout(0,0));

        final JPanel pnlData = new JPanel();
        pnlData.setBorder(null);
        pnlData.setBackground(SystemColor.window);
        pnlData.setPreferredSize(new Dimension(400, 400));
        pnlData.setMinimumSize(new Dimension(400, 400));
        pnlData.setLayout(new GridLayout(5,2,0,70));
 
        JLabel labelCode = new JLabel("Codice:");
        labelCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelCode);

        txtCode = new JTextField(10);
        txtCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtCode);
        tfList.add(txtCode);

        JLabel labelName = new JLabel("Nome:");
        labelName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelName);

        txtName = new JTextField(10);
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtName);
        tfList.add(txtName);

        JLabel labelDescription = new JLabel("Descrizione:");
        labelDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelDescription);

        txtDescription = new JTextField(10);
        txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtDescription);
        tfList.add(txtDescription);

        JLabel labelStep = new JLabel("Step:");
        labelStep.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelStep);

        comboSteps = new JComboBox<>();
        comboSteps.setBackground(SystemColor.inactiveCaption);
        comboSteps.setForeground(SystemColor.textText);
        comboSteps.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(comboSteps);

        for (StepType myVar : StepType.values()) {
            comboSteps.addItem(myVar.getType());
        }

        JLabel labelTime = new JLabel("Tempo:");
        labelTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(labelTime);

        txtTime = new JTextField(10);
        txtTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlData.add(txtTime);
        tfList.add(txtTime);

        pnlSubmit.add(pnlData, BorderLayout.CENTER);

        final JPanel pnlButtons = new JPanel();
        pnlButtons.setBackground(SystemColor.window);
        pnlButtons.setBorder(null);
        pnlButtons.setPreferredSize(new Dimension(400, 50));
        pnlButtons.setMinimumSize(new Dimension(400, 50));
        pnlButtons.setLayout(new GridLayout(1,1,2,2));

        btnSubmit = new JButton("Inserisci nuovo");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setPreferredSize(new Dimension(120,20));
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    SubSteps c = new SubStepsImpl(getCode(), getTime(), getName(), getDescription());
                    process.addStep(c);
                    popUp.popUpInfo("Appuntamento inserito con successo.");
                    addSubStepToTable(process.getSubStepsList().get(process.getSubStepsList().size() - 1));
                    clearInsertField();
                } else {
                    popUp.popUpInfo("Ci sono dati mancanti o errati.");
                }
            }
        });
        pnlButtons.add(btnSubmit);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);


        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addComponent(panelTitle)
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(panelTable)
                        .addComponent(pnlSubmit))
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTitle)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTable)
                                .addGap(0)
                                .addComponent(pnlSubmit)))
                .addGap(0));

    }

    /**
     * 
     */
    public void clearInsertField() {
        for (JTextField tField : tfList) {
            tField.setText("");
        }
        comboSteps.setSelectedIndex(0);
    }

    /**
     * 
     * @return 0 if there is an empty textArea.
     */
    public Boolean missingField() {
        return (Boolean.parseBoolean(txtCode.getText()) || Boolean.parseBoolean(txtTime.getText()) || getName().isEmpty() || getDescription().isEmpty() || comboSteps.getSelectedItem().equals("Seleziona step"));
    }

    /**
     * 
     * @param subStep
     */
    public void addSubStepToTable(final SubSteps subStep) {
        model.insertRow(process.getSubStepsList().size() - 1, new Object[] {subStep.getCode(), subStep.getName(), subStep.getDescription(), comboSteps.getSelectedItem(), subStep.getTime()});
    }

    /**
     * @return name. 
     */
    public int getCode() {
        return Integer.parseInt(txtCode.getText());
    }

    /**
     * @return name. 
     */
    public String getName() {
        return txtName.getText();
    }


    /**
     * @return time. 
     */
    public int getTime() {
        return Integer.parseInt(txtTime.getText());
    }


    /**
     * @return description.
     */
    public String getDescription() {
        return txtDescription.getText();
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
