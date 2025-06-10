# 📘 To-do List JavaFx – Documentation d'installation et d'utilisation

## 🧾 Présentation

**To-Do List JavaFX** est une application de gestion de tâches développée en Java 21, JavaFX, et SQLite.
Elle permet d’ajouter, afficher, compléter et supprimer des tâches.

---

## 🛠️ Prérequis

- Java 21 ou version compatible
- JavaFX (via SDK ou module intégré à l’IDE)
- Un IDE comme IntelliJ IDEA ou Eclipse
- Gradle/Maven (facultatif, pour la gestion de dépendances)

---

## 🧾 Fonctionnalités

- 📌 Ajouter une tâche avec titre, date d’échéance, et description
- 🟩 Marquer une tâche comme complétée
- ❌ Supprimer une tâche
- 📋 Affichage dans une TableView avec colonne de statut et bouton d’action
- 📅 Mise en évidence des dates dépassées
- 💾 Sauvegarde automatique dans une base SQLite

---

## 📁 Structure du projet

```

src/
├── com.example.todo/
│   ├── Main.java
│   ├── DatabaseManager.java
│   ├── Task.java
│   ├── TaskService.java
│   ├── ToDoController.java
│   └── styles.css
├── resources/
│   └── todo-view.fxml
└── module-info.java

```

## 🗃️ Base de données SQLite
Le fichier tasks.db est automatiquement généré à la racine du projet au premier lancement.
La table utilisée est :

```

CREATE TABLE IF NOT EXISTS tasks (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    dueDate TEXT,
    description TEXT,
    completed BOOLEAN DEFAULT FALSE
);

```

## 📂 Installation et 🚀 Lancement du projet

1. **Cloner le dépôt**
   
    ```
    
    git clone https://github.com/ton-utilisateur/todo-javafx.git
    cd todo-javafx
    
    ```
    
3. **Ouvrir le projet dans votre IDE**

4. **Lancer la classe Main.java**
