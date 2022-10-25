
package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author quint
 */
public class SignUp {
    
    @FXML
    private TextField Email;

    @FXML
    private TextField UserInput;

    @FXML
    private TextField confirmPW;

    @FXML
    private Button createBtn;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField fName;
    
    @FXML
    private TextField lName;

    @FXML
    private TextField userPW;
    private int securityLvl = 1;
    User newUser;

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        String firstName = fName.getText();
        String lastName = lName.getText();
        String email = Email.getText();
        String username = UserInput.getText();
        String password = userPW.getText();
        String cfPassword = confirmPW.getText();
        
        if(firstName.equals("")){
            errorMessage.setText("first name field is empty");
            errorMessage.setVisible(true);
        }else if (lastName.equals("")){
            errorMessage.setText("last name field is empty");
            errorMessage.setVisible(true);
        }else if (email.equals("")){
            errorMessage.setText("email field is empty");
            errorMessage.setVisible(true);
        }else if (username.equals("")){
            errorMessage.setText("username field is empty");
            errorMessage.setVisible(true);
        }else if (password.equals("")){
            errorMessage.setText("password field is empty");
            errorMessage.setVisible(true);
        }else if (cfPassword.equals("")){
            errorMessage.setText("must confirm password field is empty");
            errorMessage.setVisible(true);
        }else if (!password.equals(cfPassword)){
            errorMessage.setText("Passwords do not match");
            errorMessage.setVisible(true);
        }else{
            errorMessage.setVisible(false);
            System.out.println("Creating Account Please wait....");
            newUser = new User(username,password,firstName,lastName,email,securityLvl);
        }
           
        
        Connection conn;
        String databaseURL;
        try{
            databaseURL = "jdbc:ucanaccess://.//Crime Management.accdb";
            conn = DriverManager.getConnection(databaseURL);
            String tableName = "Users";
            
            String sql = "INSERT INTO Users (username, password, firstName, lastName, email, securitylevel) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setString(3,newUser.getFirstName());
            ps.setString(4, newUser.getLastName());
            ps.setString(5, newUser.getEmail());
            ps.setInt(6, newUser.getSecurityLevel());
            
            int row = ps.executeUpdate();
            if(row > 0) {
                System.out.println("Account Created");
                System.out.println(newUser.getFirstName() + " " + newUser.getLastName()+ " " + newUser.getEmail()+ " " + newUser.getUsername()+ " " 
                        + newUser.getPassword()+ " " + newUser.getSecurityLevel());
            }
         
        } catch (SQLException ex) {
            System.err.println("An error has apppeared while creating account");
        }

    }

    @FXML
    void returnLoginIn(MouseEvent event) {
        try {
            App.setRoot("Authentication");
        } catch (IOException ex) {
            System.out.println("Can't load window");
        }

    }
    
}
