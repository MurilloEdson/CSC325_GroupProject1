package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author quint
 */
public class SignUpController implements Initializable {

    @FXML
    private TextField fName,lName,userPW,confirmPW,UserInput;
    @FXML
    private TextField Email;
    @FXML
    private Button createBtn;

    private int securityLvl = 1; 
    @FXML
    private Label errorMessage;
    User newUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
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
            
            String sql = "INSERT INTO Users (username, password, firstName, lastName, email, securityLevel) VALUES(?,?,?,?,?,?)";
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
            App.setRoot("SignIn");
        } catch (SQLException ex) {
            System.err.println("An error has apppeared while creating account");
        }
    }

    @FXML
    private void returnLoginIn(MouseEvent event) {
        try {
            App.setRoot("SignIn");
        } catch (IOException ex) {
            System.out.println("Window can't be loaded");
        }
    }
    
}
