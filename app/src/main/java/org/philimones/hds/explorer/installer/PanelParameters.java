/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author paul
 */
public class PanelParameters extends javax.swing.JPanel implements IPage {

    static final String KEY_MAIL_CONFIG = "hds.explorer.mail.configured";
    static final String KEY_MIN_FATHER_AGE = "hds.explorer.constraints.father.age.min";
    static final String KEY_MIN_MOTHER_AGE = "hds.explorer.constraints.mother.age.min";
    static final String KEY_MIN_HEAD_AGE = "hds.explorer.constraints.head.age.min";
    static final String KEY_MIN_SPOUSE_AGE = "hds.explorer.constraints.spouse.age.min";
    static final String KEY_MIN_RESPONDENT_AGE = "hds.explorer.constraints.respondent.age.min";
    static final String KEY_MAX_ANTEPARTUM_VISITS = "hds.explorer.constraints.pregnancy.antepartum.visits.max";
    static final String KEY_MAX_POSTPARTUM_VISITS = "hds.explorer.constraints.pregnancy.postpartum.visits.max";    
    static final String KEY_ANTEPARTUM_VISITS_SCHEDULE = "hds.explorer.constraints.pregnancy.antepartum.visits.schedule";
    static final String KEY_POSTPARTUM_VISITS_SCHEDULE = "hds.explorer.constraints.pregnancy.postpartum.visits.schedule";
    static final String KEY_GENDER_CHECK = "hds.explorer.constraints.gender.checking";
    static final String KEY_SYS_LANG = "hds.explorer.system.language";
    static final String KEY_SYS_CODEGEN = "hds.explorer.system.codegenerator"; //"org.philimone.hds.explorer.server.settings.generator.DefaultCodeGenerator"
    static final String KEY_SYS_CODEICR = "hds.explorer.system.codegenerator_rules.incremental";
    static final String KEY_SYSTEM_PATH = "hds.explorer.system.path"; //"/var/lib/hds-explorer"
    static final String KEY_SYSTEM_USE_ETHIOPIAN_CALENDAR = "hds.explorer.system.use.ethiopian.calendar";
    static final String KEY_SYSTEM_EXPORT_HISTORY_MODE = "hds.explorer.system.export.history.mode";    
    static final String KEY_EMAIL = "grails.mail.username";
    static final String KEY_EMAIL_PASSWORD = "grails.mail.password";
    
    static List<String> codeGeneratorList = Arrays.asList("org.philimone.hds.explorer.server.settings.generator.DefaultCodeGenerator", 
                                                          "org.philimone.hds.explorer.server.settings.generator.DefaultSimpleCodeGenerator",
                                                          "org.philimone.hds.explorer.server.settings.generator.CompoundSimpleCodeGenerator",
                                                          "org.philimone.hds.explorer.server.settings.generator.KimpeseHdssCodeGenerator",
                                                          "org.philimone.hds.explorer.server.settings.generator.NavrongoHdssCodeGenerator");
    static List<String> codeGeneratorRulesList = Arrays.asList("FILL_GAPS", "INCREMENT_LAST_CODE");
    
    private Map<String, String> mapConfigFile;
    private String historyExportMode;
    
    /**
     * Creates new form PanelDatabase
     */
    public PanelParameters() {
        initComponents();        
        initListeners();
        this.mapConfigFile = new LinkedHashMap<>();       
        
        setupTextChanges();
        makeNumericFields();
    }
    
    private void setupTextChanges(){
        txtMaxAntepartumVisits.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Triggered when text is typed or pasted
                onAntepartumChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Triggered when text is deleted or backspaced
                onAntepartumChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Rarely used for plain text fields (handles style changes)
            }
        });
        
        txtMaxPostpartumVisits.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Triggered when text is typed or pasted
                onPostpartumChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Triggered when text is deleted or backspaced
                onPostpartumChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Rarely used for plain text fields (handles style changes)
            }
        });
    }
    
    private void onAntepartumChanged(){
        Integer value = StringUtil.getInteger(txtMaxAntepartumVisits.getText());
        if (value != null) {
            enableAntepartumSchedules(value);
        }
    }
    
    private void onPostpartumChanged(){
        Integer value = StringUtil.getInteger(txtMaxPostpartumVisits.getText());
        if (value != null) {
            enablePostpartumSchedules(value);
        }
    }
        
    private void makeNumericFields() {
        makeNumericOnly(txtMinFatherAge);
        makeNumericOnly(txtMinMotherAge);
        makeNumericOnly(txtMinSpouseAge);
        makeNumericOnly(txtMinHeadAge);
        makeNumericOnly(txtMinRespondentAge);
        
        makeNumericOnly(txtMaxAntepartumVisits);
        makeNumericOnly(txtMaxPostpartumVisits);
        
        makeNumericOnly(txtVisitPos1);
        makeNumericOnly(txtVisitPos2);
        makeNumericOnly(txtVisitPos3);
        makeNumericOnly(txtVisitPos4);
        makeNumericOnly(txtVisitPos5);
        makeNumericOnly(txtVisitPos6);
        makeNumericOnly(txtVisitPos7);
        makeNumericOnly(txtVisitPos8);
        
        makeNumericOnly(txtVisitPos1);
        makeNumericOnly(txtVisitPos2);
        makeNumericOnly(txtVisitPos3);
        makeNumericOnly(txtVisitPos4);
        makeNumericOnly(txtVisitPos5);
        makeNumericOnly(txtVisitPos6);
        makeNumericOnly(txtVisitPos7);
        makeNumericOnly(txtVisitPos8);
    }
    
    private void enableAntepartumSchedules(int antepartumVisitsCount) {
        txtVisitAnt1.setEnabled(antepartumVisitsCount > 0);
        txtVisitAnt2.setEnabled(antepartumVisitsCount > 1);
        txtVisitAnt3.setEnabled(antepartumVisitsCount > 2);
        txtVisitAnt4.setEnabled(antepartumVisitsCount > 3);
        txtVisitAnt5.setEnabled(antepartumVisitsCount > 4);
        txtVisitAnt6.setEnabled(antepartumVisitsCount > 5);
        txtVisitAnt7.setEnabled(antepartumVisitsCount > 6);
        txtVisitAnt8.setEnabled(antepartumVisitsCount > 7);
    }
    
    private void enablePostpartumSchedules(int postpartumVisitsCount) {
        txtVisitPos1.setEnabled(postpartumVisitsCount > 0);
        txtVisitPos2.setEnabled(postpartumVisitsCount > 1);
        txtVisitPos3.setEnabled(postpartumVisitsCount > 2);
        txtVisitPos4.setEnabled(postpartumVisitsCount > 3);
        txtVisitPos5.setEnabled(postpartumVisitsCount > 4);
        txtVisitPos6.setEnabled(postpartumVisitsCount > 5);
        txtVisitPos7.setEnabled(postpartumVisitsCount > 6);
        txtVisitPos8.setEnabled(postpartumVisitsCount > 7);
    }
    
    public  void makeNumericOnly(JTextField field) {
        ((AbstractDocument) field.getDocument()).setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) 
                    throws BadLocationException {
                if (string != null && string.matches("\\d+")) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) 
                    throws BadLocationException {
                if (text != null && text.matches("\\d+")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }


    @Override
    public void setTemporaryDirectory(File temporaryDirectory) {

    }
    
    private void initListeners() {
        this.addAncestorListener ( new AncestorListener () {
            public void ancestorAdded ( AncestorEvent event ) {
                
            }

            public void ancestorRemoved ( AncestorEvent event ) {
                
            }

            public void ancestorMoved ( AncestorEvent event ) {
                
                
                new Thread() {
                    @Override
                    public void run() {
                        updateParametersFromConfig();
                    }
                    
                }.start();
                
            }
        } );
    }

    private String getSystemLanguage() {
        String[] langs = new String[] { "en", "fr", "pt", "am" };
        int i = cboSystemLanguage.getSelectedIndex();
        return langs[i];
    }
    
    private String getCodeGenerator() {        
        int i = cboSystemCodeGen.getSelectedIndex();
        return codeGeneratorList.get(i);
    }
    
    private String getCodeGeneratorIncrementalRule() {
        int i = cboSystemCodeGenIcr.getSelectedIndex();
        return codeGeneratorRulesList.get(i);
    }
    
    private int getSystemLanguageIndex(String value) {        
        List<String> list = Arrays.asList("en", "fr", "pt", "am");
        return list.indexOf(value);
    }
    
    private int getCodeGeneratorIndex(String value) {
        return codeGeneratorList.indexOf(value);
    }
    
    private int getCodeGeneratorIncRulesIndex(String value) {
        int index = codeGeneratorRulesList.indexOf(value);        
        return index >= 0 ? index : 0;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblTitleInfo1 = new javax.swing.JLabel();
        panelSettings = new javax.swing.JPanel();
        lblHostname = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblPasswordConfirm = new javax.swing.JLabel();
        txtSystemPath = new javax.swing.JTextField();
        txtMinFatherAge = new javax.swing.JTextField();
        panelDbms = new javax.swing.JPanel();
        chkUseGmail = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEmailPassword = new javax.swing.JPasswordField();
        cboSystemLanguage = new javax.swing.JComboBox<>();
        txtMinMotherAge = new javax.swing.JTextField();
        txtMinSpouseAge = new javax.swing.JTextField();
        lblPasswordConfirm1 = new javax.swing.JLabel();
        txtMinHeadAge = new javax.swing.JTextField();
        lblPort1 = new javax.swing.JLabel();
        cboSystemCodeGen = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        lblPort2 = new javax.swing.JLabel();
        cboSystemCodeGenIcr = new javax.swing.JComboBox<>();
        txtMinRespondentAge = new javax.swing.JTextField();
        lblPasswordConfirm2 = new javax.swing.JLabel();
        cboSystemCalendar = new javax.swing.JComboBox<>();
        lblPort3 = new javax.swing.JLabel();
        panelVisits = new javax.swing.JPanel();
        txtVisitAnt1 = new javax.swing.JTextField();
        lblPasswordConfirm3 = new javax.swing.JLabel();
        lblPasswordConfirm4 = new javax.swing.JLabel();
        txtMaxPostpartumVisits = new javax.swing.JTextField();
        txtMaxAntepartumVisits = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtVisitAnt2 = new javax.swing.JTextField();
        txtVisitAnt3 = new javax.swing.JTextField();
        txtVisitAnt4 = new javax.swing.JTextField();
        txtVisitAnt5 = new javax.swing.JTextField();
        txtVisitAnt6 = new javax.swing.JTextField();
        txtVisitAnt7 = new javax.swing.JTextField();
        txtVisitAnt8 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtVisitPos1 = new javax.swing.JTextField();
        txtVisitPos2 = new javax.swing.JTextField();
        txtVisitPos3 = new javax.swing.JTextField();
        txtVisitPos4 = new javax.swing.JTextField();
        txtVisitPos5 = new javax.swing.JTextField();
        txtVisitPos6 = new javax.swing.JTextField();
        txtVisitPos7 = new javax.swing.JTextField();
        txtVisitPos8 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(701, 532));
        setMinimumSize(new java.awt.Dimension(701, 532));
        setPreferredSize(new java.awt.Dimension(701, 532));
        setRequestFocusEnabled(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hds-icon-1.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("Gordita", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("System settings");
        lblTitle.setToolTipText("");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setMaximumSize(new java.awt.Dimension(230, 25));
        lblTitle.setMinimumSize(new java.awt.Dimension(230, 25));

        lblTitleInfo1.setText("Choose the default parameters of the system and HDSS site specific variables");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleInfo1))
                .addContainerGap(230, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblTitleInfo1))
                    .addComponent(lblIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"));

        lblHostname.setText("System Resources Path:");

        lblPort.setText("System Language:");

        lblUsername.setText("Minimum Father Age");

        lblPassword.setText("Minimum Mother Age:");

        lblPasswordConfirm.setText("Minimum Spouse Age:");

        txtSystemPath.setText("/var/lib/hds-explorer");
        txtSystemPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSystemPathActionPerformed(evt);
            }
        });

        txtMinFatherAge.setText("12");

        panelDbms.setBorder(javax.swing.BorderFactory.createTitledBorder("Mail Configuration"));
        panelDbms.setPreferredSize(new java.awt.Dimension(353, 134));

        chkUseGmail.setText("     Use Gmail SMTP");

        jLabel1.setText("Email:");

        txtEmail.setText("user@gmail.com");

        jLabel2.setText("Password");

        txtEmailPassword.setText("jPasswordField1");

        javax.swing.GroupLayout panelDbmsLayout = new javax.swing.GroupLayout(panelDbms);
        panelDbms.setLayout(panelDbmsLayout);
        panelDbmsLayout.setHorizontalGroup(
            panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDbmsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEmail)
                    .addComponent(txtEmailPassword))
                .addContainerGap())
            .addGroup(panelDbmsLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(chkUseGmail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDbmsLayout.setVerticalGroup(
            panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDbmsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(chkUseGmail)
                .addGap(18, 18, 18)
                .addGroup(panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtEmailPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cboSystemLanguage.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "French", "Portuguese", "Amharic (Ethiopian)" }));
        cboSystemLanguage.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSystemLanguageItemStateChanged(evt);
            }
        });
        cboSystemLanguage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSystemLanguageActionPerformed(evt);
            }
        });

        txtMinMotherAge.setText("12");

        txtMinSpouseAge.setText("12");

        lblPasswordConfirm1.setText("Minimum Head of Household Age:");

        txtMinHeadAge.setText("16");

        lblPort1.setText("System Code Generator:");

        cboSystemCodeGen.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Default Code Generator (Household no: TXUPF1001) (Region+User+001)", "Simple Code Generator   (Household no: TXU000001) (Region+000001)", "Compound Based Code Scheme Generator (Household no: TXU000001001) (RegionCompound+001)", "Kimpese HDSS Code Scheme Generator - DRC  (Household no: A04A00101) (Concession+01)", "Navrongo HDSS Compound-Based Code Scheme Generator  (Household no: TXU000001001) (RegionCompound+001)" }));
        cboSystemCodeGen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSystemCodeGenActionPerformed(evt);
            }
        });

        lblPort2.setText("Code Generator Incremental Rule:");

        cboSystemCodeGenIcr.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fill Gaps (Fill first missing codes that increment from Last)", "Increment the Last maximun code" }));
        cboSystemCodeGenIcr.setSelectedIndex(1);
        cboSystemCodeGenIcr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSystemCodeGenIcrActionPerformed(evt);
            }
        });

        txtMinRespondentAge.setText("12");
        txtMinRespondentAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinRespondentAgeActionPerformed(evt);
            }
        });

        lblPasswordConfirm2.setText("Minimum Respondent Age:");

        cboSystemCalendar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gregorian Calendar (Default)", "Ethiopian Calendar" }));
        cboSystemCalendar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboSystemCalendarItemStateChanged(evt);
            }
        });
        cboSystemCalendar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSystemCalendarActionPerformed(evt);
            }
        });

        lblPort3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPort3.setText("System Calendar:");

        panelVisits.setBorder(javax.swing.BorderFactory.createTitledBorder("Pregnancy Surveillance Visits"));

        txtVisitAnt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt1.setText("12");

        lblPasswordConfirm3.setText("Maximum Antepartum Visits:");

        lblPasswordConfirm4.setText("Maximum Postpartum Visits:");

        txtMaxPostpartumVisits.setText("4");

        txtMaxAntepartumVisits.setText("8");
        txtMaxAntepartumVisits.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaxAntepartumVisitsActionPerformed(evt);
            }
        });

        jLabel3.setText("Ante. Schedule");

        txtVisitAnt2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt2.setText("20");

        txtVisitAnt3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt3.setText("26");

        txtVisitAnt4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt4.setText("30");

        txtVisitAnt5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt5.setText("34");

        txtVisitAnt6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt6.setText("36");

        txtVisitAnt7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt7.setText("38");

        txtVisitAnt8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitAnt8.setText("40");

        jLabel4.setText("Weeks");

        jLabel5.setText("Post. Schedule");

        txtVisitPos1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos1.setText("3");

        txtVisitPos2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos2.setText("7");

        txtVisitPos3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos3.setText("21");

        txtVisitPos4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos4.setText("42");

        txtVisitPos5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos5.setEnabled(false);

        txtVisitPos6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos6.setEnabled(false);

        txtVisitPos7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos7.setEnabled(false);

        txtVisitPos8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVisitPos8.setEnabled(false);

        jLabel6.setText("Days");

        javax.swing.GroupLayout panelVisitsLayout = new javax.swing.GroupLayout(panelVisits);
        panelVisits.setLayout(panelVisitsLayout);
        panelVisitsLayout.setHorizontalGroup(
            panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelVisitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisitsLayout.createSequentialGroup()
                        .addComponent(lblPasswordConfirm3)
                        .addGap(16, 16, 16)
                        .addComponent(txtMaxAntepartumVisits, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addGroup(panelVisitsLayout.createSequentialGroup()
                        .addComponent(lblPasswordConfirm4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtMaxPostpartumVisits, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addGap(3, 3, 3)
                .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisitsLayout.createSequentialGroup()
                        .addComponent(txtVisitAnt1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitAnt8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4))
                    .addGroup(panelVisitsLayout.createSequentialGroup()
                        .addComponent(txtVisitPos1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVisitPos8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelVisitsLayout.setVerticalGroup(
            panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelVisitsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPasswordConfirm3)
                    .addComponent(txtMaxAntepartumVisits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtVisitAnt1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtVisitAnt8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPasswordConfirm4)
                        .addComponent(txtMaxPostpartumVisits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelVisitsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtVisitPos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtVisitPos8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelSettingsLayout = new javax.swing.GroupLayout(panelSettings);
        panelSettings.setLayout(panelSettingsLayout);
        panelSettingsLayout.setHorizontalGroup(
            panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(panelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSettingsLayout.createSequentialGroup()
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsername)
                            .addComponent(lblPassword)
                            .addComponent(lblPasswordConfirm)
                            .addComponent(lblPasswordConfirm1)
                            .addComponent(lblPasswordConfirm2))
                        .addGap(16, 16, 16)
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMinHeadAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinRespondentAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinFatherAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinMotherAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinSpouseAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(panelDbms, javax.swing.GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelSettingsLayout.createSequentialGroup()
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblHostname)
                            .addComponent(lblPort)
                            .addComponent(lblPort1)
                            .addComponent(lblPort2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboSystemCodeGen, 0, 0, Short.MAX_VALUE)
                            .addComponent(txtSystemPath)
                            .addGroup(panelSettingsLayout.createSequentialGroup()
                                .addComponent(cboSystemCodeGenIcr, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelSettingsLayout.createSequentialGroup()
                                .addComponent(cboSystemLanguage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPort3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboSystemCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(11, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSettingsLayout.createSequentialGroup()
                        .addComponent(panelVisits, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelSettingsLayout.setVerticalGroup(
            panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSettingsLayout.createSequentialGroup()
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostname)
                    .addComponent(txtSystemPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboSystemLanguage)
                    .addComponent(cboSystemCalendar)
                    .addComponent(lblPort3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPort, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboSystemCodeGen)
                    .addComponent(lblPort1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboSystemCodeGenIcr)
                    .addComponent(lblPort2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelSettingsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelSettingsLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPassword)
                                    .addComponent(txtMinMotherAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMinSpouseAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPasswordConfirm1)
                                    .addComponent(txtMinHeadAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPasswordConfirm2)
                                    .addComponent(txtMinRespondentAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(panelDbms, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)))
                    .addGroup(panelSettingsLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUsername)
                            .addComponent(txtMinFatherAge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addComponent(lblPasswordConfirm)
                        .addGap(60, 60, 60)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelVisits, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelVisits.getAccessibleContext().setAccessibleName("Visits Schedules");
        panelVisits.getAccessibleContext().setAccessibleParent(panelVisits);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelSettings, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(176, 176, 176))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtMinRespondentAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinRespondentAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinRespondentAgeActionPerformed

    private void cboSystemCodeGenIcrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSystemCodeGenIcrActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSystemCodeGenIcrActionPerformed

    private void cboSystemCodeGenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSystemCodeGenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSystemCodeGenActionPerformed

    private void txtSystemPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSystemPathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSystemPathActionPerformed

    private void txtMaxAntepartumVisitsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaxAntepartumVisitsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaxAntepartumVisitsActionPerformed

    private void cboSystemLanguageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSystemLanguageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSystemLanguageActionPerformed

    private void cboSystemLanguageItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSystemLanguageItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
          onSystemLanguageSelected(cboSystemLanguage.getSelectedIndex());
          // do something with object
       }
    }//GEN-LAST:event_cboSystemLanguageItemStateChanged

    private void cboSystemCalendarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboSystemCalendarItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSystemCalendarItemStateChanged

    private void cboSystemCalendarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSystemCalendarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSystemCalendarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboSystemCalendar;
    private javax.swing.JComboBox<String> cboSystemCodeGen;
    private javax.swing.JComboBox<String> cboSystemCodeGenIcr;
    private javax.swing.JComboBox<String> cboSystemLanguage;
    private javax.swing.JCheckBox chkUseGmail;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirm;
    private javax.swing.JLabel lblPasswordConfirm1;
    private javax.swing.JLabel lblPasswordConfirm2;
    private javax.swing.JLabel lblPasswordConfirm3;
    private javax.swing.JLabel lblPasswordConfirm4;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblPort1;
    private javax.swing.JLabel lblPort2;
    private javax.swing.JLabel lblPort3;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleInfo1;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPanel panelDbms;
    private javax.swing.JPanel panelSettings;
    private javax.swing.JPanel panelVisits;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtEmailPassword;
    private javax.swing.JTextField txtMaxAntepartumVisits;
    private javax.swing.JTextField txtMaxPostpartumVisits;
    private javax.swing.JTextField txtMinFatherAge;
    private javax.swing.JTextField txtMinHeadAge;
    private javax.swing.JTextField txtMinMotherAge;
    private javax.swing.JTextField txtMinRespondentAge;
    private javax.swing.JTextField txtMinSpouseAge;
    private javax.swing.JTextField txtSystemPath;
    private javax.swing.JTextField txtVisitAnt1;
    private javax.swing.JTextField txtVisitAnt2;
    private javax.swing.JTextField txtVisitAnt3;
    private javax.swing.JTextField txtVisitAnt4;
    private javax.swing.JTextField txtVisitAnt5;
    private javax.swing.JTextField txtVisitAnt6;
    private javax.swing.JTextField txtVisitAnt7;
    private javax.swing.JTextField txtVisitAnt8;
    private javax.swing.JTextField txtVisitPos1;
    private javax.swing.JTextField txtVisitPos2;
    private javax.swing.JTextField txtVisitPos3;
    private javax.swing.JTextField txtVisitPos4;
    private javax.swing.JTextField txtVisitPos5;
    private javax.swing.JTextField txtVisitPos6;
    private javax.swing.JTextField txtVisitPos7;
    private javax.swing.JTextField txtVisitPos8;
    // End of variables declaration//GEN-END:variables

    @Override
    public ValidationResult validatePage() {
        ValidationType type = ValidationType.SUCCESS;
        Component component = null;
        String errorMessage = "";
        
        if (StringUtil.isBlank(txtSystemPath.getText())) {
            errorMessage = "The System resources path cannot be blank";
            type = ValidationType.ERROR;
            component = txtSystemPath;
            
        } else if (!StringUtil.isIntegerGreaterThan(txtMinFatherAge.getText(), 0)) {
            errorMessage = "The Minimum Father age must be an integer value greater than zero";
            type = ValidationType.ERROR;
            component = txtMinFatherAge;
            
        } else if (!StringUtil.isIntegerGreaterThan(txtMinMotherAge.getText(), 0)) {
            errorMessage = "The Minimum Mother age must be an integer value greater than zero";
            type = ValidationType.ERROR;
            component = txtMinMotherAge;
            
        } else if (!StringUtil.isIntegerGreaterThan(txtMinSpouseAge.getText(), 0)) {
            errorMessage = "The Minimum Spouse age must be an integer value greater than zero";
            type = ValidationType.ERROR;
            component = txtMinSpouseAge;
            
        } else if (!StringUtil.isIntegerGreaterThan(txtMinHeadAge.getText(), 0)) {
            errorMessage = "The Minimum Head of Household age must be an integer value greater than zero";
            type = ValidationType.ERROR;
            component = txtMinHeadAge;
            
        } else if (!StringUtil.isIntegerGreaterThan(txtMinRespondentAge.getText(), 0)) {
            errorMessage = "The Minimum Respondent age must be an integer value greater than zero";
            type = ValidationType.ERROR;
            component = txtMinRespondentAge;
            
        } else if (chkUseGmail.isSelected() && StringUtil.isBlank(txtEmail.getText())) {
            errorMessage = "The field email cannot be blank";
            type = ValidationType.ERROR;
            component = txtEmail;
            
        } else if (chkUseGmail.isSelected() && StringUtil.isBlank(new String(txtEmailPassword.getPassword()))) {
            errorMessage = "The field password cannot be blank";
            type = ValidationType.ERROR;
            component = txtEmailPassword;
            
        } else if (!StringUtil.isIntegerGreaterThanAndLessThan(txtMaxAntepartumVisits.getText(), 1, 8)) {
            errorMessage = "The Maximum Number of Pregnancy Antepartum Visits must be an integer value greater than zero and less or equal to 8";
            type = ValidationType.ERROR;
            component = txtMaxPostpartumVisits;
            
        } else if (!StringUtil.isIntegerGreaterThanAndLessThan(txtMaxPostpartumVisits.getText(), 1, 8)) {
            errorMessage = "The Maximum Number of Pregnancy Postpartum Visits must be an integer value greater than zero and less or equal to 8";
            type = ValidationType.ERROR;
            component = txtMaxPostpartumVisits;            
        } else if (!isScheduleValid(txtVisitAnt1, null)) {
            errorMessage = "The number of weeks of the (1st antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt1;
        } else if (!isScheduleValid(txtVisitAnt2, txtVisitAnt1)) {
            errorMessage = "The number of weeks of the (2nd antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt2;
        } else if (!isScheduleValid(txtVisitAnt3, txtVisitAnt2)) {
            errorMessage = "The number of weeks of the (3rd antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt3;
        } else if (!isScheduleValid(txtVisitAnt4, txtVisitAnt3)) {
            errorMessage = "The number of weeks of the (4th antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt4;
        } else if (!isScheduleValid(txtVisitAnt5, txtVisitAnt4)) {
            errorMessage = "The number of weeks of the (5th antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt5;
        } else if (!isScheduleValid(txtVisitAnt6, txtVisitAnt5)) {
            errorMessage = "The number of weeks of the (6th antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt6;
        } else if (!isScheduleValid(txtVisitAnt7, txtVisitAnt6)) {
            errorMessage = "The number of weeks of the (7th antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt7;
        } else if (!isScheduleValid(txtVisitAnt8, txtVisitAnt7)) {
            errorMessage = "The number of weeks of the (8th antepartum visit) must be greater than zero and greater than the previous visit week number";
            type = ValidationType.ERROR;
            component = txtVisitAnt8;
        } else if (!isScheduleValid(txtVisitPos1, null)) {
            errorMessage = "The number of days of the (1st pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos1;
        } else if (!isScheduleValid(txtVisitPos2, txtVisitPos1)) {
            errorMessage = "The number of days of the (2nd pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos2;
        } else if (!isScheduleValid(txtVisitPos3, txtVisitPos2)) {
            errorMessage = "The number of days of the (3rd pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos3;
        } else if (!isScheduleValid(txtVisitPos4, txtVisitPos3)) {
            errorMessage = "The number of days of the (4th pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos4;
        } else if (!isScheduleValid(txtVisitPos5, txtVisitPos4)) {
            errorMessage = "The number of days of the (5th pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos5;
        } else if (!isScheduleValid(txtVisitPos6, txtVisitPos5)) {
            errorMessage = "The number of days of the (6th pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos6;
        } else if (!isScheduleValid(txtVisitPos7, txtVisitPos6)) {
            errorMessage = "The number of days of the (7th pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos7;
        } else if (!isScheduleValid(txtVisitPos8, txtVisitPos7)) {
            errorMessage = "The number of days of the (8th pospartum visit) must be greater than zero and greater than the previous visit day number";
            type = ValidationType.ERROR;
            component = txtVisitPos8;
        }
        
        return new ValidationResult(type, errorMessage, component);
    }    
    
    private boolean isScheduleValid(JTextField currentField, JTextField previousField){
        if (!currentField.isEnabled()) return true;
        
        Integer currentValue = StringUtil.getInteger(currentField.getText());
        Integer previousValue = previousField == null ? null : StringUtil.getInteger(previousField.getText());
        
        if (previousValue == null && currentValue > 0) {
            return true;
        } else if (currentValue > 0 && currentValue > previousValue) {
            return true;
        } else {
            return false;            
        }
    }

    @Override
    public Map<String, String> exportValues() {
        Map<String,String> map = new LinkedHashMap<>();
        map.put(KEY_MAIL_CONFIG, chkUseGmail.isSelected() + "");
        map.put(KEY_MIN_FATHER_AGE, txtMinFatherAge.getText());
        map.put(KEY_MIN_MOTHER_AGE, txtMinMotherAge.getText());
        map.put(KEY_MIN_HEAD_AGE, txtMinHeadAge.getText());
        map.put(KEY_MIN_SPOUSE_AGE, txtMinSpouseAge.getText());
        map.put(KEY_MIN_RESPONDENT_AGE, txtMinRespondentAge.getText());
        map.put(KEY_MAX_ANTEPARTUM_VISITS, txtMaxAntepartumVisits.getText());
        map.put(KEY_MAX_POSTPARTUM_VISITS, txtMaxPostpartumVisits.getText());
        map.put(KEY_ANTEPARTUM_VISITS_SCHEDULE, getAntepartumSchedule());
        map.put(KEY_POSTPARTUM_VISITS_SCHEDULE, getPospartumSchedule());
        map.put(KEY_GENDER_CHECK, "true");
        map.put(KEY_SYS_LANG, getSystemLanguage());
        map.put(KEY_SYS_CODEGEN, getCodeGenerator());
        map.put(KEY_SYS_CODEICR, getCodeGeneratorIncrementalRule());
        map.put(KEY_SYSTEM_PATH, txtSystemPath.getText());
        map.put(KEY_SYSTEM_USE_ETHIOPIAN_CALENDAR, isEthiopianSelected()+""); //chkUseEthiopianCalendar.isSelected()+"");
        map.put(KEY_SYSTEM_EXPORT_HISTORY_MODE, historyExportMode==null ? "FULL" : historyExportMode);
        map.put(KEY_EMAIL, txtEmail.getText());
        map.put(KEY_EMAIL_PASSWORD, new String(txtEmailPassword.getPassword()));
        
        return map;
    }
    
    public void setLoadedConfigurationMap(Map<String, String> map) {
        this.mapConfigFile.putAll(map);
    }
    
    private void updateParametersFromConfig() {

        if (!this.mapConfigFile.isEmpty()) {
            String respath = mapConfigFile.get(KEY_SYSTEM_PATH);
            String syslang = mapConfigFile.get(KEY_SYS_LANG);
            String codegen = mapConfigFile.get(KEY_SYS_CODEGEN);
            String codeicr = mapConfigFile.get(KEY_SYS_CODEICR);
            String minfath = mapConfigFile.get(KEY_MIN_FATHER_AGE);
            String minmoth = mapConfigFile.get(KEY_MIN_MOTHER_AGE);
            String minspou = mapConfigFile.get(KEY_MIN_SPOUSE_AGE);
            String minresp = mapConfigFile.get(KEY_MIN_RESPONDENT_AGE);
            String maxanvi = mapConfigFile.get(KEY_MAX_ANTEPARTUM_VISITS);
            String maxpovi = mapConfigFile.get(KEY_MAX_POSTPARTUM_VISITS);
            String antschd = mapConfigFile.get(KEY_ANTEPARTUM_VISITS_SCHEDULE);
            String posschd = mapConfigFile.get(KEY_POSTPARTUM_VISITS_SCHEDULE);
            String minhead = mapConfigFile.get(KEY_MIN_HEAD_AGE);
            String mailcon = mapConfigFile.get(KEY_MAIL_CONFIG);
            String emailad = mapConfigFile.get(KEY_EMAIL);
            String passwrd = mapConfigFile.get(KEY_EMAIL_PASSWORD);
            String ethipca = mapConfigFile.get(KEY_SYSTEM_USE_ETHIOPIAN_CALENDAR);
            String hisexmd = mapConfigFile.get(KEY_SYSTEM_EXPORT_HISTORY_MODE);

            if (StringUtil.isBlank(maxanvi)) maxanvi = "4";
            if (StringUtil.isBlank(maxpovi)) maxpovi = "4";
            if (StringUtil.isBlank(minresp)) minresp = "12";
            if (StringUtil.isBlank(hisexmd)) hisexmd = "FULL";

            txtSystemPath.setText(respath);
            cboSystemLanguage.setSelectedIndex(getSystemLanguageIndex(syslang));
            //chkUseEthiopianCalendar.setSelected(getBoolean(ethipca));
            setSystemCalendar(getBoolean(ethipca));
            cboSystemCodeGen.setSelectedIndex(getCodeGeneratorIndex(codegen));
            cboSystemCodeGenIcr.setSelectedIndex(getCodeGeneratorIncRulesIndex(codeicr));            
            txtMinFatherAge.setText(minfath);
            txtMinMotherAge.setText(minmoth);
            txtMinSpouseAge.setText(minspou);
            txtMinHeadAge.setText(minhead);
            txtMinRespondentAge.setText(minresp);
            txtMaxAntepartumVisits.setText(maxanvi);
            txtMaxPostpartumVisits.setText(maxpovi);
            setAntepartumSchedule(antschd);
            setPospartumSchedule(posschd);
            chkUseGmail.setSelected(mailcon.equals("true"));
            txtEmail.setText(emailad);
            txtEmailPassword.setText(passwrd);
            historyExportMode = hisexmd;
        }
        
    }
    
    private boolean getBoolean(String value){
        try {
            return Boolean.parseBoolean(value);
        } catch (Exception ex) {
            return false;
        }
    }

    private void onSystemLanguageSelected(int selectedIndex) {
        List<String> list = Arrays.asList("en", "fr", "pt", "am");
        
        /*if (selectedIndex == 3) { //AHMARIC
            chkUseEthiopianCalendar.setVisible(true);
            chkUseEthiopianCalendar.setSelected(true);
        } else {
            chkUseEthiopianCalendar.setVisible(false);
            chkUseEthiopianCalendar.setSelected(false);
        }*/
    }
    
    private void setSystemCalendar(boolean isEthiopianSelected){
        cboSystemCalendar.setSelectedIndex(isEthiopianSelected ? 1 : 0);
    }
    
    private boolean isEthiopianSelected() {
        return cboSystemCalendar.getSelectedIndex()==1;
    }

    private String getAntepartumSchedule() {
        StringBuilder sb = new StringBuilder();
        
        if (txtVisitAnt1.isEnabled()) sb.append(txtVisitAnt1.getText());
        if (txtVisitAnt2.isEnabled()) sb.append("," + txtVisitAnt2.getText());
        if (txtVisitAnt3.isEnabled()) sb.append("," + txtVisitAnt3.getText());
        if (txtVisitAnt4.isEnabled()) sb.append("," + txtVisitAnt4.getText());
        if (txtVisitAnt5.isEnabled()) sb.append("," + txtVisitAnt5.getText());
        if (txtVisitAnt6.isEnabled()) sb.append("," + txtVisitAnt6.getText());
        if (txtVisitAnt7.isEnabled()) sb.append("," + txtVisitAnt7.getText());
        if (txtVisitAnt8.isEnabled()) sb.append("," + txtVisitAnt8.getText());
        
        return sb.toString();
    }

    private String getPospartumSchedule() {
        StringBuilder sb = new StringBuilder();
        
        if (txtVisitPos1.isEnabled()) sb.append(txtVisitPos1.getText());
        if (txtVisitPos2.isEnabled()) sb.append("," + txtVisitPos2.getText());
        if (txtVisitPos3.isEnabled()) sb.append("," + txtVisitPos3.getText());
        if (txtVisitPos4.isEnabled()) sb.append("," + txtVisitPos4.getText());
        if (txtVisitPos5.isEnabled()) sb.append("," + txtVisitPos5.getText());
        if (txtVisitPos6.isEnabled()) sb.append("," + txtVisitPos6.getText());
        if (txtVisitPos7.isEnabled()) sb.append("," + txtVisitPos7.getText());
        if (txtVisitPos8.isEnabled()) sb.append("," + txtVisitPos8.getText());
        
        return sb.toString();
    }

    private void setAntepartumSchedule(String antschd) {
        int[] values = StringUtil.splitToNumbers(antschd);
        if (values.length > 0) txtVisitAnt1.setText(values[0]+"");
        if (values.length > 1) txtVisitAnt2.setText(values[1]+"");
        if (values.length > 2) txtVisitAnt3.setText(values[2]+"");
        if (values.length > 3) txtVisitAnt4.setText(values[3]+"");
        if (values.length > 4) txtVisitAnt5.setText(values[4]+"");
        if (values.length > 5) txtVisitAnt6.setText(values[5]+"");
        if (values.length > 6) txtVisitAnt7.setText(values[6]+"");
        if (values.length > 7) txtVisitAnt8.setText(values[7]+"");
    }

    private void setPospartumSchedule(String posschd) {
        int[] values = StringUtil.splitToNumbers(posschd);
        if (values.length > 0) txtVisitPos1.setText(values[0]+"");
        if (values.length > 1) txtVisitPos2.setText(values[1]+"");
        if (values.length > 2) txtVisitPos3.setText(values[2]+"");
        if (values.length > 3) txtVisitPos4.setText(values[3]+"");
        if (values.length > 4) txtVisitPos5.setText(values[4]+"");
        if (values.length > 5) txtVisitPos6.setText(values[5]+"");
        if (values.length > 6) txtVisitPos7.setText(values[6]+"");
        if (values.length > 7) txtVisitPos8.setText(values[7]+"");
    }

    
}
