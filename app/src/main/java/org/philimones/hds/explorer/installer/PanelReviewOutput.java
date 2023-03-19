/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author paul
 */
public class PanelReviewOutput extends javax.swing.JPanel implements IPage {

    private JFileChooser warFileChooser;
    private JFileChooser outputDirChooser;
    private File selectedOutputDir;
    private File selectedWarFile;
    private File temporaryDirectory;
    
    /**
     * Creates new form PanelDatabase
     */
    public PanelReviewOutput() {
        initComponents();
        init();        
    }

    @Override
    public void setTemporaryDirectory(File temporaryDirectory) {
        this.temporaryDirectory = temporaryDirectory;
    }

    private void init() {
        this.warFileChooser = new JFileChooser();
        this.warFileChooser.setCurrentDirectory(new File("."));
        this.warFileChooser.setDialogTitle("Select the War File");
        this.warFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        this.warFileChooser.setFileFilter(new FileNameExtensionFilter("HDS-Explorer WAR File", "war"));
        this.warFileChooser.setAcceptAllFileFilterUsed(false);
                
        this.outputDirChooser = new JFileChooser();
        this.outputDirChooser.setCurrentDirectory(new File("."));
        this.outputDirChooser.setDialogTitle("Select the output directory");
        this.outputDirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.outputDirChooser.setAcceptAllFileFilterUsed(false);
        
        this.txtWarLocation.setText("");
        this.txtWarFilename.setText("");
        this.txtWarVersion.setText("");
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
        jScrollPane1 = new javax.swing.JScrollPane();
        txtReviewConfig = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtWarLocation = new javax.swing.JTextField();
        txtWarFilename = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtWarVersion = new javax.swing.JTextField();
        btSelectWar = new javax.swing.JButton();
        btSelectDir = new javax.swing.JButton();
        txtOutputDir = new javax.swing.JTextField();
        lblHostname = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(701, 473));
        setMinimumSize(new java.awt.Dimension(701, 473));
        setPreferredSize(new java.awt.Dimension(701, 473));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 90));

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/hds-icon-1.png"))); // NOI18N

        lblTitle.setFont(new java.awt.Font("Gordita", 1, 16)); // NOI18N
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblTitle.setText("Final step: Review and output");
        lblTitle.setToolTipText("");
        lblTitle.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblTitle.setMaximumSize(new java.awt.Dimension(230, 25));
        lblTitle.setMinimumSize(new java.awt.Dimension(230, 25));

        lblTitleInfo1.setText("<html>\nReview the configuration file that is generated by the previous panels. This setup wizard will generate a WAR file of HDS-Explorer and also export an SQL file with statements to create a user and grant access.\n</html>\n");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(lblTitleInfo1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblIcon))
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jScrollPane1.setMaximumSize(new java.awt.Dimension(675, 282));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(675, 282));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(675, 282));

        txtReviewConfig.setMaximumSize(new java.awt.Dimension(675, 2147483647));
        txtReviewConfig.setMinimumSize(new java.awt.Dimension(675, 22));
        txtReviewConfig.setPreferredSize(new java.awt.Dimension(675, 22));
        jScrollPane1.setViewportView(txtReviewConfig);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel2.setMaximumSize(new java.awt.Dimension(675, 81));

        jLabel1.setText("Deploy File:");

        txtWarLocation.setEditable(false);
        txtWarLocation.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWarLocation.setText("INTERNAL");
        txtWarLocation.setDisabledTextColor(new java.awt.Color(0, 51, 51));
        txtWarLocation.setEnabled(false);

        txtWarFilename.setEditable(false);
        txtWarFilename.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWarFilename.setText("hds-explorer-server-1.0.0 build 220.war");
        txtWarFilename.setDisabledTextColor(new java.awt.Color(0, 51, 51));
        txtWarFilename.setEnabled(false);

        jLabel2.setText("Version:");

        txtWarVersion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtWarVersion.setText("1.0.0 build 220");
        txtWarVersion.setDisabledTextColor(new java.awt.Color(0, 51, 51));
        txtWarVersion.setEnabled(false);

        btSelectWar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/war_icon.png"))); // NOI18N
        btSelectWar.setText("Select War");
        btSelectWar.setToolTipText("");
        btSelectWar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btSelectWar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSelectWar.setIconTextGap(3);
        btSelectWar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectWarActionPerformed(evt);
            }
        });

        btSelectDir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/baseline_folder_open_black_24dp.png"))); // NOI18N
        btSelectDir.setText("Select Dir");
        btSelectDir.setToolTipText("");
        btSelectDir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btSelectDir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btSelectDir.setIconTextGap(3);
        btSelectDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSelectDirActionPerformed(evt);
            }
        });

        txtOutputDir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOutputDirActionPerformed(evt);
            }
        });

        lblHostname.setText("Output dir:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHostname)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtWarLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtWarFilename, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(6, 6, 6)
                        .addComponent(txtWarVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btSelectWar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtOutputDir, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(btSelectDir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtWarLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtWarFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtWarVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelectWar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHostname)
                    .addComponent(txtOutputDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSelectDir))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtOutputDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOutputDirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOutputDirActionPerformed

    private void btSelectDirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectDirActionPerformed
        selectOutputDirectory();
    }//GEN-LAST:event_btSelectDirActionPerformed

    private void btSelectWarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSelectWarActionPerformed
        selectWarFile();
    }//GEN-LAST:event_btSelectWarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btSelectDir;
    private javax.swing.JButton btSelectWar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHostname;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleInfo1;
    private javax.swing.JTextField txtOutputDir;
    private javax.swing.JTextPane txtReviewConfig;
    private javax.swing.JTextField txtWarFilename;
    private javax.swing.JTextField txtWarLocation;
    private javax.swing.JTextField txtWarVersion;
    // End of variables declaration//GEN-END:variables

    @Override
    public ValidationResult validatePage() {
        
        if (selectedOutputDir == null) {            
            return new ValidationResult(ValidationType.ERROR, "Output directory is not choosen yet. Please choose an Output directory", this.btSelectDir);
        } else if (this.selectedWarFile == null) {
            return new ValidationResult(ValidationType.ERROR, "No HDS-Explorer Server WAR Archive file was selected. Please download one from repository and select it", this.btSelectWar);
        }
        
        
        return new ValidationResult(ValidationType.SUCCESS, "", null);
    }

    @Override
    public Map<String, String> exportValues() {
        return null;
    }

    void setReviewText(String reviewString) {
        txtReviewConfig.setText(reviewString);
    }
    
    public void initWarRetrieval() {
        if (this.selectedWarFile == null) {
            retrieveInternalWar();
            retrieveInfoFromWarFile(this.selectedWarFile, WarFileLocation.INTERNAL);
        }
    }
    
    private void retrieveInternalWar() {
        //URL url = getClass().getResource("/wars");
        
        File warFile = null;

        //copy current war to temporary directory


        try {

            InputStream warInputStream = getClass().getResourceAsStream("/wars/hds-explorer-server.war");

            File newFile = new File(temporaryDirectory.getAbsolutePath() + "/hds-explorer-server.war");

            long result = Files.copy(warInputStream, newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            warFile = newFile;

        } catch (IOException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        this.selectedWarFile = warFile;
    }
    
    private void selectWarFile() {
        int result = this.warFileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            this.selectedWarFile = this.warFileChooser.getSelectedFile();
            txtWarFilename.setText(this.selectedWarFile.getName());
            
            retrieveInfoFromWarFile(this.selectedWarFile, WarFileLocation.EXTERNAL);
        }
    }
    
    private void retrieveInfoFromWarFile(File warFile, WarFileLocation warFileLocation) {
        WarValidationResult result = validateWarFile(warFile);
        if (warFile != null) {
            if (result.isValidWar == false) {
                this.txtWarLocation.setText(warFileLocation.name());
                this.txtWarFilename.setText("");
                this.txtWarVersion.setText("");                        
                
                messageBox("War File Validation", "The selected WAR file is not a valid HDS-Explorer Server war file");
            } else {

                this.txtWarLocation.setText(warFileLocation.name());
                this.txtWarFilename.setText(result.file.getName());
                this.txtWarVersion.setText(result.version);                       
            }
        }
    }
    
    private WarValidationResult validateWarFile(File warFile) {
        //grails.build.info
        //info.app.version=1.0.0 build 220
        //info.app.name=hds-explorer-server
                
        boolean isExplorerWar = false;
        String version = null;
        
        if (warFile != null) {

            //check if is explorer            
            try {
                JarFile jarFile = new JarFile(warFile);
                JarEntry entry = jarFile.getJarEntry("WEB-INF/classes/META-INF/grails.build.info");

                
                
                
                System.out.println("entry: "+entry+ " jar="+jarFile);            
                if (entry != null) {
                    Scanner scan = new Scanner(jarFile.getInputStream(entry));
                    System.out.println("reading file");
                    while (scan.hasNextLine()) {
                        String line = scan.nextLine().trim();
                        //System.out.println(""+line);
                        if (line.startsWith("info.app.version=")) {
                            version = line.replace("info.app.version=", "");
                        }

                        if (line.startsWith("info.app.name=hds-explorer-server")) {
                            isExplorerWar = true;
                        }
                    }
                } /*else {
                    Enumeration<JarEntry> entries = jarFile.entries();
                    
                    while (entries.hasMoreElements()) {
                        JarEntry ent = entries.nextElement();
                        if (ent.getName().contains("build")) {
                            System.out.println("entry: "+ent.getName()+", full: "+ent);
                        }
                    }
                }*/
            } catch (IOException ex) {
                Logger.getLogger(PanelReviewOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        return new WarValidationResult(warFile, isExplorerWar, version);
    }

    private void selectOutputDirectory() {
        int result = this.outputDirChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            this.selectedOutputDir = this.outputDirChooser.getSelectedFile();
            txtOutputDir.setText(this.selectedOutputDir.toString());
        }
        
    }
    
    public File getSelectedOutputDirectory() {
        return this.selectedOutputDir;
    }
    
    public File getSelectedWarFile() {
        return this.selectedWarFile;
    }
    
    private void messageBox(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    class WarValidationResult {
        public File file;
        public boolean isValidWar;
        public String version;

        public WarValidationResult(File file, boolean isValidWar, String version) {
            this.file = file;
            this.isValidWar = isValidWar;
            this.version = version;
        }        
    }
    
    enum WarFileLocation {
        INTERNAL, EXTERNAL
    }
    
}
