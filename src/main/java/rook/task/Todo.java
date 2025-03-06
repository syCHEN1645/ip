package rook.task;

/**
 * A simple type of <code>Task</code> that contains <code>isDone, description</code>.
 */
public class Todo extends Task {
    protected static final String INITIAL = "[T]";

    /**
     * Constructs a <code>Todo</code> task with <code>isDone</code> set to false.
     * There is no time component.
     *
     * @param description Text description of this task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * @return Initial identifier of this object, "[T]".
     */
    public static String getINITIAL() {
        return INITIAL;
    }

    /**
     * @return String format of this task, e.g. [T][X] Homework.
     */
    @Override
    public String toString() {
        if (isDone()) {
            return INITIAL + FINISH + " " + getDescription();
        }
        return INITIAL + N_FINISH + " " + getDescription();
    }
}
