package edu.farmingdale.csc325_groupproject;

import Models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.WriteResult;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;
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
    private TextField fName,lName,userPW,confirmPW,UserInput,Email;
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
        
        if(verifyAllTextfields() && cfPassword.equals(password)){
            DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("username", UserInput.getText());
            data.put("password", userPW.getText());
            data.put("firstName", fName.getText());
            data.put("lastName", lName.getText());
            data.put("email", Email.getText());
            data.put("securityLevel", 1);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
            clearText();
            App.setRoot("SignIn");
        }
    }
    
    public boolean verifyAllTextfields(){
        boolean clear = false;
        if(!fName.getText().isBlank()){
            if(!lName.getText().isBlank()){
                if(!Email.getText().isBlank()){
                    if(!UserInput.getText().isBlank()){
                        if(!userPW.getText().isBlank()){
                            clear = true;
                        }
                    }
                }
            }
        }
        return clear;
    }
    public void clearText(){
        fName.clear();
        lName.clear();
        Email.clear();
        UserInput.clear();
        userPW.clear();
        confirmPW.clear();
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
