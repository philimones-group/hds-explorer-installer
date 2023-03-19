/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paul
 */
public class PanelGoodbye extends javax.swing.JPanel implements IPage {

    /**
     * Creates new form PanelWelcome
     */
    public PanelGoodbye() {
        initComponents();
    }

    @Override
    public void setTemporaryDirectory(File temporaryDirectory) {

    }

    private void openUrl(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException ex) {
                Logger.getLogger(PanelGoodbye.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        label1setup = new javax.swing.JLabel();
        label2welcome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblWarFile = new javax.swing.JLabel();
        lblWarFileDesc = new javax.swing.JLabel();
        lblSql = new javax.swing.JLabel();
        lblSqlDesc = new javax.swing.JLabel();
        lblConf = new javax.swing.JLabel();
        lblConfDesc = new javax.swing.JLabel();
        lblReadme = new javax.swing.JLabel();
        lblReadmeDesc = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(701, 473));
        setPreferredSize(new java.awt.Dimension(701, 473));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/nio splash.jpg"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(312, 469));
        jLabel1.setMinimumSize(new java.awt.Dimension(312, 469));
        jLabel1.setPreferredSize(new java.awt.Dimension(312, 469));

        label1setup.setFont(new java.awt.Font("Gordita", 1, 14)); // NOI18N
        label1setup.setText("Finalizing HDS-Explorer Setup wizard");

        label2welcome.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        label2welcome.setText("The setup configuration is finished!");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 11)); // NOI18N
        jLabel2.setText("The configuration generated 4 files that can be found in the output directory");

        jPanel1.setLayout(new java.awt.GridLayout(4, 2, -150, 0));

        lblWarFile.setForeground(java.awt.Color.blue);
        lblWarFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWarFile.setText("<html><u>hds-explorer-server.war</u></html>");
        lblWarFile.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblWarFile);

        lblWarFileDesc.setFont(new java.awt.Font("Noto Sans", 0, 11)); // NOI18N
        lblWarFileDesc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblWarFileDesc.setText("<html>A java WAR file to be deployed in containers like Tomcat</html>");
        jPanel1.add(lblWarFileDesc);

        lblSql.setForeground(java.awt.Color.blue);
        lblSql.setText("<html><u>create-user-mysql.sql</u></html>");
        lblSql.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblSql);

        lblSqlDesc.setFont(new java.awt.Font("Noto Sans", 0, 11)); // NOI18N
        lblSqlDesc.setText("An SQL file with database and user creation statements");
        jPanel1.add(lblSqlDesc);

        lblConf.setForeground(java.awt.Color.blue);
        lblConf.setText("<html><u>app-config.yml</u></html>");
        lblConf.setToolTipText("");
        lblConf.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblConf);

        lblConfDesc.setFont(new java.awt.Font("Noto Sans", 0, 11)); // NOI18N
        lblConfDesc.setText("The app config. file that can be reused in other setups");
        jPanel1.add(lblConfDesc);

        lblReadme.setForeground(java.awt.Color.blue);
        lblReadme.setText("<html><u>README.html</u></html>");
        lblReadme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(lblReadme);

        lblReadmeDesc.setText("Web page file containing deployment instructions ");
        jPanel1.add(lblReadmeDesc);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label1setup)
                    .addComponent(label2welcome)
                    .addComponent(jLabel2)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(label1setup)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2welcome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(139, 139, 139))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel label1setup;
    private javax.swing.JLabel label2welcome;
    private javax.swing.JLabel lblConf;
    private javax.swing.JLabel lblConfDesc;
    private javax.swing.JLabel lblReadme;
    private javax.swing.JLabel lblReadmeDesc;
    private javax.swing.JLabel lblSql;
    private javax.swing.JLabel lblSqlDesc;
    private javax.swing.JLabel lblWarFile;
    private javax.swing.JLabel lblWarFileDesc;
    // End of variables declaration//GEN-END:variables

    @Override
    public ValidationResult validatePage() {
        return new ValidationResult(ValidationType.SUCCESS, null, null);
    }

    @Override
    public Map<String, String> exportValues() {
        return null;
    }
}
