package fiap.logistics.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DatabaseException extends RuntimeException {
    private final HttpStatus status;

    public DatabaseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}