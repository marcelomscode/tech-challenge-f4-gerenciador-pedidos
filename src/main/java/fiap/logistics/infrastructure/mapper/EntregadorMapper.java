package fiap.logistics.infrastructure.mapper;

import fiap.logistics.domain.model.entrega.EntregadorDisponivel;
import fiap.logistics.domain.model.entrega.EntregadorDomain;

public class EntregadorMapper {

    public static EntregadorDomain toDomain(EntregadorDisponivel entregadorEntity) {
        return new EntregadorDomain(entregadorEntity.getId(),
                entregadorEntity.getIdEntregador().getId(),
                entregadorEntity.getStatusEntregador());
    }

}
