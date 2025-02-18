package fiap.logistics.domain.repository;

import fiap.logistics.domain.enums.StatusEntregador;

public interface AtualizarStatusEntregadorRepository {

    void atualizarStatusEntregador(Long idEntregador, StatusEntregador status);

}
