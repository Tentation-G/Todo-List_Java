package com.td.todo;

public class Task {
    private int id;
    private String title;
    private String dueDate;
    private String description;
    private boolean completed;

    public Task(int id, String title, String dueDate, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.description = description;
        this.completed = completed;
    }

    public int getId() { return id; }

    public String getTitle() { return title; }

    public String getDueDate() { return dueDate; }

    public String getDescription() { return description; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }
}
