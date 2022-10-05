
package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class TertiaryController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Menu");
    }
    @FXML
    private void switchToQuaternary() throws IOException {
        App.setRoot("ComplaintUI");
    }
}
