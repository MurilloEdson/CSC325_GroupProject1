module edu.farmingdale.csc325_groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens edu.farmingdale.csc325_groupproject to javafx.fxml;
    exports edu.farmingdale.csc325_groupproject;
}
