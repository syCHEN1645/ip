package rook.exception;

/**
 * Exception from missing information or contents.
 */
public class MissingInfoException extends RookException {
    public MissingInfoException() {
        super("Excuse me, my Lord, I need more information.");
    }
}
