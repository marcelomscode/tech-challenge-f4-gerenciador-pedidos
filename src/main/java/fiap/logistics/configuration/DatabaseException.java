package fiap.logistics.configuration;

import org.springframework.http.HttpStatus;

public class DatabaseException extends RuntimeException {
    private final HttpStatus status;

    public DatabaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public DatabaseException(String message, HttpStatus status, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}