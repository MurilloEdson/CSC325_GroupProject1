package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class SignInController implements Initializable{
    @FXML
    private Button primaryButton;
    @FXML
    private ImageView logoView;
    @FXML
    private TextField userInput;
    @FXML
    private TextField userPassword;
    
    @FXML
    private void verifyCredentials() throws IOException {
        //TODO: Switch scenes between the Authentication screen and the Menu screen
        //TODO: Read the person credentials with the Admin class
        //TODO: Correlate with the person that has the database to search for credentials in database 
        App.setRoot("Menu");   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/pics/OIP.jpg");
        logoView.setImage(img);
    }
     @FXML
    private void toCreateWinodw(MouseEvent event) {
        try {
            App.setRoot("SignUp");
        } catch (IOException ex) {
            System.out.println("Can't load window");
            
        }
    }
}
