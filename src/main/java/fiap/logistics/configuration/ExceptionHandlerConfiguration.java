package fiap.logistics.configuration;

import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice

public class ExceptionHandlerConfiguration {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public DeliveryManException genericError(final Throwable e) {
        log.error(e.getMessage(), e);
        return new DeliveryManException(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
