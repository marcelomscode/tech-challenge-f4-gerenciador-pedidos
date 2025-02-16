package fiap.logistics.infrastructure.persistences.adapter;

import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;
import fiap.logistics.deliveryorder.entities.domain.remessa.Remessa;
import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository.RemessaJpaRepository;
import fiap.logistics.domain.ports.AtualizarStatusRemessaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AtualizarStatusRemessaImpl implements AtualizarStatusRemessaRepository {

    private final RemessaJpaRepository atualizarStatusRemessaRepository;

    public AtualizarStatusRemessaImpl(RemessaJpaRepository atualizarStatusRemessaRepository) {
        this.atualizarStatusRemessaRepository = atualizarStatusRemessaRepository;
    }

    @Override
    public void atualizarStatusRemessa(Long idRemessa, int status) {
        // Lógica para atualizar status da remessa no banco de dados
      log.info("Atualizando status da remessa {} para {}", idRemessa, status);
      List<RemessaPersistence> listaDeRemessas = atualizarStatusRemessaRepository.findByIdRemessa(String.valueOf(idRemessa));

      listaDeRemessas.forEach(remessa -> {
          remessa.setStatusRemessa(status);
          atualizarStatusRemessaRepository.save(remessa);
      });
      log.info("Status da remessa {} atualizado para {}", idRemessa, status);
    }
}