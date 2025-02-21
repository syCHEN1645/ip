package Rook;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Rook.Task.Deadline;
import Rook.Task.Event;
import Rook.Task.Task;
import Rook.Task.Todo;

public class FileManager {
    private File saveFile;
    private String filePath = "";
    private final String DIVIDER = "\\";

    public Task readTask(String line) throws IOException {
        FileReader reader = new FileReader(saveFile);
        return null;
    }

    public void writeTask(Task task) throws IOException {
        FileWriter writer = new FileWriter(saveFile, true);

        if (task.getClass() == Todo.class) {
            writer.write(((Todo) task).getINITIAL() + DIVIDER
                    + ((Todo) task).getDescription() + DIVIDER
                    + ((Todo) task).isDone());
        } else if (task.getClass() == Deadline.class) {
            writer.write(((Deadline) task).getINITIAL() + DIVIDER
                    + ((Deadline) task).getDescription() + DIVIDER
                    + ((Deadline) task).isDone() + DIVIDER
                    + ((Deadline) task).getByTime());
        } else if (task.getClass() == Event.class) {
            writer.write(((Event) task).getINITIAL() + DIVIDER
                    + ((Event) task).getDescription() + DIVIDER
                    + ((Event) task).isDone() + DIVIDER
                    + ((Event) task).getStartTime() + DIVIDER
                    + ((Event) task).getEndTime());
        }
        writer.write(System.lineSeparator());
        writer.close();
    }
}
