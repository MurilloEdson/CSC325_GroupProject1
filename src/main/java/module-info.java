module edu.farmingdale.csc325_groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires com.google.gson;

    opens edu.farmingdale.csc325_groupproject to javafx.fxml, com.google.gson;
    exports edu.farmingdale.csc325_groupproject;
}
