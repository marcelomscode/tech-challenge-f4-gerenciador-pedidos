package fiap.logistics.entregador.usecase;

import fiap.logistics.entregador.dto.DeliveryManDTO;
import fiap.logistics.entregador.entitydomain.DeliveryManDomain;
import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;
import fiap.logistics.entregador.exceptions.DeliveryManException;
import fiap.logistics.entregador.mappers.DeliveryManMapper;
import fiap.logistics.entregador.repository.DeliveryManRepository;
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
        } catch (Exception e) { // Captura Exception (ou SQLException, dependendo do que o repository lança)
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
        } catch (DeliveryManException e) { // Não capture aqui, apenas relance
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
