package fiap.logistics.domain.model;

import lombok.Getter;

@Getter
public class EntregadorDomain {

    private final Long id;
    private final Long idEntregador;
    private final int statusEntregador;

    public EntregadorDomain(Long id, Long idEntregador, int statusEntregador) {
        this.id = id;
        this.idEntregador = idEntregador;
        this.statusEntregador = statusEntregador;
    }

}
