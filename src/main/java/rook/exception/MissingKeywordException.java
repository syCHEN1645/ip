package rook.exception;

/**
 * Exception from missing command keywords.
 */
public class MissingKeywordException extends RookException {
    public MissingKeywordException() {
        super("Excuse me, my Lord, I need more information.");
    }
}
