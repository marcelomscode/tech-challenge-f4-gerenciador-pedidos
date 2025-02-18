package fiap.logistics.infrastructure.mapper;

import fiap.logistics.domain.model.EntregadorDomain;
import fiap.logistics.infrastructure.persistence.EntregadorDisponivelPersistence;

public interface EntregadorMapper {

    static EntregadorDomain toDomain(EntregadorDisponivelPersistence entregadorEntity) {
        return new EntregadorDomain(entregadorEntity.getId(),
                entregadorEntity.getIdEntregador().getId(),
                entregadorEntity.getStatusEntregador());
    }

}
