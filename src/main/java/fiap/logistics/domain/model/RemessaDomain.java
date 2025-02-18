package fiap.logistics.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class RemessaDomain {

    private String idRemessa;
    private String numeroPedido;
    private String enderecoEntrega;
    private int numeroResidencia;
    private int statusRemessa;
    private String tempoEntrega;

}
