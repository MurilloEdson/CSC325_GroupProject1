package edu.farmingdale.csc325_groupproject;

import Models.Complaint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    private ChoiceBox<String> neighTxt;
    @FXML
    private TextField timeTxt;
    @FXML
    private DatePicker date2;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Aesthetics/OIP.jpg");
        logoView.setImage(img);
        Image img1 = new Image("/Aesthetics/helpIMG.png");
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
            preparedStatement.setString(4, neighTxt.getValue());
            
            int row = preparedStatement.executeUpdate();
            if (row > 0) {
                System.out.println("Row inserted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        timeTxt.clear();
        txtArea.clear();
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
