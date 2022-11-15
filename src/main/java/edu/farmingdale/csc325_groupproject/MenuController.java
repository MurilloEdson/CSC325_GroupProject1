package edu.farmingdale.csc325_groupproject;


import Models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuController implements Initializable {
    
    @FXML
    private ImageView menuLogo;
    @FXML
    private MenuItem username;
    @FXML
    private ImageView profPic;
    
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
        Image img = new Image("/Aesthetics/OIP.jpg");
        menuLogo.setImage(img);
        profPic.setImage(SignInController.currUser.getProfilePic());
        username.setText(SignInController.currUser.getUsername());
    }
    


    
    
}