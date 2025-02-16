package fiap.logistics.domain.ports;

import fiap.logistics.domain.model.entrega.StatusEntregador;

public interface AtualizarStatusEntregadorRepository {

    void atualizarStatusEntregador(Long idEntregador, StatusEntregador status);

}
