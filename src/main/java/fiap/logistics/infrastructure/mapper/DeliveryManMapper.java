package fiap.logistics.infrastructure.mapper;

import fiap.logistics.api.dto.DeliveryManDTO;
import fiap.logistics.domain.model.DeliveryManDomain;
import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;

public class DeliveryManMapper {

    private DeliveryManMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static DeliveryManDTO toDTOFromPersistence(DeliveryManPersistence deliveryManPersistence) {
        return DeliveryManDTO.builder()
                .id(deliveryManPersistence.getId())
                .name(deliveryManPersistence.getName())
                .build();
    }

    public static DeliveryManPersistence toPersistence(DeliveryManDomain deliveryManDomain) {
        return DeliveryManPersistence.builder()
                .id(deliveryManDomain.getId())
                .name(deliveryManDomain.getName())
                .build();
    }

    public static DeliveryManDTO fromPersistenceToDTO(DeliveryManPersistence deliveryManPersistence) {
        return DeliveryManDTO.builder()
                .id(deliveryManPersistence.getId())
                .name(deliveryManPersistence.getName())
                .build();
    }

    public static DeliveryManPersistence fromDtoToPersistence(DeliveryManDTO deliveryManDTO) {
        return DeliveryManPersistence.builder()
                .id(deliveryManDTO.getId())
                .name(deliveryManDTO.getName())
                .build();
    }

}
