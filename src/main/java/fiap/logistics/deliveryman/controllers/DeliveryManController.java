package fiap.logistics.deliveryman.controllers;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.services.DeliveryManService;
import fiap.logistics.deliveryman.usecase.DeliveryManUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryman")
public class DeliveryManController {

    private final DeliveryManUseCase deliveryManUseCase;

    public DeliveryManController(DeliveryManUseCase deliveryManUseCase) {
        this.deliveryManUseCase = deliveryManUseCase;
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

}
