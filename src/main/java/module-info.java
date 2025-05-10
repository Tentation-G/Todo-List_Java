module com.td.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Ouvre le package à FXML
    opens com.td.todo to javafx.fxml;

    // Exporte le package
    exports com.td.todo;
}
