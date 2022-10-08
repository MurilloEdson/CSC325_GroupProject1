package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {
    @FXML
    private Button primaryButton;
    @FXML
    private void verifyCredentials(ActionEvent event) throws IOException {
        //TODO: Switch scenes between the Authentication screen and the Menu screen
        //TODO: Read the person credentials with the Admin class
        //TODo: Correlate with the person that has the database to search for credentials in database 
        
        
        String user;
        String password;
        user = userInput.getText();
        password = userPassword.getText();
        
     
        Stage stage;
        Parent root;
        
        root = FXMLLoader.load(getClass().getResource("Menu.fxml")); // retreives the information for the Menu Window
        stage = (Stage) loginButton.getScene().getWindow();//retrieves the current frame
        Scene scene = new Scene(root, 640, 480); //creates the new Scene 
        stage.setScene(scene); //sets the scene to the stage
        stage.show();//displays Menu Window
   
}
