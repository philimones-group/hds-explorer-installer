/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author paul
 */
public class PanelDatabase extends javax.swing.JPanel implements IPage {

    static final String DRIVER_POSTGRES = "org.postgresql.Driver";
    static final String DIALECT_POSTGRES = "org.hibernate.dialect.PostgreSQL94Dialect";
    static final String DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";    
    static final String DIALECT_MYSQL = "org.hibernate.dialect.MySQL8Dialect";
    static final String URL_POSTGRES = "jdbc:postgresql://$HOSTNAME:$PORT/$DATABASE";
    static final String URL_MYSQL = "jdbc:mysql://$HOSTNAME:$PORT/$DATABASE?createDatabaseIfNotExist=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true";
    static final String URL_MYSQL_EXPORT = "jdbc:mysql://$HOSTNAME:$PORT/$DATABASE?createDatabaseIfNotExist=true&autoReconnect=true&readOnly=true&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true";
    static final String DOCKER_HOSTNAME = "docker-hds-db";
        
    static final String KEY_DATASOURCE_POOLED = "dataSource.pooled";
    static final String KEY_DATASOURCE_DRIVER = "dataSource.driverClassName";
    static final String KEY_DATASOURCE_DIALECT = "dataSource.dialect";
    static final String KEY_DATASOURCE_USERNAME = "dataSource.username";
    static final String KEY_DATASOURCE_PASSWORD = "dataSource.password";
    static final String KEY_DATASOURCE_URL = "dataSource.url";
    static final String KEY_DATASOURCE_HOSTNAME = "dataSource.hostname";
    static final String KEY_DATASOURCE_DATABASE = "dataSource.database";
    
    static final String KEY_EXPORT_DATASOURCE_POOLED   = "dataSource_export.pooled";
    static final String KEY_EXPORT_DATASOURCE_DRIVER   = "dataSource_export.driverClassName";
    static final String KEY_EXPORT_DATASOURCE_DIALECT  = "dataSource_export.dialect";
    static final String KEY_EXPORT_DATASOURCE_USERNAME = "dataSource_export.username";
    static final String KEY_EXPORT_DATASOURCE_PASSWORD = "dataSource_export.password";
    static final String KEY_EXPORT_DATASOURCE_URL      = "dataSource_export.url";
    
    static final String KEY_SYSTEM_PATH = "hds.explorer.system.path";
    
    static File LOADED_CONFIG_PATH = null;
    
    private JFileChooser configFileChooser;
    private Map<String, String> mapConfigFile;
    
    /**
     * Creates new form PanelDatabase
     */
    public PanelDatabase() {
        initComponents();
        
        this.mapConfigFile = new LinkedHashMap<>();
                        
        checkboxesGroup.add(chkMySqlDb);
        checkboxesGroup.add(chkPostgresDb);
        
        this.configFileChooser = new JFileChooser();
        this.configFileChooser.setCurrentDirectory(new File("."));
        this.configFileChooser.setDialogTitle("Select the Configuration File");
        this.configFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.configFileChooser.setFileFilter(new FileNameExtensionFilter("HDS-Explorer YAML Config File", "yml"));
        this.configFileChooser.setAcceptAllFileFilterUsed(false);
    }

    @Override
    public void setTemporaryDirectory(File temporaryDirectory) {

    }

    private String getUrl() {
        
        boolean hasDockerHostname = chkExportDockerHostname.isSelected();
        
        if (chkMySqlDb.isSelected()) {
            return createMySqlUrl(hasDockerHostname ? DOCKER_HOSTNAME : txtHostname.getText(), txtDatabase.getText(), txtPort.getText());
        } else {
            return createPostgresUrl(hasDockerHostname ? DOCKER_HOSTNAME : txtHostname.getText(), txtDatabase.getText(), txtPort.getText());
        }
    }
    
    private String createMySqlUrl(String hostname, String database, String port) {
        String url = URL_MYSQL;
        
        url = url.replace("$HOSTNAME", hostname);
        url = url.replace("$DATABASE", database);
        url = url.replace("$PORT", port);
        
        return url;
    }
    
    private String createPostgresUrl(String hostname, String database, String port) {
        String url = URL_POSTGRES;
        
        url = url.replace("$HOSTNAME", hostname);
        url = url.replace("$DATABASE", database);
        url = url.replace("$PORT", port);
        
        return url;
    }

    private String getExportUrl() {
        
        boolean hasDockerHostname = chkExportDockerHostname.isSelected();
        
        if (chkMySqlDb.isSelected()) {
            return createMySqlExportUrl(hasDockerHostname ? DOCKER_HOSTNAME : txtHostname.getText(), txtDatabase.getText(), txtPort.getText());
        } else {
            return createPostgresExportUrl(hasDockerHostname ? DOCKER_HOSTNAME : txtHostname.getText(), txtDatabase.getText(), txtPort.getText());
        }
    }

    private String createMySqlExportUrl(String hostname, String database, String port) {
        String url = URL_MYSQL_EXPORT;
        
        url = url.replace("$HOSTNAME", hostname);
        url = url.replace("$DATABASE", database);
        url = url.replace("$PORT", port);
        
        return url;
    }
    
    private String createPostgresExportUrl(String hostname, String database, String port) {
        String url = "jdbc:postgresql://$HOSTNAME:$PORT/$DATABASE?readOnly=true";
        url = url.replace("$HOSTNAME", hostname);
        url = url.replace("$DATABASE", database);
        url = url.replace("$PORT", port);
        return url;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkboxesGroup = new javax.swing.ButtonGroup();
        panelTop = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        lblTitle = new javax.swing.JLabel();
        lblTitleInfo1 = new javax.swing.JLabel();
        lblTitleInfo2 = new javax.swing.JLabel();
        panelDbms = new javax.swing.JPanel();
        chkMySqlDb = new javax.swing.JRadioButton();
        chkPostgresDb = new javax.swing.JRadioButton();
        panelSettings = new javax.swing.JPanel();
        lblHostname = new javax.swing.JLabel();
        lblPort = new javax.swing.JLabel();
        lblDatabase = new javax.swing.JLabel();
        txtHostname = new javax.swing.JTextField();
        txtPort = new javax.swing.JTextField();
        txtDatabase = new javax.swing.JTextField();
        chkExportDockerHostname = new javax.swing.JCheckBox();
        panelConfig = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtConfigFile = new javax.swing.JTextField();
        btLoadConfigFile = new javax.swing.JButton();
        panelUser = new javax.swing.JPanel();
        lblUsername1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        lblPassword = new javax.swing.JLabel();
        lblPasswordConfirm = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JPasswordField();
        panelExportUser = new javax.swing.JPanel();
        lblExportUsername = new javax.swing.JLabel();
        txtExportUsername = new javax.swing.JTextField();
        lblExportPassword = new javax.swing.JLabel();
        txtExportPassword = new javax.swing.JPasswordField();
        lblExportPasswordConfirm = new javax.swing.JLabel();
        txtExportPasswordConfirm = new javax.swing.JPasswordField();

        setMaximumSize(new java.awt.Dimension(701, 532));
        setMinimumSize(new java.awt.Dimension(701, 532));
        setPreferredSize(new java.awt.Dimension(701, 532));
        setRequestFocusEnabled(false);

        panelTop.setBackground(new java.awt.Color(255, 255, 255));
        panelTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hds-icon-1.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("Gordita", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Database server settings");
        lblTitle.setToolTipText("");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setMaximumSize(new java.awt.Dimension(230, 25));
        lblTitle.setMinimumSize(new java.awt.Dimension(230, 25));

        lblTitleInfo1.setText("Choose the database management system that will be used by HDS-Explorer");

        lblTitleInfo2.setText("Specify the host, port and user credentials to access the database server");

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleInfo2)
                    .addComponent(lblTitleInfo1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelTopLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblTitleInfo1)
                        .addGap(0, 0, 0)
                        .addComponent(lblTitleInfo2))
                    .addComponent(lblIcon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelDbms.setBorder(javax.swing.BorderFactory.createTitledBorder("DBMS Systems"));

        chkMySqlDb.setText("MySQL Database");
        chkMySqlDb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkMySqlDbItemStateChanged(evt);
            }
        });

        chkPostgresDb.setText("PostgreSQL Database");
        chkPostgresDb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                chkPostgresDbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelDbmsLayout = new javax.swing.GroupLayout(panelDbms);
        panelDbms.setLayout(panelDbmsLayout);
        panelDbmsLayout.setHorizontalGroup(
            panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDbmsLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPostgresDb)
                    .addComponent(chkMySqlDb))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelDbmsLayout.setVerticalGroup(
            panelDbmsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDbmsLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(chkMySqlDb)
                .addGap(20, 20, 20)
                .addComponent(chkPostgresDb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelSettings.setBorder(javax.swing.BorderFactory.createTitledBorder("Access settings"));

        lblHostname.setText("Database server hostname:");

        lblPort.setText("Database server port:");

        lblDatabase.setText("Database name:");

        txtHostname.setText("localhost");
        txtHostname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHostnameActionPerformed(evt);
            }
        });

        txtPort.setText("3306");

        txtDatabase.setText("hds_explorer_db");

        chkExportDockerHostname.setText("Export Docker Hostname");

        javax.swing.GroupLayout panelSettingsLayout = new javax.swing.GroupLayout(panelSettings);
        panelSettings.setLayout(panelSettingsLayout);
        panelSettingsLayout.setHorizontalGroup(
            panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSettingsLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHostname)
                    .addComponent(lblPort)
                    .addComponent(lblDatabase))
                .addGap(16, 16, 16)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkExportDockerHostname)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSettingsLayout.setVerticalGroup(
            panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSettingsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostname)
                    .addComponent(txtHostname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelSettingsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabase)
                    .addComponent(txtDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkExportDockerHostname)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelConfig.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("Config File");

        txtConfigFile.setDisabledTextColor(new java.awt.Color(0, 51, 51));
        txtConfigFile.setEnabled(false);

        btLoadConfigFile.setText("Load saved config");
        btLoadConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoadConfigFileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelConfigLayout = new javax.swing.GroupLayout(panelConfig);
        panelConfig.setLayout(panelConfigLayout);
        panelConfigLayout.setHorizontalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLoadConfigFile, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
        );
        panelConfigLayout.setVerticalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConfigLayout.createSequentialGroup()
                .addGroup(panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btLoadConfigFile))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panelUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Main User"));

        lblUsername1.setText("Database username:");

        txtUsername.setText("hds_explorer");

        lblPassword.setText("Database password:");

        lblPasswordConfirm.setText("Retype database password:");

        javax.swing.GroupLayout panelUserLayout = new javax.swing.GroupLayout(panelUser);
        panelUser.setLayout(panelUserLayout);
        panelUserLayout.setHorizontalGroup(
            panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelUserLayout.createSequentialGroup()
                        .addComponent(lblPassword)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUserLayout.createSequentialGroup()
                        .addComponent(lblPasswordConfirm)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelUserLayout.createSequentialGroup()
                        .addComponent(lblUsername1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelUserLayout.setVerticalGroup(
            panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUsername1)
                    .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPasswordConfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelExportUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Export/AI User"));

        lblExportUsername.setText("Export username (read-only):");

        txtExportUsername.setText("hds_export");

        lblExportPassword.setText("Export user password:");

        lblExportPasswordConfirm.setText("Retype export password:");

        javax.swing.GroupLayout panelExportUserLayout = new javax.swing.GroupLayout(panelExportUser);
        panelExportUser.setLayout(panelExportUserLayout);
        panelExportUserLayout.setHorizontalGroup(
            panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExportUserLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblExportUsername)
                    .addComponent(lblExportPassword)
                    .addComponent(lblExportPasswordConfirm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtExportUsername, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                    .addComponent(txtExportPassword)
                    .addComponent(txtExportPasswordConfirm))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelExportUserLayout.setVerticalGroup(
            panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelExportUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExportUsername)
                    .addComponent(txtExportUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExportPassword)
                    .addComponent(txtExportPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelExportUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExportPasswordConfirm)
                    .addComponent(txtExportPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelConfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelDbms, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelExportUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelDbms, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelSettings, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelExportUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtHostnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHostnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHostnameActionPerformed

    private void btLoadConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoadConfigFileActionPerformed
        loadConfigurationFile();        // TODO add your handling code here:
    }//GEN-LAST:event_btLoadConfigFileActionPerformed

    private void chkMySqlDbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkMySqlDbItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtPort.setText("3306");
        }
    }//GEN-LAST:event_chkMySqlDbItemStateChanged

    private void chkPostgresDbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_chkPostgresDbItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtPort.setText("5432");
        }
    }//GEN-LAST:event_chkPostgresDbItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLoadConfigFile;
    private javax.swing.ButtonGroup checkboxesGroup;
    private javax.swing.JCheckBox chkExportDockerHostname;
    private javax.swing.JRadioButton chkMySqlDb;
    private javax.swing.JRadioButton chkPostgresDb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDatabase;
    private javax.swing.JLabel lblExportPassword;
    private javax.swing.JLabel lblExportPasswordConfirm;
    private javax.swing.JLabel lblExportUsername;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirm;
    private javax.swing.JLabel lblPort;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleInfo1;
    private javax.swing.JLabel lblTitleInfo2;
    private javax.swing.JLabel lblUsername1;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JPanel panelDbms;
    private javax.swing.JPanel panelExportUser;
    private javax.swing.JPanel panelSettings;
    private javax.swing.JPanel panelTop;
    private javax.swing.JPanel panelUser;
    private javax.swing.JTextField txtConfigFile;
    private javax.swing.JTextField txtDatabase;
    private javax.swing.JPasswordField txtExportPassword;
    private javax.swing.JPasswordField txtExportPasswordConfirm;
    private javax.swing.JTextField txtExportUsername;
    private javax.swing.JTextField txtHostname;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtPasswordConfirm;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

    @Override
    public ValidationResult validatePage() {
        
        ValidationType type = ValidationType.SUCCESS;
        Component component = null;
        String errorMessage = "";
        
        if (!chkMySqlDb.isSelected() && !chkPostgresDb.isSelected()) {
            errorMessage = "Please select one of database types showed in the left panel";
            type = ValidationType.ERROR;
            component = chkMySqlDb;
            
        } else if (StringUtil.isBlank(txtHostname.getText())) {
            errorMessage = "The hostname cannot be blank";
            type = ValidationType.ERROR;
            component = txtHostname;
            
        } else if (!StringUtil.isInteger(txtPort.getText())) {
            errorMessage = "The port number must be an integer value";
            type = ValidationType.ERROR;
            component = txtPort;
        } else if (StringUtil.isBlank(txtHostname.getText())) {
            errorMessage = "The hostname cannot be blank";
            type = ValidationType.ERROR;
            component = txtHostname;
            
        } else if (StringUtil.isBlank(txtDatabase.getText())) {
            errorMessage = "The database name/schema cannot be blank";
            type = ValidationType.ERROR;
            component = txtDatabase;
            
        } else if (StringUtil.isBlank(txtUsername.getText())) {
            errorMessage = "The username cannot be blank";
            type = ValidationType.ERROR;
            component = txtUsername;
            
        } else if (StringUtil.isBlank(new String(txtPassword.getPassword()))) {
            errorMessage = "The field password cannot be blank";
            type = ValidationType.ERROR;
            component = txtPassword;
            
        } else if (StringUtil.isBlank(new String(txtPasswordConfirm.getPassword()))) {
            errorMessage = "The retyped password cannot be blank";
            type = ValidationType.ERROR;
            component = txtPasswordConfirm;
            
        } else if (!StringUtil.equals(txtPassword.getPassword(), txtPasswordConfirm.getPassword())) {
            errorMessage = "The passwords doesnt match";
            type = ValidationType.ERROR;
            component = txtPasswordConfirm;

        } else if (StringUtil.isBlank(txtExportUsername.getText())) {
            errorMessage = "The export username cannot be blank";
            type = ValidationType.ERROR;
            component = txtExportUsername;

        } else if (StringUtil.isBlank(new String(txtExportPassword.getPassword()))) {
            errorMessage = "The export password field cannot be blank";
            type = ValidationType.ERROR;
            component = txtExportPassword;

        } else if (StringUtil.isBlank(new String(txtExportPasswordConfirm.getPassword()))) {
            errorMessage = "The retyped export password cannot be blank";
            type = ValidationType.ERROR;
            component = txtExportPasswordConfirm;

        } else if (!StringUtil.equals(txtExportPassword.getPassword(), txtExportPasswordConfirm.getPassword())) {
            errorMessage = "The export user passwords do not match";
            type = ValidationType.ERROR;
            component = txtExportPasswordConfirm;

        } else if (StringUtil.equals(txtUsername.getText().trim(), txtExportUsername.getText().trim())) {
            errorMessage = "The export username must be different from the main database username for security isolation";
            type = ValidationType.ERROR;
            component = txtExportUsername;
        }
        
        
        return new ValidationResult(type, errorMessage, component);
    }

    @Override
    public Map<String, String> exportValues() {
        Map<String,String> map = new LinkedHashMap<>();
        
        map.put(KEY_DATASOURCE_POOLED, "true");
        map.put(KEY_DATASOURCE_DRIVER, chkMySqlDb.isSelected() ? DRIVER_MYSQL : DRIVER_POSTGRES);
        map.put(KEY_DATASOURCE_DIALECT, chkMySqlDb.isSelected() ? DIALECT_MYSQL : DIALECT_POSTGRES);
        map.put(KEY_DATASOURCE_USERNAME, txtUsername.getText());
        map.put(KEY_DATASOURCE_PASSWORD, new String(txtPassword.getPassword()));
        map.put(KEY_DATASOURCE_URL, getUrl());
        map.put(KEY_DATASOURCE_HOSTNAME, txtHostname.getText());
        map.put(KEY_DATASOURCE_DATABASE, txtDatabase.getText());
        
        // Export Read-Only Data Export DataSource Keys
        map.put(KEY_EXPORT_DATASOURCE_POOLED, "true");
        map.put(KEY_EXPORT_DATASOURCE_DRIVER, chkMySqlDb.isSelected() ? DRIVER_MYSQL : DRIVER_POSTGRES);
        map.put(KEY_EXPORT_DATASOURCE_DIALECT, chkMySqlDb.isSelected() ? DIALECT_MYSQL : DIALECT_POSTGRES);
        map.put(KEY_EXPORT_DATASOURCE_USERNAME, txtExportUsername.getText().trim());
        map.put(KEY_EXPORT_DATASOURCE_PASSWORD, new String(txtExportPassword.getPassword()).trim());
        map.put(KEY_EXPORT_DATASOURCE_URL, getExportUrl());

        return map;
    }
    
    private void loadConfigurationFile() {
        int result = this.configFileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedConfigFile = this.configFileChooser.getSelectedFile();
            
            LOADED_CONFIG_PATH = selectedConfigFile.getParentFile();
            
            try {
                //validate yaml file
                Map<String, String> map = new LinkedHashMap<>();
                Scanner scan = new Scanner(selectedConfigFile);
                while (scan.hasNextLine()) {
                    String line = scan.nextLine();
                    
                    if (line.trim().startsWith("#")) continue;
                    
                    if (line.contains(": ")) {
                        String[] rsplit = line.split(": ");
                        map.put(rsplit[0].trim(), rsplit.length > 1 ? rsplit[1].trim() : "");
                    }
                }
                
                if (map.containsKey(KEY_SYSTEM_PATH)) { //is a valid config file
                    this.mapConfigFile.putAll(map);
                    txtConfigFile.setText(selectedConfigFile.getAbsolutePath());
                    updateParametersFromConfig();
                } else {
                    messageBox("Configuration File", "The selected configuration file is not a valid configuration file of HDS-Explorer");
                    txtConfigFile.setText("");
                    this.mapConfigFile.clear();
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PanelDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void updateParametersFromConfig() {
        
        for (Entry<String, String> entry :mapConfigFile.entrySet()) {
            System.out.println(entry.getKey()+" -> "+entry.getValue());
        }
        
        String url = mapConfigFile.get(KEY_DATASOURCE_URL);
        String hostname = getHostname(url);
        String database = getDatabase(url, hostname);
        String username = mapConfigFile.get(KEY_DATASOURCE_USERNAME);
        String password = mapConfigFile.get(KEY_DATASOURCE_PASSWORD);
        String dbdriver = mapConfigFile.get(KEY_DATASOURCE_DRIVER);
        
        boolean isMysql = dbdriver.equals(PanelInstall.MYSQL_DRIVER_VALUE);
        boolean isPostg = dbdriver.equals(PanelInstall.POSTGRES_DRIVER_VALUE);
        boolean isDocker = hostname.equals(DOCKER_HOSTNAME);
        
        chkMySqlDb.setSelected(isMysql);
        chkPostgresDb.setSelected(isPostg);
        
        chkExportDockerHostname.setSelected(isDocker);
        
        txtHostname.setText(hostname);
        txtPort.setText(getPort(url, hostname));
        txtDatabase.setText(database);
        txtUsername.setText(username);
        txtPassword.setText(password);
        txtPasswordConfirm.setText(password);

        String exportUsername = mapConfigFile.get(KEY_EXPORT_DATASOURCE_USERNAME);
        String exportPassword = mapConfigFile.get(KEY_EXPORT_DATASOURCE_PASSWORD);
        if (StringUtil.isBlank(exportUsername)) exportUsername = "hds_export";
        if (StringUtil.isBlank(exportPassword)) exportPassword = "hds_export_pass";

        txtExportUsername.setText(exportUsername);
        txtExportPassword.setText(exportPassword);
        txtExportPasswordConfirm.setText(exportPassword);
        
    }
    
    private String getHostname(String url) {
        //"jdbc:postgresql://$HOSTNAME:$PORT/$DATABASE"
        //"jdbc:mysql://$HOSTNAME:$PORT/$DATABASE?createDatabaseIfNotExist=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false"
                
        String hostname = url;
        
        hostname = hostname.replace("jdbc:postgresql://", "");
        hostname = hostname.replace("jdbc:mysql://", "");
        //hostname:3306/database or hostname/database
        
        if (!hostname.contains(":")) { //no database defined
            return hostname.substring(0, hostname.indexOf("/"));
        } else {
            return hostname.substring(0, hostname.indexOf(":"));
        }
        
    }
    
    private String getPort(String url, String hostname) {
        //"jdbc:postgresql://$HOSTNAME:$PORT/$DATABASE"
        //"jdbc:mysql://$HOSTNAME:$PORT/$DATABASE?createDatabaseIfNotExist=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false"
        
        boolean ismysql = url.startsWith("jdbc:mysql:");
        boolean ispostgres = url.startsWith("jdbc:postgresql:");
        
        String port = url;
        
        port = port.replace("jdbc:postgresql://"+hostname, "");
        port = port.replace("jdbc:mysql://"+hostname, "");
        //:3306/database or /database
        
        if (!port.startsWith(":")) { //no hostname defined
            return ismysql ? "3306" : ispostgres ? "5432" : "";
        }
        
        port = port.replace(":", "");
        port = port.substring(0, port.indexOf("/"));
                       
        return port;        
    }
    
    private String getDatabase(String url, String hostname) {
        //"jdbc:postgresql://$HOSTNAME:$PORT/$DATABASE"
        //"jdbc:mysql://$HOSTNAME:$PORT/$DATABASE?createDatabaseIfNotExist=true&autoReconnect=true&zeroDateTimeBehavior=convertToNull&useSSL=false"
        
        String database = url;
        
        database = database.replace("jdbc:postgresql://"+hostname, "");
        database = database.replace("jdbc:mysql://"+hostname, "");
        //:3306/database or /database
        
        database = database.substring(database.indexOf("/")+1);
        
        if (database.contains("?")) {
            return database.substring(0, database.indexOf("?")).trim();
        }
                               
        return database;        
    }

    public Map<String, String> getLoadedConfigurationFileMap() {
        return mapConfigFile;
    }       
    
    private void messageBox(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
