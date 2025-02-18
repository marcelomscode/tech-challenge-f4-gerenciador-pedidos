package fiap.logistics.domain.repository;

import fiap.logistics.domain.enums.StatusEntregador;
import fiap.logistics.domain.model.EntregadorDomain;

import java.util.List;

public interface EntregadorDisponivelRepository {

    List<EntregadorDomain> buscarEntregadoresDisponiveis();

    void atualizarStatusEntregador(Long idEntregador, StatusEntregador statusEntregador);

    void salvarEntregador(Long idEntregador);
}
