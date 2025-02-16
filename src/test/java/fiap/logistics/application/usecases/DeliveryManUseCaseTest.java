package fiap.logistics.application.usecases;

import fiap.logistics.entregador.dto.DeliveryManDTO;
import fiap.logistics.entregador.entitydomain.DeliveryManDomain;
import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;
import fiap.logistics.entregador.exceptions.DeliveryManException;
import fiap.logistics.entregador.mappers.DeliveryManMapper;
import fiap.logistics.entregador.repository.DeliveryManRepository;
import fiap.logistics.entregador.usecase.DeliveryManUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeliveryManUseCaseTest {

    @Mock
    private DeliveryManRepository manRepository;

    @InjectMocks
    private DeliveryManUseCase deliveryManUseCase;

    private DeliveryManDomain deliveryManDomain;
    private DeliveryManPersistence deliveryManPersistence;
    private DeliveryManDTO deliveryManDTO;

    @BeforeEach
    void setUp() {
        deliveryManDomain = DeliveryManDomain.builder().id(1L).name("Test DeliveryMan").build();
        deliveryManPersistence = DeliveryManMapper.toPersistence(deliveryManDomain);
        deliveryManDTO = DeliveryManMapper.toDTOFromPersistence(deliveryManPersistence);
    }

    @Test
    void saveDeliveryMan_Success() {
        deliveryManUseCase.saveDeliveryMan(deliveryManDomain);
        verify(manRepository, times(1)).save(any(DeliveryManPersistence.class));
    }

    @Test
    void saveDeliveryMan_Error() {
        doThrow(new RuntimeException("Simulated error")).when(manRepository).save(any(DeliveryManPersistence.class));

        DeliveryManException exception = assertThrows(DeliveryManException.class, () -> {
            deliveryManUseCase.saveDeliveryMan(deliveryManDomain);
        });

        assertEquals("Error saving DeliveryMan", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }

    @Test
    void getDeliveryMan_Success() {
        when(manRepository.findById(1L)).thenReturn(deliveryManPersistence);
        DeliveryManDTO result = deliveryManUseCase.getDeliveryMan(1L);
        assertEquals(deliveryManDTO, result);
    }

    @Test
    void getDeliveryMan_NotFound() {
        when(manRepository.findById(1L)).thenReturn(null);

        DeliveryManException exception = assertThrows(DeliveryManException.class, () -> {
            deliveryManUseCase.getDeliveryMan(1L);
        });

        assertEquals("DeliveryMan not found with id: 1", exception.getMessage());
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }

    @Test
    void getAllDeliveryMan_Success() {
        Collection<DeliveryManPersistence> deliveryManPersistenceList = Arrays.asList(deliveryManPersistence, deliveryManPersistence);
        List<DeliveryManDTO> deliveryManDTOList = Arrays.asList(deliveryManDTO, deliveryManDTO);

        when(manRepository.findAll()).thenReturn(deliveryManPersistenceList);

        List<DeliveryManDTO> result = deliveryManUseCase.getAllDeliveryMan();

        assertEquals(deliveryManDTOList, result);
    }

    @Test
    void getAllDeliveryMan_Error() {
        when(manRepository.findAll()).thenThrow(new RuntimeException("Simulated error"));

        DeliveryManException exception = assertThrows(DeliveryManException.class, () -> {
            deliveryManUseCase.getAllDeliveryMan();
        });

        assertEquals("Error getting DeliveryMan", exception.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, exception.getHttpStatus());
    }
}
