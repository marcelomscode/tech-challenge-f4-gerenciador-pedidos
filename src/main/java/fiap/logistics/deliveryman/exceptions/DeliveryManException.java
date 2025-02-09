package fiap.logistics.deliveryman.exceptions;

import org.springframework.http.HttpStatus;

public class DeliveryManException extends RuntimeException {

    public DeliveryManException(String mensagem, HttpStatus status) {
        super(mensagem);
    }

}
