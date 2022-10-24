package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NewComplaintController implements Initializable {

    private ArrayList<Complaint> comps = new ArrayList<Complaint>();
    
    @FXML
    private ImageView logoView;
    
    @FXML
    private ImageView logoViewHelp;
    
    @FXML
    private TextArea txtArea;
    
    @FXML
    private TextField neighTxt;

    @FXML
    private TextField timeTxt;
        
    @FXML
    private DatePicker date2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/pics/OIP.jpg");
        logoView.setImage(img);
        Image img1 = new Image("/pics/helpIMG.png");
        logoViewHelp.setImage(img1);
    } 
    
    @FXML
    void helpWindow(MouseEvent event) {
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please input whatever information you can remember about the event\nThe data will be sent for manual review.");
        alert.show();
    }
    
    @FXML
    void InputData(ActionEvent event) {
        String databaseURL = "";
        Connection conn = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            
            String sql = "INSERT INTO Complaint (CrimeDate, CrimeTime, Description, Neighborhood) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //preparedStatement.setDate(1, date2.getValue());
            preparedStatement.setString(2, timeTxt.getText());
            preparedStatement.setString(3, txtArea.getText());
            preparedStatement.setString(4, neighTxt.getText());
            
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Row inserted");
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        timeTxt.clear();
        txtArea.clear();
        neighTxt.clear();
        
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
