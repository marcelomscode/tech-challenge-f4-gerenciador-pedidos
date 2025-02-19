package fiap.logistics.infrastructure.repository;

import fiap.logistics.domain.enums.StatusRemessa;
import fiap.logistics.infrastructure.persistence.RemessaEntregadorPersistence;
import fiap.logistics.domain.repository.AssociarRemessaEntregadorRepository;
import fiap.logistics.infrastructure.repository.jpa.RemessaEntregadorJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class AssociarRemessaEntregadorImpl implements AssociarRemessaEntregadorRepository {

    private final RemessaEntregadorJpaRepository associarRemessaEntregadorRepository;

    public AssociarRemessaEntregadorImpl(RemessaEntregadorJpaRepository associarRemessaEntregadorRepository) {
        this.associarRemessaEntregadorRepository = associarRemessaEntregadorRepository;
    }

    @Override
    public void associarRemessaAEntregador(Long idRemessa, Long idEntregador) {
        log.info("Associando remessa {} ao entregador {}", idRemessa, idEntregador);

        RemessaEntregadorPersistence remessaEntregadorPersistence = new RemessaEntregadorPersistence();
        remessaEntregadorPersistence.setIdRemessa(idRemessa);
        remessaEntregadorPersistence.setIdEntregador(idEntregador);
        remessaEntregadorPersistence.setStatusRemessa(StatusRemessa.EM_ROTA_DE_ENTREGA.getId());
        remessaEntregadorPersistence.setDataInclusao(LocalDateTime.now());

        associarRemessaEntregadorRepository.save(remessaEntregadorPersistence);

        log.info("Remessa {} associada ao entregador {}", idRemessa, idEntregador);
    }
}