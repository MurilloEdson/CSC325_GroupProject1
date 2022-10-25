package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class SignInController implements Initializable{
    @FXML
    private Button primaryButton;
    @FXML
    private ImageView logoView;
    @FXML
    private TextField userInput,userPassword;
    
    @FXML
    private void verifyCredentials() throws IOException {
        //TODO: Switch scenes between the Authentication screen and the Menu screen
        //TODO: Read the person credentials with the Admin class
        //TODO: Correlate with the person that has the database to search for credentials in database
        
        String username = userInput.getText();
        String password = userPassword.getText();
        boolean signedIn = false;
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
                    System.out.println("Hello "+ user+", Welcome");
                    signedIn = true;
                    result.afterLast();
                }else {
                    App.setRoot("Menu");
                }    
                //System.out.printf("%s %s", user, pw);  
            }
            if(signedIn == false){
                System.out.println("invalid user and password");
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
    @FXML
    private void toCreateWinodw(MouseEvent event) {
        try {
            App.setRoot("SignUp");
        } catch (IOException ex) {
            System.out.println("Can't load window");
            
        }
    }
}
