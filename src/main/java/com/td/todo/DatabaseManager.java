package com.example.todo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Gère la connexion à la base SQLite
 * et l'initialisation de la table `tasks`.
 */
public class DatabaseManager {
    private static final String URL = "jdbc:sqlite:tasks.db";

    /**
     * @return une Connection à la base de données SQLite
     */
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            throw new RuntimeException("Erreur de connexion à SQLite", e);
        }
    }

    /**
     * Initialise la table `tasks` si elle n'existe pas déjà.
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
