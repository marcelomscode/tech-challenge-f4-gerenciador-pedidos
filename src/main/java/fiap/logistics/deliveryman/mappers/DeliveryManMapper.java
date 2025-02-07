package fiap.logistics.deliveryman.mappers;

import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;

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

}
