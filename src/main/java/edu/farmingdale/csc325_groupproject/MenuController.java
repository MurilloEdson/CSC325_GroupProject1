package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class MenuController {
    
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
}