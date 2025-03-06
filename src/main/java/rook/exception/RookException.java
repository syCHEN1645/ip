package rook.exception;

/**
 * Serves as parent class of more specific exception types used in <code>Rook</code>
 */
public class RookException extends Exception {
    public RookException(String errorMessage) {
        super(errorMessage);
    }
    public void printErrorMessage() {
        System.out.println(getMessage());
    }
}
