/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/Application.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.philimones.hds.explorer.installer.IPage.ValidationResult;

/**
 *
 * @author paul
 */
public class MainPage extends javax.swing.JFrame implements InstallListener {

    private enum Page { WELCOME, DATABASE, PARAMETERS, REVIEW, INSTALL, GOODBYE }
    
    
    private Page currentPage = Page.WELCOME;
    private PanelWelcome pageWelcome;
    private PanelDatabase pageDatabase;
    private PanelParameters pageParameters;
    private PanelReviewOutput pageReview;
    private PanelInstall pageInstall;
    private PanelGoodbye pageGoodbye;
    
    private String reviewString;

    private File temporaryDirectory;

    /**
     * Creates new form MainPage
     */
    public MainPage() {
        initComponents();
        setLocationRelativeTo(null);

        this.temporaryDirectory = createTemporaryDir();

        initPages();
        gotoWelcomePage();
    }
    
    private void initPages(){
        this.pageWelcome = new PanelWelcome();
        this.pageDatabase = new PanelDatabase();
        this.pageParameters = new PanelParameters();
        this.pageReview = new PanelReviewOutput();
        this.pageInstall = new PanelInstall();
        this.pageGoodbye = new PanelGoodbye();
        
        this.pageInstall.setInstallListener(this);

        this.pageWelcome.setTemporaryDirectory(this.temporaryDirectory);
        this.pageDatabase.setTemporaryDirectory(this.temporaryDirectory);
        this.pageParameters.setTemporaryDirectory(this.temporaryDirectory);
        this.pageReview.setTemporaryDirectory(this.temporaryDirectory);
        this.pageInstall.setTemporaryDirectory(this.temporaryDirectory);
        this.pageGoodbye.setTemporaryDirectory(this.temporaryDirectory);
    }

    private File createTemporaryDir() {
        try {
            //setConfiguringText("Creating Temporary Directory");
            Path path = Files.createTempDirectory("hdsInstaller");
            return path.toFile();

        } catch (IOException ex) {
            Logger.getLogger(PanelInstall.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    private void createReviewInfo() {
       
        Map<String, String> allMap = new LinkedHashMap();

        allMap.putAll(pageDatabase.exportValues());
        allMap.putAll(pageParameters.exportValues());
            
        String lines = getAppConfigLines();
        StringBuilder builder = new StringBuilder();
            
        for (String line : lines.split("\n")) {            
            for (String key : allMap.keySet()) {
                
                if (line.startsWith(key + ":")) {
                    line = key + ": " + allMap.get(key);
                }               
                
            }
            
            builder.append(line + "\n");            
        }
        
        this.reviewString = builder.toString();
        
        this.pageReview.setReviewText(this.reviewString);
        this.pageReview.initWarRetrieval();
    }
    
    private void setInstallData() {
        
        Map<String, String> allMap = new LinkedHashMap();

        allMap.putAll(pageDatabase.exportValues());
        allMap.putAll(pageParameters.exportValues());
                
        this.pageInstall.setInstallData(this.pageReview.getSelectedOutputDirectory(), this.pageReview.getSelectedWarFile(), allMap, this.reviewString);
    }
    
    private String getAppConfigLines() {
        String txt = "";
        
        try {
            InputStream fileStream = getClass().getResourceAsStream("/samples/app-config.yml");
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream));
            
            
            String line = null;
            do {
                
                line = reader.readLine();
                
                if (line != null) {
                    txt += line + "\n";
                }

            } while (line != null);
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return txt;
    }
    
    private void gotoWelcomePage(){
        System.out.println("goto welcome");
        
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageWelcome);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(false);
        this.btNext.setEnabled(true);
        this.btCancel.setEnabled(true);
        
        currentPage = Page.WELCOME;
    }
    
    private void gotoGoodbyePage(){
        System.out.println("goto sayonara");
        
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageGoodbye);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(false);
        this.btNext.setEnabled(true);        
        this.btCancel.setEnabled(false);
        
        this.btNext.setText("Finish");
        
        currentPage = Page.WELCOME;
    }
    
    private void gotoDatabasePage(){
        System.out.println("go to database");
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageDatabase);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(true);
        this.btNext.setEnabled(true);
        this.btCancel.setEnabled(true);
        
        currentPage = Page.DATABASE;
    }
    
    private void gotoParametersPage(){
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageParameters);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(true);
        this.btNext.setEnabled(true);
        this.btCancel.setEnabled(true);
        
        this.pageParameters.setLoadedConfigurationMap(this.pageDatabase.getLoadedConfigurationFileMap());
        
        currentPage = Page.PARAMETERS;
    }
    
    private void gotoReviewPage(){
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageReview);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(true);
        this.btNext.setEnabled(true);
        this.btCancel.setEnabled(true);
        
        currentPage = Page.REVIEW;
        
        createReviewInfo();
    }
    
    private void gotoInstallPage(){
        this.mainPanel.removeAll();
        this.mainPanel.setLayout(new GridLayout(1,1));
        this.mainPanel.add(this.pageInstall);
        this.mainPanel.revalidate();
        this.mainPanel.repaint();
        this.pack();
        
        this.btBack.setEnabled(false);
        this.btNext.setEnabled(false);
        this.btCancel.setEnabled(true);
        
        currentPage = Page.INSTALL;
        
        setInstallData();        
    }
    
    private void gotoPage(Page page) {
        switch (page) {
            case WELCOME: gotoWelcomePage(); break;
            case DATABASE: gotoDatabasePage(); break;
            case PARAMETERS: gotoParametersPage(); break;
            case REVIEW: gotoReviewPage(); break;
            case INSTALL: gotoInstallPage(); break;
            case GOODBYE: gotoGoodbyePage(); break;
        }
    }
    
    private IPage getIPage(Page page) {
        switch (page) {
            case WELCOME: return this.pageWelcome;
            case DATABASE: return this.pageDatabase;
            case PARAMETERS: return this.pageParameters;
            case REVIEW: return this.pageReview;
            case INSTALL: return this.pageInstall;
            case GOODBYE: return this.pageGoodbye;            
            default: return null;
        }
    }
    
    private void onBackClicked() {
        
        Page[] pages = Page.values();
        Page nextPage = currentPage;
                
        for (int i=0; i < pages.length; i++) {
            if (pages[i] == this.currentPage) {
                if (i==0) {
                    nextPage = Page.WELCOME;
                } else {
                    nextPage = pages[i-1];
                }                
                
                break;
            }
        }
        
        System.out.println("back to "+nextPage);
        
        if (nextPage != currentPage) {
            gotoPage(nextPage);
        }
    }
    
    private void onNextClicked() {
        
        if (btNext.getText().equals("Finish")) {
            dispose();
            System.exit(0);
            return;
        }
        
        Page[] pages = Page.values();
        Page nextPage = currentPage;
        
        //validate current page
        IPage currentPanelPage = getIPage(currentPage);        
        
        ValidationResult result = currentPanelPage.validatePage();
        
        if (result.type == IPage.ValidationType.ERROR) {
                        
            messageBox("Error", result.errorMessage);
            
            if (result.component != null) {
                result.component.requestFocusInWindow();
            }
            return;
        }
        
        for (int i=0; i < pages.length; i++) {
            if (pages[i] == this.currentPage) {
                if (i==pages.length-1) {
                    nextPage = Page.GOODBYE;
                } else {
                    nextPage = pages[i+1];
                }                
                break;
            }
        }
        
        System.out.println("Next page = "+nextPage+", current="+currentPage);
        
        if (nextPage != currentPage) {
            gotoPage(nextPage);
        }
    }
    
    
    private void cancel() {
        int result = JOptionPane.showConfirmDialog(this, "Do you want to cancel this setup configuration process?", "Cancelling Setup", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        if (result == JOptionPane.YES_OPTION) {            
            dispose();
            System.exit(0);
        }
    }
    
    @Override
    public void finishedInstallation() {
        gotoGoodbyePage();
    }
    
    private void messageBox(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        btBack = new javax.swing.JButton();
        btNext = new javax.swing.JButton();
        btCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Setup HDS-Explorer Server");
        setMaximumSize(new java.awt.Dimension(684, 519));
        setMinimumSize(new java.awt.Dimension(684, 519));
        setResizable(false);
        setSize(new java.awt.Dimension(684, 519));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        mainPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        mainPanel.setMinimumSize(new java.awt.Dimension(701, 500));
        mainPanel.setPreferredSize(new java.awt.Dimension(701, 500));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        bottomPanel.setFont(new java.awt.Font("Gordita", 0, 13)); // NOI18N
        bottomPanel.setMaximumSize(null);
        bottomPanel.setRequestFocusEnabled(false);

        btBack.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        btBack.setText("Back");
        btBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBackActionPerformed(evt);
            }
        });

        btNext.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        btNext.setText("Next");
        btNext.setToolTipText("");
        btNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNextActionPerformed(evt);
            }
        });

        btCancel.setFont(new java.awt.Font("sansserif", 0, 12)); // NOI18N
        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bottomPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bottomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btBack, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNext, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(bottomPanel, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBackActionPerformed
        onBackClicked();
    }//GEN-LAST:event_btBackActionPerformed

    private void btNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNextActionPerformed
        onNextClicked();
    }//GEN-LAST:event_btNextActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));   
    }//GEN-LAST:event_btCancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        cancel();
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton btBack;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btNext;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables

    
    
}
