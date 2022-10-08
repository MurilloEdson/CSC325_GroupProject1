
package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class TertiaryController implements Initializable {
     @FXML
    private ListView<?> criminalNames;
     
    ObservableList<Criminal> criminals = (ObservableList<Criminal>)criminalNames.getItems();
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
}
