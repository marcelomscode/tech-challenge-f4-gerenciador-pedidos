package fiap.logistics.domain.ports;


import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;

public interface AtualizarStatusRemessaRepository {

    void atualizarStatusRemessa(Long idRemessa, int status);

}
