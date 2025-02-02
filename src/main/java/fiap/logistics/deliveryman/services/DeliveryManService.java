package fiap.logistics.deliveryman.services;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import fiap.logistics.deliveryman.mappers.DeliveryManMapper;
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

    public void saveDeliveryMan(DeliveryManDTO deliveryManDTO) {
        try {
            log.info("Saving DeliveryMan");
            deliveryManPersistenceService.saveDeliveryMan(DeliveryManMapper.toDomain(deliveryManDTO));
            log.info("DeliveryMan saved");
        } catch (DeliveryManException e) {
            log.error("Error saving DeliveryMan", e);
        }
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
