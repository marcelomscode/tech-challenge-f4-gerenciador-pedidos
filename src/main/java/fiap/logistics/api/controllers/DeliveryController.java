package fiap.logistics.api.controllers;

import fiap.logistics.application.usecases.CalcularRotaDeEntregaUseCase;
import fiap.logistics.application.usecases.remessas.PreparaRemessaDePedidosUseCase;
import fiap.logistics.domain.model.DeliveryInfoDomain;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {

    private final PreparaRemessaDePedidosUseCase remessaPedidoEntregaRepositoryImpl;
    private final CalcularRotaDeEntregaUseCase deliveryService;

    public DeliveryController(PreparaRemessaDePedidosUseCase remessaPedidoEntregaRepositoryImpl, CalcularRotaDeEntregaUseCase deliveryService) {
        this.remessaPedidoEntregaRepositoryImpl = remessaPedidoEntregaRepositoryImpl;
        this.deliveryService = deliveryService;

    }

    @GetMapping("/route")
    public List<DeliveryInfoDomain> getDeliveryRoute(
            @RequestParam String originCep,
            @RequestParam List<String> waypointCeps
    ) {
        return deliveryService.calculaRotaEntrega(originCep, waypointCeps);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/prepara-remessa")
    public String test() {
        remessaPedidoEntregaRepositoryImpl.preparaRemessaPedidoParaEntregar(LocalDate.now());
        return "Teste";
    }

}