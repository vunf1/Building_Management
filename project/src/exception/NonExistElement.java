package exception;

public class NonExistElement extends RuntimeException {
    public NonExistElement(String s) {
        super(s);
        System.out.println("Non Exist Element");
    }
}