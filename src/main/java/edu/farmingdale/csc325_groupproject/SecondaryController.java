package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class SecondaryController {
    
    @FXML
    private void logout() throws IOException {
        App.setRoot("Authentication");
    }
    
    @FXML
    private void switchToCrimeDB() throws IOException{
       App.setRoot("CriminalUI");
    }
    
    @FXML
    private void switchToComplaintDB() throws IOException{
        App.setRoot("ComplaintUI");
    }
}