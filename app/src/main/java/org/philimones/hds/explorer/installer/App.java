/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.philimones.hds.explorer.installer;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class App {

    public static void main(String[] args) {
        
        
        setLookAndFeel();
        
        new MainPage().setVisible(true);
    }

    public static void setLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
       }
    }
}