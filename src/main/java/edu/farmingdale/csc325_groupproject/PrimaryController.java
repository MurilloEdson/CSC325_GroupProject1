package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;

public class PrimaryController implements Initializable{
    @FXML
    private Button primaryButton;
    @FXML
    private ImageView logoView;
    
    @FXML
    private TextField userInput;

    @FXML
    private TextField userPassword;
    
    @FXML
    private void verifyCredentials(ActionEvent event) throws IOException {


        String username = userInput.getText();
        String password = userPassword.getText();
        
        Connection conn;
        String databaseURL;
         try {
            
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            String tableName = "Users";
            Statement stmt = conn.createStatement();
            ResultSet result =  stmt.executeQuery("select * from " + tableName);
            
            while(result.next()){
                String user = result.getString("username");
                String pw = result.getString("password");
                if (user.equals(username) && pw.equals(password)){
                    App.setRoot("Menu");   
                }else {
                    System.out.println("invalid user and password");
                }    
                
            
                //System.out.printf("%s %s", user, pw);  
            }
         }
         
         catch(SQLException e){
             
         }    
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/pics/OIP.jpg");
        logoView.setImage(img);           
    }
    
}
