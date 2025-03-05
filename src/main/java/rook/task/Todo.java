package Rook.Task;

public class Todo extends Task {
    protected static final String INITIAL = "[T]";

    public Todo(String description) {
        super(description);
    }

    public static String getINITIAL() {
        return INITIAL;
    }

    @Override
    public String toString() {
        if (isDone()) {
            return INITIAL + FINISH + " " + getDescription();
        }
        return INITIAL + N_FINISH + " " + getDescription();
    }
}
