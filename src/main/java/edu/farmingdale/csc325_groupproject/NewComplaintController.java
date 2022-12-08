package edu.farmingdale.csc325_groupproject;

import Models.*;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
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
    private Button update,addInput;
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
            setEditText(SignInController.UA.complaintUpdate);
            update.setVisible(true);
            addInput.setDisable(true);
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
        String fxml = MenuController.lastPage.pop();
        fadeOut(fxml);
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
                update.setVisible(false);
                addInput.setDisable(false);
            } catch (IOException ex) {
                System.out.println("Can't load window");
            }
        });
        fade.play();

    }
    private void clearAll(){
        timeTxt.clear();
        neighTxt.setValue(null);
        txtArea.clear();
    }
    
    private void setEditText(Complaint cp) {
        if (cp != null) {
            timeTxt.setText(cp.CrimeTime);
            neighTxt.setValue(cp.Neighborhood);
            txtArea.setText(cp.CrimeDesc);
        }
    }
    
    public void update(){
        //Query chainedQuery1 = cities.whereEqualTo("state", "CO").whereEqualTo("name", "Denver");
        Complaint c = SignInController.UA.complaintUpdate;
        String docID = "";
        try {
            CollectionReference criminals = App.fstore.collection("Complaint");
            Query query = criminals.whereEqualTo("Neighborhood", c.getNeighborhood());
            ApiFuture<QuerySnapshot> querySnapshot = query.get();
            List<QueryDocumentSnapshot> docRefList = querySnapshot.get().getDocuments();
            DocumentReference docRef = null;

            Map<String, Object> updates = new HashMap<>();
            updates.put("Description", txtArea.getText());
            updates.put("Neighborhood", neighTxt.getValue());
            updates.put("crimeDate", date2.getValue());
            updates.put("crimeTime", timeTxt.getText());

            for (QueryDocumentSnapshot curr : docRefList) {
                docRef = curr.getReference();
            }
            if(docRef != null){
                ApiFuture<WriteResult> futureUpdate = docRef.update(updates);
                WriteResult result = futureUpdate.get();
            }
            //System.out.println("Write result: " + result);
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(NewComplaintController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void settings(){
        SignInController.UA.setEdittingUser(SignInController.UA.current);
        fadeOut("SignUp");
    }
}
