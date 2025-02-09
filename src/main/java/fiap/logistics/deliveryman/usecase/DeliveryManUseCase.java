package fiap.logistics.deliveryman.usecase;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import fiap.logistics.deliveryman.mappers.DeliveryManMapper;
import fiap.logistics.deliveryman.repository.DeliveryManRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryManUseCase {

    private final DeliveryManRepository manRepository;

    public void saveDeliveryMan(DeliveryManDomain deliveryMan) {
        try {
            manRepository.save(DeliveryManMapper.toPersistence(deliveryMan));
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error saving DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public DeliveryManDTO getDeliveryMan(Long id) {
        try {
            return DeliveryManMapper.toDTOFromPersistence(manRepository.findById(id));
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public List<DeliveryManDTO> getAllDeliveryMan() {
        try {
            return manRepository.findAll().stream().map(DeliveryManMapper::fromPersistenceToDTO).toList();
        } catch (DeliveryManException e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

}
