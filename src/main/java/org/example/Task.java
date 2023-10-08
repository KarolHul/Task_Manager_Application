package org.example;

import java.time.LocalDate;

public class Task {
    String title;
    String description;
    LocalDate target_date;
    String priority;

    public Task(String title, String description, LocalDate target_date, String priority) {
        this.title = title;
        this.description = description;
        this.target_date = target_date;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTarget_date() {
        return target_date;
    }

    public void setTarget_date(LocalDate target_date) {
        this.target_date = target_date;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
