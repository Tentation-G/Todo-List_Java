package com.example.todo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    /**
     * Ajoute une nouvelle tâche en BD.
     */
    public static void addTask(String title, String dueDate, String description) {
        String sql = "INSERT INTO tasks (title, dueDate, description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title);
            pstmt.setString(2, dueDate);
            pstmt.setString(3, description);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retourne toutes les tâches (ordre décroissant par id).
     */
    public static List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks ORDER BY id DESC";
        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String dueDate = rs.getString("dueDate");
                String desc = rs.getString("description");
                boolean completed = rs.getBoolean("completed");

                Task task = new Task(id, title, dueDate, desc, completed);
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Supprime une tâche par son id.
     */
    public static void deleteTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Met à jour le champ completed pour la tâche id.
     */
    public static void updateTaskCompletion(int id, boolean completed) {
        String sql = "UPDATE tasks SET completed = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, completed);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
