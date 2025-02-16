package fiap.logistics.infrastructure.persistences.adapter;

import fiap.logistics.domain.model.entrega.EntregadorDisponivel;
import fiap.logistics.domain.model.entrega.EntregadorDomain;
import fiap.logistics.domain.model.entrega.StatusEntregador;
import fiap.logistics.domain.ports.EntregadorDisponivelRepository;
import fiap.logistics.entregador.mappers.DeliveryManMapper;
import fiap.logistics.entregador.repository.db.DeliveryManJpaRepository;
import fiap.logistics.entregador.services.DeliveryManPersistenceService;
import fiap.logistics.infrastructure.mapper.EntregadorMapper;
import fiap.logistics.infrastructure.persistences.repository.EntregadorDisponivelJpaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class EntregadorDisponivelImpl implements EntregadorDisponivelRepository {

    private final DeliveryManJpaRepository deliveryManJpaRepository;
    private final EntregadorDisponivelJpaRepository entregadorDisponivelJpaRepository;
    private final DeliveryManPersistenceService deliveryManPersistenceService;


    @Override
    public List<EntregadorDomain> buscarEntregadoresDisponiveis() {
        log.info("Buscando entregadores disponíveis");
        var entregadorDisponivel = entregadorDisponivelJpaRepository.findByStatusEntregador(StatusEntregador.DISPONIVEL.getCodigo());
        log.info("Entregadores disponíveis encontrados: {}", entregadorDisponivel.size());
        return entregadorDisponivel.stream().map(EntregadorMapper::toDomain).toList();
    }

    @Override
    public void atualizarStatusEntregador(Long idEntregador, StatusEntregador statusEntregador) {
        log.info("Atualizando status do entregador {} para {}", idEntregador, statusEntregador);
        var entregadorDisponivel = deliveryManJpaRepository.findById(idEntregador).orElseThrow();
        var entregador = entregadorDisponivelJpaRepository.findByIdEntregador(entregadorDisponivel);

        entregador.setStatusEntregador(statusEntregador.getCodigo());
        entregadorDisponivelJpaRepository.save(entregador);
        log.info("Status do entregador {} atualizado para {}", idEntregador, statusEntregador);
    }

    @Override
    public void salvarEntregador(Long idEntregador) {

        var entregador = deliveryManPersistenceService.getDeliveryMan(idEntregador);
        var deliveryMan = DeliveryManMapper.fromDtoToPersistence(entregador);

        EntregadorDisponivel entregadorDisponivel = new EntregadorDisponivel();
        entregadorDisponivel.setIdEntregador(deliveryMan);
        entregadorDisponivel.setStatusEntregador(StatusEntregador.DISPONIVEL.getCodigo());

        entregadorDisponivelJpaRepository.save(entregadorDisponivel);
    }


}