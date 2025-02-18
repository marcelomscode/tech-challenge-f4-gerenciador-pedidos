package fiap.logistics.infrastructure.mapper;

import fiap.logistics.infrastructure.persistence.RemessaPersistence;
import fiap.logistics.domain.model.remessa.RemessaDomain;

public interface RemessaMapper {

    static RemessaDomain toDomain(RemessaPersistence remessaEntity) {
        return new RemessaDomain(
                Long.parseLong(remessaEntity.getIdRemessa()),
                 remessaEntity.getStatusRemessa());
    }


}
