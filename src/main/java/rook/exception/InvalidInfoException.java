package rook.exception;

/**
 * Exception from invalid information such as excess information, invalid information
 * types and contents.
 */
public class InvalidInfoException extends RookException {
    public InvalidInfoException() {
        super("Excuse me, my Lord, I do not understand your instruction.");
    }
}
