package edu.farmingdale.csc325_groupproject;

import Models.Criminal;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class NewCriminalController implements Initializable {
    
    @FXML
    private TextField codeTxt,dateTxt,descTxt,distTxt,loc1Txt,locTxt,nameTxt,postTxt,timeTxt,totTxt,weapTxt;
    @FXML
    private ImageView logoView,logoViewHelp;
    @FXML
    private ChoiceBox<String> neighTxt;
    
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
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>(){}.getType());
             for(String curr : list){
                neighTxt.getItems().add(curr);}
        } catch (FileNotFoundException ex) {
        }
    } 
    
    @FXML
    void helpWindow(MouseEvent event) {
        System.out.println("pressed");
                
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please Input the data of the criminal here \nThe Data will then be inserted into the crime DB");
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
        b.Neighborhood = neighTxt.getValue();
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

