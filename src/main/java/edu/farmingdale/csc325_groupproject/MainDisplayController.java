package edu.farmingdale.csc325_groupproject;

import com.google.gson.*;
import java.io.*;
import java.sql.*;
import javafx.fxml.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;

import com.google.gson.reflect.TypeToken;
import java.net.URL;
import javafx.event.ActionEvent;


public class MainDisplayController implements Initializable {

    @FXML
    private ListView<String> criminalNames;
    @FXML
    private ChoiceBox<String> locations;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>(){}.getType());
             for(String curr : list){
                locations.getItems().add(curr);}
        } catch (FileNotFoundException ex) {
        }  
        locations.setOnAction(this::setListView);
    }

    public void setListView(ActionEvent event){
        ObservableList<String> criminals = (ObservableList<String>) criminalNames.getItems();
        String databaseURL;
        Connection conn;
        String place = locations.getValue();
        try {
            criminals.clear();
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            String tableName = "Criminal";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from " + tableName+" where Neighborhood = '" + place+"'");
            while (result.next()) {
                String name = result.getString("Full Name"); 
                criminals.add(name);
        } }
            catch (SQLException e) {
        }
    }
    /*    
    public void inputDataToJSon() throws FileNotFoundException{
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        FileReader fr = new FileReader("Locations.json");
        String jsonString = gson.toJson(place);
        PrintStream ps = new PrintStream("Locations.json");
        ps.println(jsonString);
    }*/

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
