package Rook.Task;

public class Todo extends Task {
    protected final String INITIAL = "[T]";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        if (isDone()) {
            return INITIAL + FINISH + " " + getDescription();
        }
        return INITIAL + N_FINISH + " " + getDescription();
    }
}
