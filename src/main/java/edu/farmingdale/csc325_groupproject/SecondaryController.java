package edu.farmingdale.csc325_groupproject;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class SecondaryController {
    @FXML
    private Button secondaryButton;
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("Authentication");
    }
    @FXML
    private void switchToTertiary() throws IOException {
        App.setRoot("CriminalUI");
    }
}