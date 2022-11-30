package edu.farmingdale.csc325_groupproject;

import Models.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class SignInController implements Initializable {

    static User currUser = new User();
    @FXML
    private ImageView logoView;
    @FXML
    private TextField userInput;
    @FXML
    private PasswordField userPassword;

    FadeTransition fade = new FadeTransition();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button loginButton;
    @FXML
    private Label signUpLabel;

    @FXML
    private void verifyCredentials() throws IOException {
        //TODO: Read the person credentials with the Admin class
        String username = userInput.getText();
        String password = userPassword.getText();
        boolean signedIn = false;
        ApiFuture<QuerySnapshot> future = App.fstore.collection("Users").get();
        List<QueryDocumentSnapshot> documents;
        try {
            documents = future.get().getDocuments();
            if (!documents.isEmpty()) {
                for (QueryDocumentSnapshot document : documents) {
                    String docUser = document.getData().get("username").toString();
                    String docPass = document.getData().get("password").toString();
                    if (username.equals(docUser) && password.equals(docPass)) {
                        //currUser
                        currUser = currUser.DBtoObject(docUser, docPass, document);
                        signedIn = true;
                        fadeOut("Menu");
                        System.out.println("Hello " + currUser.getFirstName() + ", Welcome");
                        break;
                    }
                }
            } else {
                System.out.println("No data");
            }
            if (signedIn == false) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WRONG INFO");
                alert.setContentText("You have entered an invalid username and/or password");
                alert.show();
            }
        } catch (InterruptedException | ExecutionException ex) {
        }
        //SignUpController.newUsername = null;
        //SignUpController.newPassword = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Aesthetics/logo.png");
        logoView.setImage(img);
        fadeIn();

    }

    @FXML
    private void toCreateWinodw(MouseEvent event) {
        fadeOut("SignUp");

    }

    public void fadeIn() {
        rootPane.setOpacity(0);
        fade.setDelay(Duration.millis(200));
        fade.setNode(rootPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void fadeOut(String scene) {
        fade.setDuration(Duration.millis(100));
        fade.setNode(rootPane);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.setOnFinished((t) -> {
            try {
                App.setRoot(scene);

            } catch (IOException ex) {
                System.out.println("Can't load window");
            }

        });
        fade.play();

    }

}
