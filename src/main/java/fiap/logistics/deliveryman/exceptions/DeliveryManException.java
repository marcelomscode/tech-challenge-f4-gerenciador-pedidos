package fiap.logistics.deliveryman.exceptions;

import org.springframework.http.HttpStatus;

public class DeliveryManException extends RuntimeException {

    private final HttpStatus status;

    public DeliveryManException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.status = status;
    }



}
