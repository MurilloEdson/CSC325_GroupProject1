package edu.farmingdale.csc325_groupproject;

import Models.Criminal;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class NewCriminalController implements Initializable {
    
    @FXML
    private TextField dateTxt,timeTxt,nameTxt,addyTxt,descTxt,postTxt;
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
        
        b.CrimeDate = dateTxt.getText()+ timeTxt.getText();
        b.Neighborhood = neighTxt.getValue();
        b.Post = Integer.parseInt(postTxt.getText());
        b.Name = nameTxt.getText();
        b.Address = addyTxt.getText();
        b.Description = descTxt.getText();
        
        comps.add(b);
        
        DocumentReference docRef = App.fstore.collection("Criminals").document(UUID.randomUUID().toString());
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("crimeDate", b.CrimeDate);
            data.put("Address", b.Address);
            data.put("Name", b.Name);
            data.put("Neighborhood", b.Neighborhood);
            data.put("Description", b.Description);
            data.put("Post", b.Post);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
        
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}

