package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
    
   
}
