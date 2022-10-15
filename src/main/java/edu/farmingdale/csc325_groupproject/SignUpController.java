/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author quint
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField fName;
    @FXML
    private TextField lName;
    @FXML
    private TextField Email;
    @FXML
    private Button createBtn;
    @FXML
    private TextField userPW;
    @FXML
    private TextField confirmPW;
    @FXML
    private TextField UserInput;
    
    private int securityLvl = 1; 
    @FXML
    private Label errorMessage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createAccount(ActionEvent event) {
 
        
        
        
    }

    @FXML
    private void returnLoginIn(MouseEvent event) {
        try {
            App.setRoot("Authentication");
        } catch (IOException ex) {
            System.out.println("Window can't be loaded");
        }
    }
    
}
