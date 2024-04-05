package Sunbase.example.Project.customExceptions;

public class InvalidSearchException extends RuntimeException{
    public InvalidSearchException(String message) {
        super(message);
    }
}
