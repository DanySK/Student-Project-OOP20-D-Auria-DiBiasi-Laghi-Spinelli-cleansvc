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

import controller.ProcessImpl;
import model.step.SubSteps;
import model.step.SubStepsImpl;
import model.step.enumerations.StepType;

import utility.PopUp;
import utility.InputValidator;

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
    private JTextField txtDelete;
    private JComboBox<String> comboSteps;
    private List<JTextField> tfList = new ArrayList<>();
    private PopUp popUp = new PopUp();
    private InputValidator validator = new InputValidator();

    private final JButton btnSubmit;
    private final JButton btnRemove;
    private final JButton btnHome;

    private ProcessImpl process = ProcessImpl.getInstance();

    private final String[] cols = new String[] {"Codice", "Nome", "Descrizione", "Fase", "Tempo"};
    private Object[][] data = new Object[0][cols.length];
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

        for (int i = 0; i < process.getSubStepsList().size(); i++) {
            SubSteps subStep = process.getSubStepsList().get(i);
            model.insertRow(i, new Object[] {subStep.getCode(), subStep.getName(), subStep.getDescription(), subStep.getStepType(), subStep.getTime()});
        } 

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
        pnlData.setLayout(new GridLayout(5,2,0,45));
 
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

        for (StepType step : process.getStepTypeList()) {
            comboSteps.addItem(step.getType());
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
        pnlButtons.setPreferredSize(new Dimension(400, 30));
        pnlButtons.setMinimumSize(new Dimension(400, 30));
        pnlButtons.setLayout(new GridLayout(1,1,2,2));

        btnSubmit = new JButton("Inserimento");
        btnSubmit.setForeground(SystemColor.textText);
        btnSubmit.setBackground(SystemColor.activeCaption);
        btnSubmit.setPreferredSize(new Dimension(120,20));
        btnSubmit.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnSubmit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!missingField()) {
                    SubSteps c = new SubStepsImpl(getCode(), getTime(), getName(), getDescription(),process.getStepTypeList().get(getIndexComboStep()));
                    process.addStep(c);
                    popUp.popUpInfo("Sottofase inserita con successo.");
                    addSubStepToTable(process.getSubStepsList().get(process.getSubStepsList().size() - 1));
                    clearInsertField();
                } else {
                    popUp.popUpInfo("Ci sono dati mancanti o errati.");
                }
            }
        });
        pnlButtons.add(btnSubmit);
        pnlSubmit.add(pnlButtons, BorderLayout.SOUTH);

        final JPanel pnlRemove = new JPanel();
        pnlRemove.setBorder(new TitledBorder(null, "Elimina Sottofase", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        pnlRemove.setBackground(SystemColor.window);
        pnlRemove.setPreferredSize(new Dimension(400, 500));
        pnlRemove.setMinimumSize(new Dimension(400, 100));

        final JPanel pnlCode = new JPanel();
        pnlCode.setBorder(null);
        pnlCode.setBackground(SystemColor.window);
        pnlCode.setPreferredSize(new Dimension(390, 40));
        pnlCode.setMinimumSize(new Dimension(390, 40));
        pnlCode.setLayout(new GridLayout(1,1,0,20));

        JLabel lblsearchCode = new JLabel("Codice:");
        lblsearchCode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlCode.add(lblsearchCode);

        txtDelete = new JTextField(20);
        txtDelete.setFont(new Font("Tahoma", Font.PLAIN, 14));
        pnlCode.add(txtDelete);
        tfList.add(txtDelete);

        pnlRemove.add(pnlCode, BorderLayout.CENTER);
 
        final JPanel pnlButtons2 = new JPanel();
        pnlButtons2.setBackground(SystemColor.window);
        pnlButtons2.setBorder(null);
        pnlButtons2.setPreferredSize(new Dimension(390, 45));
        pnlButtons2.setMinimumSize(new Dimension(390, 45));
        pnlButtons2.setLayout(new GridLayout(1,1,2,2));

        btnRemove = new JButton("Elimina");
        btnRemove.setForeground(SystemColor.textText);
        btnRemove.setBackground(SystemColor.activeCaption);
        btnRemove.setPreferredSize(new Dimension(100,20));
        btnRemove.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        btnRemove.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                Optional<SubSteps> subStepRemove = process.searchSubStep(getSearchingCode());
                if (subStepRemove.isEmpty()) {
                    popUp.popUpWarning("Nessuna sottofase specificata");
                } else {
                    Boolean confirmed = popUp.popUpConfirm("Vuoi eliminare la sottofase " + subStepRemove.get().getName() + "?");
                    if (confirmed) {
                        popUp.popUpInfo("Sottofase eliminata con successo.");
                        removeSubStepToTable(subStepRemove.get());
                        process.removeStep(subStepRemove.get());
                        clearInsertField();
                    } else {
                        popUp.popUpInfo("Eliminazione annullata.");
                        }
                    }
            }
        });
        pnlButtons2.add(btnRemove);
        pnlRemove.add(pnlButtons2, BorderLayout.SOUTH);


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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlSubmit)
                                .addGap(0)
                                .addComponent(pnlRemove)))
                .addGap(0));

        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGap(0)
                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(panelTitle)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(panelTable)
                                .addGap(0)
                                .addGroup(layout.createParallelGroup(Alignment.CENTER)
                                        .addComponent(pnlSubmit)
                                        .addComponent(pnlRemove))))
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
        return (getCode().isEmpty() || getTime() == Integer.MIN_VALUE || getName().isEmpty() || getDescription().isEmpty() || comboSteps.getSelectedItem().equals("Seleziona step"));
    }

    /**
     * 
     * @param subStep
     */
    public void addSubStepToTable(final SubSteps subStep) {
        model.insertRow(process.getSubStepsList().size() - 1, new Object[] {subStep.getCode(), subStep.getName(), subStep.getDescription(), comboSteps.getSelectedItem(), subStep.getTime()});
    }
    /**
     * 
     * @param subStep
     */
    public void removeSubStepToTable(final SubSteps subStep) {
        for (int j = 0; j < model.getRowCount(); j++) {
            if (model.getDataVector().elementAt(j).elementAt(0).equals(subStep.getCode())) {
                model.removeRow(j);
            }
        }
    }

    /**
     * 
     * @return  code.
     */
    public String getSearchingCode() {
        return validator.isNameAndNum(txtDelete.getText()) ? txtDelete.getText() : "";
    }

    /**
     * @return code. 
     */
    public String getCode() {
        return validator.isNameAndNum(txtCode.getText()) ? txtCode.getText() : "";
    }

    /**
     * @return name. 
     */
    public String getName() {
        return validator.isName(txtName.getText()) ? txtName.getText() : "";
    }

    /**
     * @return time. 
     */
    public int getTime() {
        return validator.isInteger(txtTime.getText()) ? Integer.parseInt(txtTime.getText()) : Integer.MIN_VALUE;
    }

    /**
     * @return description.
     */
    public String getDescription() {
        return validator.isName(txtDescription.getText()) ? txtDescription.getText() : "";
    }
    
    /**
     * @return description.
     */
    public int getIndexComboStep() {
        return comboSteps.getSelectedIndex();
    }

    /**
     * 
     */
    public void display() {
        setVisible(true);
        setResizable(true);
    }
}
