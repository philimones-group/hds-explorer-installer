/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.philimones.hds.explorer.installer;

/**
 *
 * @author paul
 */
public class StringUtil {
    public static boolean isBlank(String value) {
        return (value == null || value.isEmpty());
    }
    
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean equals(char[] c1, char[] c2) {
        return new String(c1).equalsIgnoreCase(new String(c2));
    }
}
