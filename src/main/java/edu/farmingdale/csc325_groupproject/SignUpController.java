package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
            App.setRoot("SignIn");
        } catch (IOException ex) {
            System.out.println("Window can't be loaded");
        }
    }
    
}
