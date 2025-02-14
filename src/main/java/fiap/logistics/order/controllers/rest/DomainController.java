package fiap.logistics.order.controllers.rest;


import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.usecases.PedidoUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class DomainController {

    private final PedidoUsecase pedidoUsecase;

    public DomainController(PedidoUsecase pedidoUsecase) {
        this.pedidoUsecase = pedidoUsecase;
    }

    @GetMapping("/{id}")
    @ResponseStatus (HttpStatus.OK)
    public Order getOrderById(@PathVariable Long id) {
        return pedidoUsecase.findByNumeroPedido(id);
    }

}
