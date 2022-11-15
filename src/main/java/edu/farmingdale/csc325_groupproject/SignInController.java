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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;

public class SignInController implements Initializable{
    static User currUser = new User();
    @FXML
    private Button primaryButton;
    @FXML
    private ImageView logoView;
    @FXML
    private TextField userInput;
    @FXML     
    private PasswordField userPassword;
    
    @FXML
    private void verifyCredentials() throws IOException {
        //TODO: Read the person credentials with the Admin class
        
        String username = userInput.getText();
        String password = userPassword.getText();
        boolean signedIn = false;
        ApiFuture<QuerySnapshot> future =  App.fstore.collection("Users").get();
        List<QueryDocumentSnapshot> documents;
        try 
        {
            documents = future.get().getDocuments();
            if(!documents.isEmpty())
            {
                for (QueryDocumentSnapshot document : documents) 
                {
                    String docUser = document.getData().get("username").toString();
                    System.out.println(docUser);
                    String docPass = document.getData().get("password").toString();
                    System.out.println(docPass);
                    if(username.equals(docUser) && password.equals(docPass)){
                        //currUser
                        currUser = DBtoObject(docUser, docPass, document);
                        currUser.setProfilePic("OIP.jpg");
                        signedIn = true;
                        App.setRoot("Menu");
                        System.out.println("Hello "+ currUser.getFirstName()+", Welcome");
                        break;
                    }
                }
            }
            else
            {
               System.out.println("No data"); 
            }
            if(signedIn == false){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("WRONG INFO");
                alert.setContentText("You have entered an invalid username and/or password");
                alert.show();
            }  
        }
        catch (InterruptedException | ExecutionException ex ) {
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img = new Image("/Aesthetics/OIP.jpg");
        logoView.setImage(img);
    }
    @FXML
    private void toCreateWinodw(MouseEvent event) {
        try {
            App.setRoot("SignUp");
        } catch (IOException ex) {
            System.out.println("Can't load window");
            
        }
    }
    private User DBtoObject(String uN,String pW,QueryDocumentSnapshot doc){
        String fname =  doc.getData().get("firstName").toString();
        String lname =  doc.getData().get("lastName").toString();
        String email =  doc.getData().get("email").toString();
        int lvl =  Integer.parseInt(doc.getData().get("securityLevel").toString());
        
        User client = new User(uN,pW,fname,lname,email,lvl);
        return client;
    }
}
