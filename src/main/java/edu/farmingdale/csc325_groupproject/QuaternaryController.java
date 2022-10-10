package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import javafx.fxml.*;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class QuaternaryController implements Initializable {
    
    @FXML
    private TextField codeTxt;
    @FXML
    private TextField dateTxt;
    @FXML
    private TextField descTxt;
    @FXML
    private TextField distTxt;
    @FXML
    private TextField loc1Txt;
    @FXML
    private TextField locTxt;
    @FXML
    private TextField nameTxt;
    @FXML
    private TextField neighTxt;
    @FXML
    private TextField postTxt;
    @FXML
    private TextField timeTxt;
    @FXML
    private TextField totTxt;
    @FXML
    private TextField weapTxt;
    
    private ArrayList<Complaint> comps = new ArrayList<Complaint>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    @FXML
    void InputData(ActionEvent event) {
        Complaint b = new Complaint();
        b.CrimeDate = dateTxt.getText();
        b.CrimeTime = timeTxt.getText();
        b.CrimeCode = codeTxt.getText();
        b.CrimeLoc = locTxt.getText();
        b.CrimeDesc = descTxt.getText();
        b.CrimeWeap = weapTxt.getText();
        b.CrimePost = Integer.parseInt(postTxt.getText());
        b.CrimeDist = distTxt.getText();
        b.CrimeNeigh = neighTxt.getText();
        b.CrimeLoc1 = loc1Txt.getText();
        b.CrimeTot = Integer.parseInt(totTxt.getText());
        b.CrimeName = nameTxt.getText();
        comps.add(b);
    }
    
    @FXML
    private void switchToMenu() throws IOException {
        App.setRoot("Menu");
    }
}
