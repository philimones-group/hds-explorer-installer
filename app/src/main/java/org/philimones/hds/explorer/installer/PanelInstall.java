/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import org.zeroturnaround.zip.ZipUtil;

/**
 *
 * @author paul
 */
public class PanelInstall extends javax.swing.JPanel implements IPage {
    
    static final String KEY_DATASOURCE_HOSTNAME = "dataSource.hostname";
    static final String KEY_DATASOURCE_DATABASE = "dataSource.database";
    static final String KEY_DATASOURCE_USERNAME = "dataSource.username";
    static final String KEY_DATASOURCE_PASSWORD = "dataSource.password";
    static final String KEY_DATASOURCE_DRIVER = "dataSource.driverClassName";
    
    static final String KEY_SYSTEM_PATH = "hds.explorer.system.path";
    
    static final String MYSQL_DRIVER_VALUE = "com.mysql.jdbc.Driver";
    static final String POSTGRES_DRIVER_VALUE = "org.postgresql.Driver";

    private File temporaryDirectory;
    private File hdsWarFile;
    private File outputDirectory;    
    private Map<String, String> appConfigMap;
    private String appConfigText;
    private String mysqlCreateText;
    private String postgresCreateText;
    private String readmeText;
    
    private InstallListener installListener;
    
    /**
     * Creates new form PanelDatabase
     */
    public PanelInstall() {
        initComponents();
        readCreateFiles();
        initListeners();
    }

    @Override
    public void setTemporaryDirectory(File temporaryDirectory) {
        this.temporaryDirectory = temporaryDirectory;
    }
    
    private void initListeners() {
        this.addAncestorListener ( new AncestorListener () {
            public void ancestorAdded ( AncestorEvent event ) {
                System.out.println("Component added somewhere " + event.getComponent());
            }

            public void ancestorRemoved ( AncestorEvent event ) {
                System.out.println("Component removed from container " + event.getComponent());
            }

            public void ancestorMoved ( AncestorEvent event ) {
                System.out.println("Component container moved "+ event.getComponent());
                
                new Thread() {
                    @Override
                    public void run() {
                        startInstall();
                    }
                    
                }.start();
                
            }
        } );
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
        lblConfiguringTutle = new javax.swing.JLabel();
        lblHostname = new javax.swing.JLabel();
        txtOutputDir = new javax.swing.JTextField();
        pbarConfiguring = new javax.swing.JProgressBar();
        lblConfiguring = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(701, 473));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(701, 473));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hds-icon-1.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("Gordita", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Configuring");
        lblTitle.setToolTipText("");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setMaximumSize(new java.awt.Dimension(230, 25));
        lblTitle.setMinimumSize(new java.awt.Dimension(230, 25));

        lblTitleInfo1.setText("The setup wizard will apply the selected parameters into the app java web archive");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitleInfo1)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(8, Short.MAX_VALUE))
        );

        lblConfiguringTutle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfiguringTutle.setText("<html>Configuring and generating files</html>");

        lblHostname.setText("Output dir:");

        txtOutputDir.setEnabled(false);
        txtOutputDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutputDirActionPerformed(evt);
            }
        });

        pbarConfiguring.setIndeterminate(true);

        lblConfiguring.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblConfiguring.setText("Saving data in hds-explorer-server.war");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblHostname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtOutputDir, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblConfiguringTutle)
                    .addComponent(lblConfiguring, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pbarConfiguring, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostname)
                    .addComponent(txtOutputDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addComponent(lblConfiguringTutle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lblConfiguring)
                .addGap(1, 1, 1)
                .addComponent(pbarConfiguring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(242, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtOutputDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutputDirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOutputDirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblConfiguring;
    private javax.swing.JLabel lblConfiguringTutle;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleInfo1;
    private javax.swing.JProgressBar pbarConfiguring;
    private javax.swing.JTextField txtOutputDir;
    // End of variables declaration//GEN-END:variables

    private void readCreateFiles() {
        StringBuilder textMysql = new StringBuilder();
        StringBuilder textPostg = new StringBuilder();
        StringBuilder textReadm = new StringBuilder();
        
        Scanner scan1 = new Scanner(getClass().getResourceAsStream("/samples/create-user-mysql.sql"));
        while (scan1.hasNextLine()) {
            String line = scan1.nextLine();
            textMysql.append(line+"\n");
        }
        scan1.close();
                        
        Scanner scan2 = new Scanner(getClass().getResourceAsStream("/samples/create-user-postgres.sql"));
        while (scan2.hasNextLine()) {
            String line = scan2.nextLine();
            textPostg.append(line+"\n");
        }
        scan2.close();
        
        Scanner scan3 = new Scanner(getClass().getResourceAsStream("/samples/README.html"));
        while (scan3.hasNextLine()) {
            String line = scan3.nextLine();
            textReadm.append(line+"\n");
        }
        scan3.close();
        
        this.mysqlCreateText = textMysql.toString();
        this.postgresCreateText = textPostg.toString();
        this.readmeText = textReadm.toString();
        
    }
    
    @Override
    public ValidationResult validatePage() {
        return new ValidationResult(ValidationType.ERROR, "No field was filled", null);
    }

    @Override
    public Map<String, String> exportValues() {
        return null;
    }
    
    public void setInstallListener(InstallListener listener) {
        this.installListener = listener;
    }
    
    public void setInstallData(File outputDir, File hdsWarFile, Map<String, String> allMap, String appConfigInText) {
        this.outputDirectory = outputDir;
        this.hdsWarFile = hdsWarFile;
        this.appConfigMap = allMap;
        this.appConfigText = appConfigInText;
        
        this.txtOutputDir.setText(this.outputDirectory.toString());
    }
    
    private void setConfiguringText(String text) {
        this.lblConfiguring.setText(text);
        this.lblConfiguring.invalidate();
        this.lblConfiguring.repaint();
    }

    public void startInstall() {
        System.out.println("starting install");
        
        this.pbarConfiguring.setIndeterminate(true);
        this.pbarConfiguring.invalidate();
        this.pbarConfiguring.repaint();
        
        
        //0. create a temporary directory - and extract war file to it
        File tempDir = this.temporaryDirectory; //createTemporaryDir();
        delay(500);
        
        File tempWarFile = this.hdsWarFile; //copyWarToTemporaryDir(tempDir); - its already in temporary directory
        delay(500);
        
        //1. create file app-config.yml and insert content
        File appConfigFile = createAppConfigFile(tempDir);
        delay(300);
        
        //2. create database file and insert content
        File databaseFile = createDatabaseFile(tempDir);
        delay(400);
        
        //3. create README file and insert content
        File readmeFile = createReadmeFile(tempDir, databaseFile, tempWarFile);
        delay(500);
        
        //4. insert app-config.yml inside hds-explorer-server.war        
        injectFileToWar(tempWarFile, this.outputDirectory, appConfigFile, "WEB-INF/classes/app-config.yml");
        delay(1000);
        
        //5. save the file hds-explorer-server.war, app-config.yml, create-database, REAME.txt in output directory
        setConfiguringText("Saving all created files in Output Directory");
        //copyFileToDirectory(this.outputDirectory, tempWarFile);
        copyFileToDirectory(this.outputDirectory, appConfigFile);
        copyFileToDirectory(this.outputDirectory, databaseFile);
        copyFileToDirectory(this.outputDirectory, readmeFile);
        delay(500);
        
        finishInstall();        
    }
    
    private File createTemporaryDir() {
        try {
            setConfiguringText("Creating Temporary Directory");
            Path path = Files.createTempDirectory("hdsInstaller");
            return path.toFile();
            
        } catch (IOException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private File copyWarToTemporaryDir(File tempDir) {
        
        setConfiguringText("Copying HDS-Explorer WAR file to temporary directory");
                
        return copyFileToDirectory(tempDir, this.hdsWarFile);
    }
    
    private File createAppConfigFile(File tempDir) {
        setConfiguringText("Creating and saving content to app-config.yml file");
        
        File appConfigFile = createNewFile(tempDir, "app-config.yml");
        writeToFile(appConfigFile, appConfigText);                            
        
        return appConfigFile;
    }
    
    private File createDatabaseFile(File tempDir) {
        
        setConfiguringText("Creating and saving user and database creation files");
        
        //create-user-mysql.sql
        //create-user-postgres.sql              
        
        String hostname = appConfigMap.get(KEY_DATASOURCE_HOSTNAME);
        String database = appConfigMap.get(KEY_DATASOURCE_DATABASE);
        String username = appConfigMap.get(KEY_DATASOURCE_USERNAME);
        String password = appConfigMap.get(KEY_DATASOURCE_PASSWORD);
        String dbdriver = appConfigMap.get(KEY_DATASOURCE_DRIVER);
        
        if (dbdriver.equals(MYSQL_DRIVER_VALUE)) {
            
            String content = mysqlCreateText;
            content = content.replace("$DATABASE", database);
            content = content.replace("$HOSTNAME", hostname);
            content = content.replace("$USERNAME", username);
            content = content.replace("$PASSWORD", password);
                        
            File newFile = createNewFile(tempDir, "create-user-mysql.sql");
            writeToFile(newFile, content);
            
            return newFile;
            
        } else if (dbdriver.equals(POSTGRES_DRIVER_VALUE)) {
            
            String content = postgresCreateText;
            content = content.replace("$DATABASE", database);
            content = content.replace("$HOSTNAME", hostname);
            content = content.replace("$USERNAME", username);
            content = content.replace("$PASSWORD", password);
            
            File newFile = createNewFile(tempDir, "create-user-postgres.sql");
            writeToFile(newFile, content);
            
            return newFile;
        }
        
        return null;
    }
    
    private File createReadmeFile(File tempDir, File sqlFile, File warFile) {
        
        setConfiguringText("Creating and saving README file");
        
        String sql_mysql = "mysql -u $MYSQL_USER -p &lt; $CREATE_USER_DB";
        String sql_postg = "psql -h $HOSTNAME -U $POSTGRES_USER -f $CREATE_USER_DB";
        
        File newFile = createNewFile(tempDir, "README.html");
        
        //readme stuff
        String resources = appConfigMap.get(KEY_SYSTEM_PATH);
        String dbdriver = appConfigMap.get(KEY_DATASOURCE_DRIVER);
        String dbms = (dbdriver.equals(MYSQL_DRIVER_VALUE)) ? "MySQL" : (dbdriver.equals(POSTGRES_DRIVER_VALUE)) ? "PostgreSQL" : "";
        String dbcreate_stmt = (dbdriver.equals(MYSQL_DRIVER_VALUE)) ? sql_mysql : (dbdriver.equals(POSTGRES_DRIVER_VALUE)) ? sql_postg : "";
                    
        String content = readmeText;
        content = content.replace("$DB_CREATE_STATEMENT", dbcreate_stmt);
        content = content.replace("$OUTPUT_DIR", this.outputDirectory.getAbsolutePath());
        content = content.replace("$CREATE_USER_DB", sqlFile.getName());
        content = content.replace("$RESOURCES_DIR", resources);
        content = content.replace("$WAR_FILENAME", warFile.getName());
        content = content.replace("$DBMS", dbms);
        
        writeToFile(newFile, content);
        
        return newFile;
    }
    
    private void injectFileToWar(File warFile, File destinationDirectory, File fileToInject, String path) {
        
        setConfiguringText("Injecting app-config.yml file into HDS-Explorer WAR File");
                 
        System.out.println("conf: "+fileToInject.getAbsolutePath());
        System.out.println("url: "+warFile.toString());

        File destWarFile = createNewFile(destinationDirectory, warFile.getName());
        
        boolean replaced = ZipUtil.replaceEntry(warFile, path, fileToInject, destWarFile);

        System.out.println("AppConfig replaced: "+replaced);
           
    }
    
    private File createNewFile(File fileDirectory, String newFilename) {
        File newFile = new File(fileDirectory.getAbsolutePath() + "/" + newFilename);
        
        try {
            newFile.createNewFile();
            return newFile;
            
        } catch (IOException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void writeToFile(File file, String content) {
         try {
            PrintStream output = new PrintStream(file);
            output.print(content);
            output.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private File copyFileToDirectory(File fileDirectory, File fileToCopy) {
        File newFile = new File(fileDirectory.getAbsolutePath() + "/" + fileToCopy.getName());
        
        try {
            Path newPath = Files.copy(fileToCopy.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
            return newPath.toFile();
            
        } catch (IOException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }        
        
        return null;
    }
    
    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void finishInstall() {
        
        setConfiguringText("Finished configuring HDS-Explorer Server Files");
        
        this.pbarConfiguring.setIndeterminate(false);
        this.pbarConfiguring.setMaximum(100);
        this.pbarConfiguring.setValue(100);
        this.pbarConfiguring.invalidate();
        this.pbarConfiguring.repaint();
        
        if (this.installListener != null) {
            this.installListener.finishedInstallation();
        }
    }
    
}
