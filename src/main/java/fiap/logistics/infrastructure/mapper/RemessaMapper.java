package fiap.logistics.infrastructure.mapper;

import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.domain.model.remessa.RemessaDomain;

public class RemessaMapper {

    public static RemessaDomain toDomain(RemessaPersistence remessaEntity) {
        return new RemessaDomain(
                Long.parseLong(remessaEntity.getIdRemessa()),
                 remessaEntity.getStatusRemessa());
    }


}
