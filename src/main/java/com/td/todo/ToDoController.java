package com.example.todo;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ToDoController {

    // Champs du formulaire
    @FXML
    private TextField titleField;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private TextArea descriptionArea;

    // TableView et colonnes
    @FXML
    private TableView<Task> taskTable;
    @FXML
    private TableColumn<Task, Boolean> completedCol;
    @FXML
    private TableColumn<Task, String> titleCol;
    @FXML
    private TableColumn<Task, String> descCol;
    @FXML
    private TableColumn<Task, String> dueDateCol;
    @FXML
    private TableColumn<Task, Void> actionCol;

    private final ObservableList<Task> taskList = FXCollections.observableArrayList();

    public void initialize() {
        // Rendre la TableView éditable pour CheckBox
        taskTable.setEditable(true);

        // Configuration colonne "completed" (case à cocher)
        completedCol.setCellValueFactory(new PropertyValueFactory<>("completed"));
        completedCol.setCellFactory(CheckBoxTableCell.forTableColumn(completedCol));

        // Ajouter un commit event pour update la DB (optionnel, dépend de la version de CheckBox usage)
        completedCol.setOnEditCommit(e -> {
            Task task = e.getRowValue();
            boolean newVal = e.getNewValue();
            TaskService.updateTaskCompletion(task.getId(), newVal);
            task.setCompleted(newVal);
        });

        // Colonne "Title"
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        // Colonne "Description"
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));

        // Colonne "dueDate"
        dueDateCol.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        // Surligner en rouge si la date est passée (facultatif)
        dueDateCol.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    try {
                        LocalDate date = LocalDate.parse(item, DateTimeFormatter.ISO_LOCAL_DATE);
                        if (date.isBefore(LocalDate.now())) {
                            setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        } else {
                            setStyle("");
                        }
                    } catch (Exception e) {
                        setStyle("");
                    }
                }
            }
        });

        // Colonne "Action" (bouton Supprimer)
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button deleteBtn = new Button("Supprimer");

            {
                // Largeur btnn pour éviter l'ellipse "..."
                deleteBtn.setMinWidth(80);
                deleteBtn.setOnAction(e -> {
                    Task task = getTableView().getItems().get(getIndex());
                    TaskService.deleteTask(task.getId());
                    loadTasks();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteBtn);
                }
            }
        });

        // Charger la liste initiale
        loadTasks();
        taskTable.setItems(taskList);
    }

    /**
     * ajout de tache via form.
     */
    @FXML
    private void addTask() {
        String title = titleField.getText().trim();
        if (title.isEmpty()) return;

        // Récupère la date du DatePicker
        LocalDate localDate = dueDatePicker.getValue();
        String dateStr = (localDate != null) ? localDate.toString() : "";

        String desc = descriptionArea.getText().trim();

        // Appel service
        TaskService.addTask(title, dateStr, desc);

        // Clear form
        titleField.clear();
        dueDatePicker.setValue(null);
        descriptionArea.clear();

        // Reload table
        loadTasks();
    }

    /**
     * Recharge table depuis bdd.
     */
    private void loadTasks() {
        taskList.setAll(TaskService.getAllTasks());
        taskTable.refresh();
    }
}
