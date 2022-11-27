package edu.farmingdale.csc325_groupproject;

import Models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SignUpController implements Initializable {

    @FXML
    private TextField fName, lName, userPW, confirmPW, UserInput, Email;
    @FXML
    private Button createBtn;
    @FXML
    private Label errorMessage,returnToLoginLabel,createAccountLabel;
    @FXML
    private AnchorPane rootPane;
    static String newUsername;
    static String newPassword;
    FadeTransition fade = new FadeTransition();
   

    @FXML
    void createAccount(ActionEvent event) throws IOException {
        //SignInController signIN = new SignInController();
        String firstName = fName.getText();
        String lastName = lName.getText();
        String email = Email.getText();
        String username = UserInput.getText();
        String password = userPW.getText();
        String cfPassword = confirmPW.getText();

        if (verifyAllTextfields() && cfPassword.equals(password)) {
            DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("username", username);
            data.put("password", password);
            data.put("firstName", firstName);
            data.put("lastName", lastName);
            data.put("email", email);
            data.put("securityLevel", 1);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
            clearText();
            newUsername = (String) data.get("username");
            newPassword = (String) data.get("password");
            fadeOut();

        }
    }

    public boolean verifyAllTextfields() {
        boolean clear = false;
        if (!fName.getText().isBlank()) {
            if (!lName.getText().isBlank()) {
                if (!Email.getText().isBlank()) {
                    if (!UserInput.getText().isBlank()) {
                        if (!userPW.getText().isBlank()) {
                            clear = true;
                        }
                    }
                }
            }
        }
        return clear;
    }

    public void clearText() {
        fName.clear();
        lName.clear();
        Email.clear();
        UserInput.clear();
        userPW.clear();
        confirmPW.clear();
    }

    @FXML
    private void returnLoginIn(MouseEvent event) {
        //FadeTransition fade = new FadeTransition();
        fadeOut();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //FadeTransition fade = new FadeTransition();
        fadeIn();
    }
    
    public void fadeIn(){
        rootPane.setOpacity(0);
        fade.setDelay(Duration.millis(1000));
        fade.setNode(rootPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();   
    }
    
    public void fadeOut(){
        fade.setDuration(Duration.millis(1000));
        fade.setNode(rootPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((t) -> {

            try {
                App.setRoot("SignIn");
            } catch (IOException ex) {
                System.out.println("Window can't be loaded");
            }

        });
        fade.play();
        
    }
}
