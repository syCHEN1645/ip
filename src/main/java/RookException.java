public class RookException extends Exception {
    protected String errorMessage = "";
    public void printErrorMessage() {
        System.out.println(errorMessage);
    }
}
