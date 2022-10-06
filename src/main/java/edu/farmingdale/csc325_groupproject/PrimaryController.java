package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController implements Initializable{
    
    @FXML
    private Button loginButton;

    @FXML
    private ImageView logoView;

    @FXML
    private TextField userInput;

    @FXML
    private TextField userPassword;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/pics/OIP.jpg");
        logoView.setImage(img);
    }

    @FXML
    private void verifyCredentials(ActionEvent event) throws IOException {
        //TODO: Switch scenes between the Authentication screen and the Menu screen
        //TODO: Read the person credentials with the Admin class
        //TODo: Correlate with the person that has the database to search for credentials in database 
        
     
        Stage stage;
        Parent root;
        
        root = FXMLLoader.load(getClass().getResource("Menu.fxml")); // retreives the information for the Menu Window
        stage = (Stage) loginButton.getScene().getWindow();//retrieves the current frame
        Scene scene = new Scene(root, 640, 480); //creates the new Scene 
        stage.setScene(scene); //sets the scene to the stage
        stage.show();//displays Menu Window
   
        
    }
    
    
   
}
