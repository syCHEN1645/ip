package Rook.Exception;

public class RookException extends Exception {
    public RookException(String errorMessage) {
        super(errorMessage);
    }
    public void printErrorMessage() {
        System.out.println(getMessage());
    }
}
