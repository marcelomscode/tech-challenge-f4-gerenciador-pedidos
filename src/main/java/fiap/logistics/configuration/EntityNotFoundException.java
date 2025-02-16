package fiap.logistics.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public EntityNotFoundException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
