package fiap.logistics.deliveryman.mappers;

import fiap.logistics.deliveryman.entitydomain.DeliveryManDomain;
import fiap.logistics.deliveryman.dto.DeliveryManDTO;
import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;

public class DeliveryManMapper {

    public static DeliveryManDTO toDTO(DeliveryManDomain deliveryManDomain) {
        return DeliveryManDTO.builder()
                .id(deliveryManDomain.getId())
                .name(deliveryManDomain.getName())
                .build();
    }

    public static DeliveryManDTO toDTOFromPersistence(DeliveryManPersistence deliveryManPersistence) {
        return DeliveryManDTO.builder()
                .id(deliveryManPersistence.getId())
                .name(deliveryManPersistence.getName())
                .build();
    }


    public static DeliveryManDomain toDomain(DeliveryManDTO deliveryManDTO) {
        return DeliveryManDomain.builder()
                .id(deliveryManDTO.getId())
                .name(deliveryManDTO.getName())
                .build();
    }

    public static DeliveryManPersistence toPersistence(DeliveryManDomain deliveryManDomain) {
        return DeliveryManPersistence.builder()
                .id(deliveryManDomain.getId())
                .name(deliveryManDomain.getName())
                .build();
    }

}
