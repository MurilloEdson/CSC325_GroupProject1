package edu.farmingdale.csc325_groupproject;

import Models.Complaint;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
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
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Date", date2.getValue());
        data.put("Description", txtArea.getText());
        data.put("Neighborhood", neighTxt.getValue());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        timeTxt.clear();
        txtArea.clear();
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
