package exception;

public class EmployeeInvalidNumberException extends RuntimeException {
    public EmployeeInvalidNumberException(String s) {
        super(s);
    }
}
