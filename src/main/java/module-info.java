module com.dawissem.crudajavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;


    opens com.dawissem.crudajavafx to javafx.fxml;
    exports com.dawissem.crudajavafx;
}