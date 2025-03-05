package rook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rook.task.Deadline;
import rook.task.Event;
import rook.task.Task;
import rook.task.Todo;

public class FileManager {
    private File saveFile;
    private final String FOLDER_PATH = "./data";
    private final String FILE_PATH = "./data/saved.txt";
    private final String DIVIDER = "/";
    private final String TRUE = "true";
    private final String FALSE = "false";

    public FileManager() {
        try {
            new File(FOLDER_PATH).mkdir();
            saveFile = new File(FILE_PATH);
            if (saveFile.createNewFile()) {
                System.out.println("File created: " + saveFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred creating file.");
        }
    }

    public File getSaveFile() {
        return saveFile;
    }

    public void setSaveFile(File saveFile) {
        this.saveFile = saveFile;
    }

    public String getFILEPATH() {
        return FILE_PATH;
    }

    private Task convertToTask(String line) {
            Task task;
            String[] data = line.split(DIVIDER);
            if (data[0].equals(Event.getINITIAL())) {
                task = new Event(data[1], data[3], data[4]);
                task.setDone((data[2].equals(TRUE)));
            } else if (data[0].equals(Deadline.getINITIAL())) {
                task = new Deadline(data[1], data[3]);
                task.setDone((data[2].equals(TRUE)));
            } else if (data[0].equals(Todo.getINITIAL())) {
                task = new Todo(data[1]);
                task.setDone((data[2].equals(TRUE)));
            } else {
                task = null;
            }
            return task;
    }

    public ArrayList<Task> readTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                Task task = convertToTask(scanner.nextLine());
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void writeTask(Task task) throws IOException {
        FileWriter writer = new FileWriter(saveFile, true);
        if (task.getClass() == Todo.class) {
            writer.write(Todo.getINITIAL() + DIVIDER
                    + ((Todo) task).getDescription() + DIVIDER
                    + ((Todo) task).isDone());
        } else if (task.getClass() == Deadline.class) {
            writer.write(Deadline.getINITIAL() + DIVIDER
                    + ((Deadline) task).getDescription() + DIVIDER
                    + ((Deadline) task).isDone() + DIVIDER
                    + ((Deadline) task).getByTime());
        } else if (task.getClass() == Event.class) {
            writer.write(Event.getINITIAL() + DIVIDER
                    + ((Event) task).getDescription() + DIVIDER
                    + ((Event) task).isDone() + DIVIDER
                    + ((Event) task).getStartTime() + DIVIDER
                    + ((Event) task).getEndTime());
        }
        writer.write(System.lineSeparator());
        writer.close();
    }

    public void deleteTask(int index) {
        List<String> tasks = new ArrayList<>();
        int counter = 1;
        try {
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                if (counter != index) {
                    tasks.add(scanner.nextLine());
                } else {
                    scanner.nextLine();
                }
                counter++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            FileWriter writer = new FileWriter(saveFile);
            // write data back to the file
            for (String line: tasks) {
                writer.write(line + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
