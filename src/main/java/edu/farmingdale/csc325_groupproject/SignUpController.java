package edu.farmingdale.csc325_groupproject;

import Models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class SignUpController implements Initializable {

    @FXML
    private TextField fName, lName, userPW, confirmPW, UserInput, Email;
    @FXML
    private Button createBtn,update;
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
        SignInController.UA.setEditting(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //FadeTransition fade = new FadeTransition();
        clearText();
        fadeIn();
        if(SignInController.UA.isEditting()){
            setEditText(SignInController.UA.userUpdate);
            update.setVisible(true);
            createBtn.setDisable(true);
        }
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
                update.setVisible(false);
                createBtn.setDisable(false);
            } catch (IOException ex) {
                System.out.println("Window can't be loaded");
            }

        });
        fade.play();
    }
    public void update(){
        String docID = "";
        try {
            ApiFuture<QuerySnapshot> future = App.fstore.collection("Users").get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
            }
            // Update an existing document
            DocumentReference docRef = App.fstore.collection("Users").document(docID);
            // (async) Update one field
            ApiFuture<WriteResult> futureUpdate = null;// = docRef.update();
            // ...
            WriteResult result = futureUpdate.get();
            //System.out.println("Write result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setEditText(User userUpdate) {
        fName.setText(userUpdate.getFirstName());
        lName.setText(userUpdate.getLastName());
        userPW.setText(userUpdate.getPassword());

        UserInput.setText(userUpdate.getUsername());
        Email.setText(userUpdate.getEmail());
    }
}
