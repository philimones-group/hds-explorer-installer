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
    
    public static Integer getInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isIntegerGreaterThan(String textValue, int value) {
        try {
            Integer result = Integer.valueOf(textValue);                        
            return result > value;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean isIntegerGreaterThanAndLessThan(String textValue, int minValue, int maxValue) {
                
        Integer result = getInteger(textValue);
        if (result != null && result >= minValue && result <= maxValue) {
            return true;
        }
        
        return false;
            
    }
    
    public static boolean equals(char[] c1, char[] c2) {
        return new String(c1).equalsIgnoreCase(new String(c2));
    }

    static int[] splitToNumbers(String antschd) {
        String[] strs = antschd.split(",");
        int[] numbers = new int[strs.length];
        
        for (int i=0; i < strs.length; i++) {
            Integer n = getInteger(strs[i]);
            if (n != null) {
                numbers[i] = n;
            }
        }        
        
        return numbers;
    }
}
