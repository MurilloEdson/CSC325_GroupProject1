package edu.farmingdale.csc325_groupproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import java.io.*;
import javafx.fxml.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javafx.event.ActionEvent;

public class MainDisplayController implements Initializable {

    @FXML
    private ListView<String> criminalNames;
    @FXML
    private ChoiceBox<String> locations;
    @FXML
    private ToggleButton permissions;
    @FXML
    private Button addCrime,addCriminal;
    Boolean Admin;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        if(SignInController.currUser.getSecurityLvl()>1){
            permissions.selectedProperty().set(true);
            Admin = true;
        }else{
            permissions.selectedProperty().set(false);
            Admin = false;
        }
        
        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>(){}.getType());
             for(String curr : list){
                locations.getItems().add(curr);}
        } catch (FileNotFoundException ex) {
        }  
        locations.setOnAction(this::setListView);
        testAdminOrViewer();
    }

    public void setListView(ActionEvent event){
        
        ObservableList<String> criminals = (ObservableList<String>) criminalNames.getItems();
        criminals.clear();
        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future;
        future = App.fstore.collection("Criminals").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try 
        {
            documents = future.get().getDocuments();
            if(!documents.isEmpty())
            {
                for (QueryDocumentSnapshot document : documents) 
                {
                    String name = ""+document.getData().get("Name");
                    if(document.getData().get("Neighborhood").equals(locations.getValue())){
                        criminals.add(name);
                    }
                }
            }
            else
            {
               System.out.println("No data"); 
            }
        }
        catch (InterruptedException | ExecutionException ex ) 
        {
        }
    }
    public void testAdminOrViewer(){
        if(Admin){
            if(permissions.isSelected()){
                permissions.setText("ViewOnly");
                addCrime.setDisable(false);
                addCriminal.setDisable(false);
                System.out.println("Viewer permissions only");
            }else{
                permissions.setText("AdminView");
                addCrime.setDisable(true);
                addCriminal.setDisable(true);
                System.out.println("Admin permissions allowed");
            }
        }else{
            permissions.disableProperty();
            
        }
    }

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
    
}
