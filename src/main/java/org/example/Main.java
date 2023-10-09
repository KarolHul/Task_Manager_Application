package org.example;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        UserInterface userInterface = new UserInterface(taskManager);

        // Exemplary tasks
        Task task1 = new Task("Task 1", "Description 1", LocalDate.of(2023, 10, 15), "High");
        Task task2 = new Task("Task 2", "Description 2", LocalDate.of(2023, 10, 10), "Medium");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        userInterface.start();
    }
}