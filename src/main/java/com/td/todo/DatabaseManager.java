package com.td.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Page connexion bdd
 * et init si non exist de la table
 */
public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:tasks.db";

    /**
     * @return une connexion a la bdd
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à SQLite", e);
        }
    }

    /**
     * Init la bdd si non exist
     */
    public static void initializeDatabase() {
        String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                dueDate TEXT,
                description TEXT,
                completed BOOLEAN DEFAULT FALSE
            );
            """;

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
