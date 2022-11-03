package edu.farmingdale.csc325_groupproject;

import com.google.cloud.firestore.Firestore;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    public static Firestore fstore;
    private final FirestoreContext contxtFirebase = new FirestoreContext();
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        scene = new Scene(loadFXML("SignIn"), 640, 440);
        stage.setScene(scene);
        stage.setTitle("Crime Records Management System");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}