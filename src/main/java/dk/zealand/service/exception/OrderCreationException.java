package dk.zealand.service.exception;

public class OrderCreationException extends RuntimeException {

    public OrderCreationException(String message) {
        super(message);
    }
}
