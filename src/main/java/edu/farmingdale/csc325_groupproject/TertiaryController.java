package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import javafx.fxml.*;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class TertiaryController implements Initializable {

    @FXML
    private ListView<String> criminalNames;
    @FXML
    private ChoiceBox<String> locations;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ObservableList<String> criminals = (ObservableList<String>) criminalNames.getItems();
        String databaseURL;
        Connection conn;
        try {
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            String tableName = "Criminal";
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select distinct Neighborhood from " + tableName);
            while (result.next()) {
                String location = result.getString("Neighborhood"); 
                locations.getItems().add(location);
            }
        }catch (SQLException e) {
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

    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
