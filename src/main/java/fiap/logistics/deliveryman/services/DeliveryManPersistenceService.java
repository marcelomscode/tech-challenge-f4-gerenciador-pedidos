package fiap.logistics.deliveryman.services;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import fiap.logistics.deliveryman.mappers.DeliveryManMapper;
import fiap.logistics.deliveryman.repository.DeliveryManRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryManPersistenceService {

    private final DeliveryManRepository deliveryManRepository;

    public DeliveryManPersistenceService(DeliveryManRepository deliveryManRepository) {
        this.deliveryManRepository = deliveryManRepository;
    }

    public void saveDeliveryMan(DeliveryManDomain deliveryMan) {

        try {
            deliveryManRepository.save(DeliveryManMapper.toPersistence(deliveryMan));
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error saving DeliveryMan", HttpStatus.BAD_REQUEST);
        }

    }

    public DeliveryManDTO getDeliveryMan(Long id) {
        try {
            DeliveryManPersistence deliveryManPersistence = deliveryManRepository.findById(id).orElseThrow(() -> new DeliveryManException("DeliveryMan not found", HttpStatus.NOT_FOUND));
            return DeliveryManMapper.toDTOFromPersistence(deliveryManPersistence);
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public List<DeliveryManDTO> getAllDeliveryMan() {
        try {
            List<DeliveryManPersistence> deliveryManPersistence = deliveryManRepository.findAll();
            return deliveryManPersistence.stream().map(DeliveryManMapper::toDTOFromPersistence).toList();
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

}
