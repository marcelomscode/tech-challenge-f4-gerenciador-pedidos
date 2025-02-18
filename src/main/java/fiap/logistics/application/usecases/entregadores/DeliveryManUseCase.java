package fiap.logistics.application.usecases.entregadores;

import fiap.logistics.api.dto.DeliveryManDTO;
import fiap.logistics.domain.model.DeliveryManDomain;
import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;
import fiap.logistics.domain.exception.DeliveryManException;
import fiap.logistics.infrastructure.mapper.DeliveryManMapper;
import fiap.logistics.domain.repository.DeliveryManRepository;
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
        } catch (Exception e) {
            throw new DeliveryManException("Error saving DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public DeliveryManDTO getDeliveryMan(Long id) {
        try {
            DeliveryManPersistence deliveryManPersistence = manRepository.findById(id);
            if (deliveryManPersistence == null) {
                throw new DeliveryManException("DeliveryMan not found with id: " + id, HttpStatus.NOT_FOUND);
            }
            return DeliveryManMapper.toDTOFromPersistence(deliveryManPersistence);
        } catch (DeliveryManException e) {
            throw e;
        } catch (Exception e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public List<DeliveryManDTO> getAllDeliveryMan() {
        try {
            return manRepository.findAll().stream().map(DeliveryManMapper::fromPersistenceToDTO).toList();
        } catch (Exception e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

}
