package fiap.logistics.domain.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class CalculoRotaException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public CalculoRotaException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
