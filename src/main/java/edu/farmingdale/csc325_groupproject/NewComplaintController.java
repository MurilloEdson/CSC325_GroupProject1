package edu.farmingdale.csc325_groupproject;

import Models.Complaint;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.animation.FadeTransition;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class NewComplaintController implements Initializable {

    private ArrayList<Complaint> comps = new ArrayList<Complaint>();

    @FXML
    private ImageView logoView;
    @FXML
    private ImageView logoViewHelp;
    @FXML
    private TextArea txtArea;
    @FXML
    private ChoiceBox<String> neighTxt;
    @FXML
    private TextField timeTxt;
    @FXML
    private DatePicker date2;
    @FXML
    private ImageView profilePicture;
    @FXML
    private MenuItem userName;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label complaintTitleLabel;
    
    FadeTransition fade = new FadeTransition();
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profilePicture.setImage(SignInController.currUser.profilePic);
        userName.setText(SignInController.currUser.getFirstName());
        Image img = new Image("/Aesthetics/logo.png");
        logoView.setImage(img);
        Image img1 = new Image("/Aesthetics/helpIMG.png");
        logoViewHelp.setImage(img1);
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>() {
            }.getType());
            for (String curr : list) {
                neighTxt.getItems().add(curr);
            }
        } catch (FileNotFoundException ex) {
        }
        
        fadeIn();
    }

    @FXML
    void helpWindow(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please input whatever information you can remember about the event\nThe data will be sent for manual review.");
        alert.show();
    }

    @FXML
    void InputData(ActionEvent event) {
        DocumentReference docRef = App.fstore.collection("Users").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("Date", date2.getValue());
        data.put("Description", txtArea.getText());
        data.put("Neighborhood", neighTxt.getValue());
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
        timeTxt.clear();
        txtArea.clear();
    }

    @FXML
    private void switchToMenu() throws IOException {
        String fxml = MenuController.st.pop();
        fadeOut(fxml);

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
        fade.setDuration(Duration.millis(180));
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
