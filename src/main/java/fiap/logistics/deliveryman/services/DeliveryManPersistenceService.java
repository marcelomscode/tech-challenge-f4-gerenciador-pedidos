package fiap.logistics.deliveryman.services;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import fiap.logistics.deliveryman.mappers.DeliveryManMapper;
import fiap.logistics.deliveryman.repository.DeliveryManRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryManPersistenceService {

    private final DeliveryManRepositoryImpl deliveryManRepository;

    public DeliveryManPersistenceService(DeliveryManRepositoryImpl deliveryManRepository) {
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
            DeliveryManPersistence deliveryManPersistence = deliveryManRepository.findById(id);
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
