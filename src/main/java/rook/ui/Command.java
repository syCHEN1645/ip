package rook.ui;

/**
 * Contains <code>enum</code> that each corresponds to a String command keyword
 * used in commandline user inputs to <code>Rook</code>.
 */
public enum Command {
    ADD_TODO_COMMAND("todo"),
    ADD_EVENT_COMMAND("event"),
    ADD_TASK_COMMAND("task"),
    ADD_DEADLINE_COMMAND("deadline"),
    EXIT_COMMAND("bye"),
    LIST_COMMAND("list"),
    UNMARK_COMMAND("unmark"),
    MARK_COMMAND("mark"),
    DELETE_COMMAND("delete"),
    FIND_COMMAND("find")
    ;

    private String cmd;

    private Command(String cmd) {
        setCmd(cmd);
    }

    private void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * @return A <code>String</code> identifier of the command.
     */
    public String getCmd() {
        return cmd;
    }
}