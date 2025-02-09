package fiap.logistics.deliveryorder.controllers;

import fiap.logistics.deliveryorder.entities.domain.DeliveryInfo;
import fiap.logistics.deliveryorder.repositories.remessapedidosentrega.PreparaRemessaDePedidosRepositoryImpl;
import fiap.logistics.deliveryorder.services.DeliveryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    private final PreparaRemessaDePedidosRepositoryImpl remessaPedidoEntregaRepositoryImpl;
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService, PreparaRemessaDePedidosRepositoryImpl remessaPedidoEntregaRepositoryImpl) {
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


}