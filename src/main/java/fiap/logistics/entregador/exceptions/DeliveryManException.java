package fiap.logistics.entregador.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class DeliveryManException extends RuntimeException {

    private final String message;
    private final HttpStatus httpStatus;

    public DeliveryManException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
