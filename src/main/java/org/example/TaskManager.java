package org.example;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // Add new task to list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Edit task
    public void editTask(int index, Task updatedTask) {
        if (index >= 0 && index < tasks.size()) {
            tasks.set(index, updatedTask);
        } else {
            System.out.println("Nieprawidłowy indeks zadania.");
        }
    }

    // Delete task from list
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Nieprawidłowy indeks zadania.");
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void sortByPriority() {
        tasks.sort((task1, task2) -> task1.getPriority().compareTo(task2.getPriority()));
    }

    public void sortByTargetDate() {
        tasks.sort((task1, task2) -> task1.getTarget_date().compareTo(task2.getTarget_date()));
    }
}
