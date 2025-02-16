package fiap.logistics.deliveryorder.controllers;

import fiap.logistics.deliveryorder.entities.domain.DeliveryInfo;
import fiap.logistics.deliveryorder.repositories.remessapedidosentrega.PreparaRemessaDePedidosRepositoryImplRefatorar;
import fiap.logistics.deliveryorder.services.DeliveryService;
import fiap.logistics.entregador.exceptions.DeliveryManException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("deliverydelivery")
public class DeliveryController {

    private final PreparaRemessaDePedidosRepositoryImplRefatorar remessaPedidoEntregaRepositoryImpl;
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService, PreparaRemessaDePedidosRepositoryImplRefatorar remessaPedidoEntregaRepositoryImpl) {
        this.deliveryService = deliveryService;
        this.remessaPedidoEntregaRepositoryImpl = remessaPedidoEntregaRepositoryImpl;
    }

    @GetMapping("/route")
    public List<DeliveryInfo> getDeliveryRoute(
            @RequestParam String originCep,
            @RequestParam List<String> waypointCeps
    ) {
        return deliveryService.calculateDeliveryRoute(originCep, waypointCeps);
    }

    @GetMapping("/test")
    public String test() {
        remessaPedidoEntregaRepositoryImpl.preparaRemessaPedidoParaEntregar(LocalDate.now());
        return "Teste";
    }

    @GetMapping("/exceptions/{id}")
    public String exceptions(@PathVariable Long id) {

        int numero = 1;

        if(numero == 1){
            throw new DeliveryManException("Erro ao buscar entregador", HttpStatus.NOT_FOUND);
        }

        return "Teste";
    }

}