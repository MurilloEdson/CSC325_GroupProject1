package edu.farmingdale.csc325_groupproject;

import Models.*;
import com.google.api.core.*;
import com.google.cloud.firestore.*;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.io.*;
import java.net.URL;
import java.util.*;
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
    static Stack<String> lastPage = new Stack<>();

    @FXML
    private void logout() throws IOException {
        fadeOut("SignIn");
    }

    @FXML
    private void switchToMainDisplay() throws IOException {
        fadeOut("MainDisplay");
        lastPage.add("Menu");
    }

    @FXML
    private void switchToNewComplaint() throws IOException {
        fadeOut("NewComplaint");
        lastPage.add("Menu");
    }

    @FXML
    private void switchToNewCriminal() throws IOException {
        fadeOut("NewCriminal");
        lastPage.add("Menu");
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
        //loadComplaint();
        });
        fade.play();
    }
    public void settings(){
        SignInController.UA.setEdittingUser(SignInController.UA.current);
        fadeOut("SignUp");
    }
    private void loadComplaint(){
        ArrayList<Complaint> al = new ArrayList<>();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        ArrayList<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Locations.json");
            list = gson.fromJson(fr, new TypeToken<ArrayList<String>>() {}.getType());

        } catch (FileNotFoundException ex) {
        }

        for (int i = 0; i < 30000; i++) {
            int rand = (int) (Math.random() * (list.size()));
            Complaint c = new Complaint();
            c.setCrimeTime("10:00:00");
            c.setCrimeDate("2016-04-05");
            c.setCrimeDesc("I was at the Chase Bank on 23rd Street but "
                    + "when the criminal robbed the bank and "
                    + "was arrested I was still late to work and lost my job");
            c.setNeighborhood(list.get(rand));
            al.add(c);
        }

        for (int j = 0; j < al.size(); j++) {
            DocumentReference docRef = App.fstore.collection("Complaint").document(UUID.randomUUID().toString());
            // Add document data  with id "alovelace" using a hashmap
            Map<String, Object> data = new HashMap<>();
            data.put("crimeDate", al.get(j).CrimeDate);
            data.put("crimeTime", al.get(j).CrimeTime);
            data.put("Description", al.get(j).CrimeDesc);
            data.put("Neighborhood", al.get(j).Neighborhood);
            //asynchronously write data
            ApiFuture<WriteResult> result = docRef.set(data);
        }
    }
}
