package Group3.exception;

public class EmailOrPasswordInvalidException extends RuntimeException {
    public EmailOrPasswordInvalidException(String message){
        super(message);
    }
}
