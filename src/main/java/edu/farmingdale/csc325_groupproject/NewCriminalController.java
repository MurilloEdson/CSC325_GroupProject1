package edu.farmingdale.csc325_groupproject;

import Models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.animation.FadeTransition;
import javafx.fxml.*;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class NewCriminalController implements Initializable {

    @FXML
    private TextField dateTxt, timeTxt, nameTxt, addyTxt, descTxt, postTxt;
    @FXML
    private ImageView logoView, logoViewHelp;
    @FXML
    private ChoiceBox<String> neighTxt;
    @FXML
    private ImageView profilePicture;
    @FXML
    private MenuItem userName;

    private ArrayList<Criminal> comps = new ArrayList<Criminal>();
    @FXML
    private AnchorPane rootPane;

    FadeTransition fade = new FadeTransition();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        clearAll();
        profilePicture.setImage(SignInController.UA.current.profilePic);
        userName.setText(SignInController.UA.current.getFirstName());
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
        if(SignInController.UA.isEditting()){
            setEditText(SignInController.UA.criminalUpdate);
        }
        fadeIn();
    }

    @FXML
    void helpWindow(MouseEvent event) {
        System.out.println("pressed");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help:");
        alert.setContentText("Please Input the data of the criminal here \nThe Data will then be inserted into the crime DB");
        alert.show();
    }

    @FXML
    void InputData(ActionEvent event) {
        Criminal b = new Criminal();
        b.CrimeDate = dateTxt.getText() + timeTxt.getText();
        b.Neighborhood = neighTxt.getValue();
        b.Post = Integer.parseInt(postTxt.getText());
        b.Name = nameTxt.getText();
        b.Address = addyTxt.getText();
        b.Description = descTxt.getText();
        comps.add(b);

        DocumentReference docRef = App.fstore.collection("Criminals").document(UUID.randomUUID().toString());
        // Add document data  with id "alovelace" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("crimeDate", b.CrimeDate);
        data.put("Address", b.Address);
        data.put("Name", b.Name);
        data.put("Neighborhood", b.Neighborhood);
        data.put("Description", b.Description);
        data.put("Post", b.Post);
        //asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    @FXML
    private void switchToMenu() throws IOException {
        fadeOut("Menu");
        SignInController.UA.setEditting(false);
    }

    public void fadeIn() {
        rootPane.setOpacity(0);
        fade.setDelay(Duration.millis(1000));
        fade.setNode(rootPane);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public void fadeOut(String scene) {
        fade.setDuration(Duration.millis(1000));
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
    private void clearAll() {
        dateTxt.clear();
        timeTxt.clear();
        nameTxt.clear();
        addyTxt.clear();
        descTxt.clear();
        postTxt.clear();
        neighTxt.setValue(null);
    }

    public void setEditText(Criminal cr) {
        if (cr != null) {
            dateTxt.setText(cr.CrimeDate);
            timeTxt.setText(cr.CrimeTime);
            nameTxt.setText(cr.Name);
            addyTxt.setText(cr.Address);
            descTxt.setText(cr.Description);
            postTxt.setText("" + cr.Post);
            neighTxt.setValue(cr.Neighborhood);
        }
    }
}
