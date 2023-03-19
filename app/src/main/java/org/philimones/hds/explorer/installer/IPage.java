/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.philimones.hds.explorer.installer;

import java.awt.Component;
import java.io.File;
import java.util.Map;

/**
 *
 * @author paul
 */
public interface IPage {
    
    ValidationResult validatePage();
    
    Map<String, String> exportValues();

    void setTemporaryDirectory(File temporaryDirectory);
    
    enum ValidationType {
        ERROR, SUCCESS
    }
    
    class ValidationResult {
        public ValidationType type;
        public String errorMessage;
        public Component component;

        public ValidationResult(ValidationType type, String errorMessage, Component component) {
            this.type = type;
            this.errorMessage = errorMessage;
            this.component = component;
        }
        
        
    }
}
