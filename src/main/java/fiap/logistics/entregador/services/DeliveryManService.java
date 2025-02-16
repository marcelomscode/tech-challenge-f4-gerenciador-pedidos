package fiap.logistics.entregador.services;

import fiap.logistics.entregador.dto.DeliveryManDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DeliveryManService {

    private final DeliveryManPersistenceService deliveryManPersistenceService;

    public DeliveryManService(DeliveryManPersistenceService deliveryManPersistenceService) {
        this.deliveryManPersistenceService = deliveryManPersistenceService;
    }

    public DeliveryManDTO getDeliveryMan(Long id) {
        log.info("Getting DeliveryMan");
        return deliveryManPersistenceService.getDeliveryMan(id);
    }

    public List<DeliveryManDTO> getAllDeliveryMan() {
        log.info("Getting all DeliveryMan");
        return deliveryManPersistenceService.getAllDeliveryMan();
    }

}
