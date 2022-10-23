package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class NewCriminalController implements Initializable {
    
    @FXML
    private TextField codeTxt,dateTxt,descTxt,distTxt,loc1Txt,locTxt,nameTxt,neighTxt,postTxt,timeTxt,totTxt,weapTxt;
    
    @FXML
    private ImageView logoView;
    
    @FXML
    private ImageView logoViewHelp;
    
    private ArrayList<Criminal> comps = new ArrayList<Criminal>();
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
        System.out.println("pressed");
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please Input the data of your complaint where you can, \n it is okay to leave fields blank.");
        alert.show();
    }
    
    @FXML
    void InputData(ActionEvent event) {

        Criminal b = new Criminal();
        b.CrimeDate = dateTxt.getText();
        b.CrimeTime = timeTxt.getText();
        b.CrimeCode = codeTxt.getText();
        b.CrimeLoc = locTxt.getText();
        b.CrimeDesc = descTxt.getText();
        b.CrimeWeap = weapTxt.getText();
        b.CrimePost = Integer.parseInt(postTxt.getText());
        b.CrimeDist = distTxt.getText();
        b.Neighborhood = neighTxt.getText();
        b.CrimeLoc1 = loc1Txt.getText();
        b.CrimeTot = Integer.parseInt(totTxt.getText());
        b.CrimeName = nameTxt.getText();
        comps.add(b);
        
        String databaseURL = "";
        Connection conn = null;
        try {
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            
            String sql = "INSERT INTO Complaint (CrimeDate, CrimeTime, CrimeCode, Location, Description, Weapon, Post, District, Neighborhood, "
                    + "Location 1, Total Incidents, Full Name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, b.getCrimeDate());
            preparedStatement.setString(2, b.getCrimeTime());
            preparedStatement.setString(3, b.getCrimeCode());
            preparedStatement.setString(4, b.getCrimeLoc());
            preparedStatement.setString(5, b.getCrimeDesc());
            preparedStatement.setString(6, b.getCrimeWeap());
            preparedStatement.setInt(7, b.getCrimePost());
            preparedStatement.setString(8, b.getCrimeDist());
            preparedStatement.setString(9, b.getNeighborhood());
            preparedStatement.setString(10, b.getCrimeLoc1());
            preparedStatement.setInt(11, b.getCrimeTot());
            preparedStatement.setString(12, b.getCrimeName());
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Row inserted");
            }
    
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}

