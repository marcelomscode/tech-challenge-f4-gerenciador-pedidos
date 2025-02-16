package fiap.logistics.entregador.controllers;

import fiap.logistics.application.usecases.entregadores.EntregadorDisponivelUseCase;
import fiap.logistics.entregador.dto.DeliveryManDTO;
import fiap.logistics.entregador.entitydomain.DeliveryManDomain;
import fiap.logistics.entregador.usecase.DeliveryManUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryman")
public class EntregadorController {

    private final DeliveryManUseCase deliveryManUseCase;
    private final EntregadorDisponivelUseCase entregadorDisponivelUseCase;

    public EntregadorController(DeliveryManUseCase deliveryManUseCase, EntregadorDisponivelUseCase entregadorDisponivelUseCase) {
        this.deliveryManUseCase = deliveryManUseCase;
        this.entregadorDisponivelUseCase = entregadorDisponivelUseCase;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public String saveDeliveryManUseCase(@RequestBody DeliveryManDTO deliveryManDTO) {
        deliveryManUseCase.saveDeliveryMan(DeliveryManDomain.builder().name(deliveryManDTO.getName()).build());
        return "DeliveryMan saved";
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryManDTO> getDeliveryMan(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryManUseCase.getDeliveryMan(id));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryManDTO>> getAllDeliveryMan() {
        return ResponseEntity.ok(deliveryManUseCase.getAllDeliveryMan());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/entregador-disponivel/{idEntregador}")
    public String salvarEntregadorDisponivel(@PathVariable Long idEntregador) {

        entregadorDisponivelUseCase.salvarEntregador(idEntregador);

        return "Entregador salvo com sucesso";
    }


}
