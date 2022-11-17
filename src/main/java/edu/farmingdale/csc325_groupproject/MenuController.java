package edu.farmingdale.csc325_groupproject;

import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController implements Initializable {
    
    @FXML
    private ImageView menuLogo;
    @FXML
    private Label welcomeLabel;
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("SignIn");
    }
    @FXML
    private void switchToMainDisplay() throws IOException{
       App.setRoot("MainDisplay");
    }
    @FXML
    private void switchToNewComplaint() throws IOException{
        App.setRoot("NewComplaint");
    }
    @FXML
    private void switchToNewCriminal() throws IOException{
        App.setRoot("NewCriminal");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Aesthetics/logo.png");
        menuLogo.setImage(img);
        welcomeLabel.setText("Welcome, " + SignInController.currUser.getFirstName());
        
    }
}