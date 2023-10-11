package org.example;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UserInterface {
    private TaskManager taskManager;
    private Scanner scanner;

    public UserInterface(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("=== Task Manager ===");
        System.out.println("1. Add New Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. Display Task List");
        System.out.println("5. Sort by Priority");
        System.out.println("6. Sort by Due Date");
        System.out.println("0. Exit");
    }

    public void start() {
        int choice;
        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();  // Read the extra newline

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    displayTaskList();
                    break;
                case 5:
                    taskManager.sortByPriority();
                    break;
                case 6:
                    taskManager.sortByTargetDate();
                    break;
                case 0:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    private void addTask() {
        System.out.println("Adding a New Task:");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        LocalDate dueDate = getValidFutureDate();
        String priority = getValidPriorityInput();

        Task newTask = new Task(title, description, dueDate, priority);
        taskManager.addTask(newTask);
        System.out.println("Task added successfully.");
    }

    private LocalDate getValidFutureDate() {
        LocalDate dueDate = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print("Due Date (YYYY-MM-DD): ");
                String dueDateStr = scanner.nextLine();
                dueDate = LocalDate.parse(dueDateStr);

                if (dueDate.isAfter(LocalDate.now())) {
                    validDate = true;
                } else {
                    System.out.println("The due date must be in the future.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
        return dueDate;
    }

    private String getValidPriorityInput() {
        String priority = null;
        boolean validPriority = false;
        while (!validPriority) {
            System.out.print("Priority (L=Low, M=Medium, H=High): ");
            String priorityInput = scanner.nextLine();
            switch (priorityInput.toUpperCase()) {
                case "L":
                    priority = "Low";
                    validPriority = true;
                    break;
                case "M":
                    priority = "Medium";
                    validPriority = true;
                    break;
                case "H":
                    priority = "High";
                    validPriority = true;
                    break;
                default:
                    System.out.println("Invalid priority. Please enter L, M, or H.");
            }
        }
        return priority;
    }

    private void editTask() {
        System.out.println("Editing a Task:");
        displayTaskList();
        System.out.print("Enter the task number to edit: ");
        int userIndex = scanner.nextInt();
        int internalIndex = userIndex - 1;
        scanner.nextLine();
        if (internalIndex >= 0 && internalIndex < taskManager.getTasks().size()) {
            Task taskToEdit = taskManager.getTasks().get(internalIndex);
            System.out.print("New Title: ");
            String newTitle = scanner.nextLine();
            System.out.print("New Description: ");
            String newDescription = scanner.nextLine();
            LocalDate newDueDate = getValidFutureDate();
            String newPriority = getValidPriorityInput();
            taskToEdit.setTitle(newTitle);
            taskToEdit.setDescription(newDescription);
            taskToEdit.setTarget_date(newDueDate);
            taskToEdit.setPriority(newPriority);
            System.out.println("Task updated successfully.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    private void deleteTask() {
        System.out.println("Deleting a Task:");
        displayTaskList();
        System.out.print("Enter the task number to delete: ");
        int userIndex = scanner.nextInt();
        int internalIndex = userIndex - 1;
        scanner.nextLine();
        if (internalIndex >= 0 && internalIndex < taskManager.getTasks().size()) {
            taskManager.deleteTask(internalIndex);
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Invalid task index.");
        }
    }

    private void displayTaskList() {
        List<Task> tasks = taskManager.getTasks();
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            System.out.println("Task List:");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                int taskNumber = i + 1;
                System.out.println("Number: " + taskNumber);
                System.out.println("Title: " + task.getTitle());
                System.out.println("Description: " + task.getDescription());
                System.out.println("Due Date: " + task.getTarget_date());
                System.out.println("Priority: " + task.getPriority());
                System.out.println();
            }
        }
    }
}
