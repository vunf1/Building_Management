package exception;

public class EmployeeDuplicatedNumberException extends RuntimeException {
    public EmployeeDuplicatedNumberException(String s) {
        super(s);
        System.out.println("Duplicated User");
    }
}
