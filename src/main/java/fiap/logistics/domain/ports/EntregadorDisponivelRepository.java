package fiap.logistics.domain.ports;

import fiap.logistics.domain.model.entrega.EntregadorDomain;
import fiap.logistics.domain.model.entrega.StatusEntregador;

import java.util.List;

public interface EntregadorDisponivelRepository {

    List<EntregadorDomain> buscarEntregadoresDisponiveis();
    void atualizarStatusEntregador(Long idEntregador, StatusEntregador statusEntregador);
    public void salvarEntregador(Long idEntregador);
}
