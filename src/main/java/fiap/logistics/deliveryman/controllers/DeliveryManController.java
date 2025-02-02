package fiap.logistics.deliveryman.controllers;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.services.DeliveryManService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryman")
public class DeliveryManController {

    private final DeliveryManService deliveryManService;

    public DeliveryManController(DeliveryManService deliveryManService) {
        this.deliveryManService = deliveryManService;
    }

    @PostMapping
    public ResponseEntity<String> saveDeliveryMan(@RequestBody DeliveryManDTO deliveryManDTO) {
        deliveryManService.saveDeliveryMan(deliveryManDTO);
        return ResponseEntity.ok("DeliveryMan saved");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeliveryManDTO> getDeliveryMan(@PathVariable Long id) {
        return ResponseEntity.ok(deliveryManService.getDeliveryMan(id));
    }

    @GetMapping
    public ResponseEntity<List<DeliveryManDTO>> getAllDeliveryMan() {
        return ResponseEntity.ok(deliveryManService.getAllDeliveryMan());
    }

}
