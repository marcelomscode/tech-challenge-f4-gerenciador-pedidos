package fiap.logistics.api.controllers;


import fiap.logistics.infrastructure.persistence.OrderPersistence;
import fiap.logistics.application.usecases.pedidos.PedidoUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class DomainController {

    private final PedidoUsecase pedidoUsecase;

    public DomainController(PedidoUsecase pedidoUsecase) {
        this.pedidoUsecase = pedidoUsecase;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderPersistence getOrderById(@PathVariable String id) {
        return pedidoUsecase.findByNumeroPedido(id);
    }

}
