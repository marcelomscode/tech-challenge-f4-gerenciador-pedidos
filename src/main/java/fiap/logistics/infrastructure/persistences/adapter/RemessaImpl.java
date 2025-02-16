package fiap.logistics.infrastructure.persistences.adapter;


import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;
import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository.RemessaJpaRepository;
import fiap.logistics.domain.model.remessa.RemessaDomain;
import fiap.logistics.domain.ports.RemessaRepository;
import fiap.logistics.infrastructure.mapper.RemessaMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RemessaImpl implements RemessaRepository {

    private final RemessaJpaRepository remessaJpaRepository;

    public RemessaImpl(RemessaJpaRepository remessaJpaRepository) {
        this.remessaJpaRepository = remessaJpaRepository;
    }

    @Override
    public List<RemessaDomain> buscarRemessasPorStatus(StatusRemessa status) {
        log.info("Buscando remessas por status {}", status);
        var remessas = remessaJpaRepository.findByStatusRemessa(status.getId());
        log.info("Remessas encontradas: {}", remessas.size());
        return remessas.stream().map(RemessaMapper::toDomain).toList();
    }

    @Override
    public List<RemessaPersistence> buscarRemessasPorIdRemessa(String idRemessa) {
        return remessaJpaRepository.findByIdRemessa(idRemessa);
    }
}
