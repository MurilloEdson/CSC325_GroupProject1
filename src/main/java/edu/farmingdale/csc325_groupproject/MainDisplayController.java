package edu.farmingdale.csc325_groupproject;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import java.io.*;
import javafx.fxml.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import java.util.logging.*;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MainDisplayController implements Initializable {

    @FXML
    private ListView<String> criminalNames = new ListView<>();
    @FXML
    private ListView<String> complaintDesc = new ListView<>();
    @FXML
    private ChoiceBox<String> locations;
    @FXML
    private ToggleButton permissions;
    @FXML
    private Button addCrime, addCriminal;
    @FXML
    private ImageView profilePicture;
    @FXML
    private MenuItem userName;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label displayTitleLabel;
    
    FadeTransition fade = new FadeTransition();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        profilePicture.setImage(SignInController.currUser.profilePic);
        userName.setText(SignInController.currUser.getFirstName());
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        permissions.selectedProperty().set(true);
        if (!SignInController.currUser.isAdmin()) {
            permissions.selectedProperty().set(false);
            permissions.disableProperty().set(true);
        }

        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>() {
            }.getType());
            for (String curr : list) {
                locations.getItems().add(curr);
            }
        } catch (FileNotFoundException ex) {
        }
        locations.setOnAction(this::setListView);
        testAdminOrViewer();

        fadeIn();
    }

    public void setListView(ActionEvent event) {
        try {
            ObservableList<String> criminals = (ObservableList<String>) criminalNames.getItems();
            ObservableList<String> complaints = (ObservableList<String>) complaintDesc.getItems();
            criminals.clear();
            complaints.clear();
            String target = locations.getValue();
            // Create a reference to the cities collection
            CollectionReference crimeTable = App.fstore.collection("Criminals");
            CollectionReference compTable = App.fstore.collection("Complaints");
            // Create a query against the collection.
            Query query = crimeTable.whereEqualTo("Neighborhood", target);
            Query query2 = compTable.whereEqualTo("Neighborhood", target);
            // retrieve  query results asynchronously using query.get()
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            ApiFuture<QuerySnapshot> querySnapshot2 = query2.get();

            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                String name = document.get("Name").toString();
                criminals.add(name);
            }
            for (DocumentSnapshot document2 : querySnapshot2.get().getDocuments()) {
                String desc = document2.get("Description").toString();
                criminals.add(desc);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MainDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void testAdminOrViewer() {
        if (permissions.isSelected()) {
            permissions.setText("AdminView");
            addCrime.setDisable(false);
            addCriminal.setDisable(false);
        } else {
            permissions.setText("ViewOnly");
            addCrime.setDisable(true);
            addCriminal.setDisable(true);
        }
    }

    @FXML
    private void switchToMenu() throws IOException {
        fadeOut("Menu");
    }

    private void close() throws IOException {
        System.exit(0);
    }

    @FXML
    private void newCriminal() throws IOException {
        fadeOut("NewCriminal");
    }

    @FXML
    private void newComplaint() throws IOException {
        fadeOut("NewComplaint");
    }

    @FXML
    private void setListView(ContextMenuEvent event) {
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
