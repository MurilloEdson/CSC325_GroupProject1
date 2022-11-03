module edu.farmingdale.csc325_groupproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires com.google.gson;
    requires firebase.admin;
    requires google.cloud.firestore;
    requires com.google.auth;
    requires com.google.auth.oauth2 ;
    requires google.cloud.core;
    requires com.google.api.apicommon;
    

    opens edu.farmingdale.csc325_groupproject to javafx.fxml, com.google.gson, firebase.admin;
    exports edu.farmingdale.csc325_groupproject;
    exports Models;
}
