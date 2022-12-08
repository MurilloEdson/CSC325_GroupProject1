package edu.farmingdale.csc325_groupproject;

import Models.*;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import java.io.*;
import javafx.fxml.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.collections.*;
import java.util.logging.*;
import javafx.scene.input.*;

import com.google.api.core.ApiFuture;
import com.google.gson.reflect.TypeToken;
import java.net.URL;
import java.util.concurrent.ExecutionException;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import java.util.Stack;

public class MainDisplayController implements Initializable {

    @FXML
    private ListView<Criminal> criminalNames = new ListView<>();
    @FXML
    private ListView<Complaint> complaintDesc = new ListView<>();
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
    
    private ObservableList<Criminal> criminals;
    private ObservableList<Complaint> complaints;
    private List<Criminal> criminalList = new ArrayList<>();
    private List<Complaint> complaintList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        MenuController.lastPage.add("MainDisplay");
        profilePicture.setImage(SignInController.UA.current.profilePic);
        userName.setText(SignInController.UA.current.getFirstName());
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list;
        permissions.selectedProperty().set(true);
        if (!SignInController.UA.current.isAdmin()) {
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
        String target = locations.getValue();
        criminals = (ObservableList<Criminal>) criminalNames.getItems();
        complaints = (ObservableList<Complaint>) complaintDesc.getItems();
        criminals.clear();
        complaints.clear();
        queryForCriminal("Neighborhood",target);
        queryForComplaint("Neighborhood",target);
        for(Criminal curr : criminalList){
            criminals.add(curr);
        }
        for(Complaint curr2 : complaintList){
            complaints.add(curr2);
        }
        criminalList.clear();
        complaintList.clear();
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
        String fxml = MenuController.st.pop();
        fadeOut(fxml);
    }

    private void close() throws IOException {
        System.exit(0);
    }

    @FXML
    private void newCriminal() throws IOException {
        fadeOut("NewCriminal");
        MenuController.st.add("MainDisplay");
    }

    @FXML
    private void newComplaint() throws IOException {
        fadeOut("NewComplaint");
        MenuController.st.add("MainDisplay");

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
    
    public void editCriminal(MouseEvent arg0) throws IOException {
        Criminal selectedCriminal = new Criminal();
        if (!criminalNames.getItems().isEmpty() && arg0.getClickCount() == 2 && permissions.isSelected()) {
            selectedCriminal = (Criminal)selectedObject(criminalNames);
            SignInController.UA.setEdittingCriminal(selectedCriminal);
            App.setRoot("NewCriminal");
        }
    }

    public void editComplaint(MouseEvent arg0) throws IOException {
        Complaint selectedComplaint = new Complaint();
        if (!complaintDesc.getItems().isEmpty() && arg0.getClickCount() == 2 && permissions.isSelected()) {
            selectedComplaint = (Complaint)selectedObject(complaintDesc);
            SignInController.UA.setEdittingComplaint(selectedComplaint);
            App.setRoot("NewComplaint");
        }
    }

    public void queryForCriminal(String field, String target) {
        try {
            CollectionReference crimeTable = App.fstore.collection("Criminals");
            Query query = crimeTable.whereEqualTo(field, target);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            for (DocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Criminal criminal = new Criminal();
                criminal.fillCriminalInfo(document);
                criminalList.add(criminal);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MainDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void queryForComplaint(String field, String target) {
        try {
            CollectionReference compTable = App.fstore.collection("Complaint");
            Query query2 = compTable.whereEqualTo(field, target);
            ApiFuture<QuerySnapshot> querySnapshot2 = query2.get();
            for (DocumentSnapshot document2 : querySnapshot2.get().getDocuments()) {
                Complaint complaint = new Complaint();
                complaint.fillComplaintInfo(document2);
                complaintList.add(complaint);
            }
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MainDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Object selectedObject(ListView lv){
        Object ob = lv.getSelectionModel().getSelectedItem();
        return ob;
    }
    public void settings(){
        SignInController.UA.setEdittingUser(SignInController.UA.current);
        fadeOut("SignUp");
    }
}
