package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

public class NewComplaintController implements Initializable {

    private ArrayList<Complaint> comps = new ArrayList<Complaint>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    @FXML
    void helpWindow(MouseEvent event) {
        /*System.out.println("pressed");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please Input the data of your complaint where you can, \n it is okay to leave fields blank.");
        alert.show();*/
    }
    
    @FXML
    void InputData(ActionEvent event) {
        
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
