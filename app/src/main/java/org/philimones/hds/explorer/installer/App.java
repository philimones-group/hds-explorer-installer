/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package org.philimones.hds.explorer.installer;

import java.awt.Toolkit;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class App {

    public static void main(String[] args) {
        
        
        setLookAndFeel();
        
        new MainPage().setVisible(true);
    }

    public static void setLookAndFeel() {
        Toolkit.getDefaultToolkit().setDynamicLayout(true);
        System.setProperty("sun.awt.noerasebackground", "true");
        JFrame.setDefaultLookAndFeelDecorated(true);
        JDialog.setDefaultLookAndFeelDecorated(true);

        try {
            UIManager.setLookAndFeel("net.sf.tinylaf.TinyLookAndFeel");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
