package fiap.logistics.infrastructure.repository;

import fiap.logistics.application.usecases.entregadores.DeliveryManUseCase;
import fiap.logistics.domain.enums.StatusEntregador;
import fiap.logistics.domain.model.EntregadorDomain;
import fiap.logistics.domain.repository.EntregadorDisponivelRepository;
import fiap.logistics.infrastructure.mapper.DeliveryManMapper;
import fiap.logistics.infrastructure.mapper.EntregadorMapper;
import fiap.logistics.infrastructure.persistence.EntregadorDisponivelPersistence;
import fiap.logistics.infrastructure.repository.jpa.DeliveryManJpaRepository;
import fiap.logistics.infrastructure.repository.jpa.EntregadorDisponivelJpaRepository;
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
    private final DeliveryManUseCase deliveryManUseCase;

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

        var entregador = deliveryManUseCase.getDeliveryMan(idEntregador);
        var deliveryMan = DeliveryManMapper.fromDtoToPersistence(entregador);

        EntregadorDisponivelPersistence entregadorDisponivel = new EntregadorDisponivelPersistence();
        entregadorDisponivel.setIdEntregador(deliveryMan);
        entregadorDisponivel.setStatusEntregador(StatusEntregador.DISPONIVEL.getCodigo());

        entregadorDisponivelJpaRepository.save(entregadorDisponivel);
    }


}