package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class PrimaryController {
    @FXML
    private Button primaryButton;
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("Menu");
    }
}
