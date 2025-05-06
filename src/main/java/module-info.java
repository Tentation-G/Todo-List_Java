module com.example.todo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Ouvre le package à FXML
    opens com.example.todo to javafx.fxml;

    // Exporte le package
    exports com.example.todo;
}
