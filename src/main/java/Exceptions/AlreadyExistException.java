package Exceptions;

/**
 * Created by ilmaz on 12.10.16.
 */
public class AlreadyExistException extends Exception {
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String s) {
        super(s);
    }
}
