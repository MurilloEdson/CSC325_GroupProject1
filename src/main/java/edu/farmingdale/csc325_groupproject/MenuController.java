package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MenuController implements Initializable {

    @FXML
    private ImageView menuLogo;
    @FXML
    private ImageView profilePicture;
    private Button newComplaint;
    private Button newCriminal;
    @FXML
    private MenuItem userName;
    FadeTransition fade = new FadeTransition();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label menuLabel;
    @FXML
    private Button newComplaintBtn;

    @FXML
    private void logout() throws IOException {
        fadeOut("SignIn");
    }

    @FXML
    private void switchToMainDisplay() throws IOException {
        fadeOut("MainDisplay");
    }

    @FXML
    private void switchToNewComplaint() throws IOException {
        fadeOut("NewComplaint");
    }

    @FXML
    private void switchToNewCriminal() throws IOException {
        fadeOut("NewCriminal");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Aesthetics/logo.png");
        //menuLogo.setImage(img);
        profilePicture.setImage(SignInController.UA.current.profilePic);
        userName.setText(SignInController.UA.current.getFirstName());

        if (!SignInController.UA.current.isAdmin()) {
            newComplaint.setDisable(true);
            newCriminal.setDisable(true);
        }
        fadeIn();
    }

    public void fadeIn() {
        rootPane.setOpacity(0);
        fade.setDelay(Duration.millis(500));
        fade.setNode(rootPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void fadeOut(String scene) {
        fade.setDuration(Duration.millis(500));
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
    public void settings(){
        SignInController.UA.setEdittingUser(SignInController.UA.current);
        fadeOut("SignUp");
    }
}
