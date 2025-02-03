public enum Command {
    ADD_TODO_COMMAND("todo"),
    ADD_EVENT_COMMAND("event"),
    ADD_TASK_COMMAND("task"),
    ADD_DEADLINE_COMMAND("deadline"),
    EXIT_COMMAND("bye"),
    LIST_COMMAND("list"),
    UNMARK_COMMAND("unmark"),
    MARK_COMMAND("mark");

    private String cmd;

    private Command(String cmd) {
        setCmd(cmd);
    }

    private void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getCmd() {
        return cmd;
    }
}