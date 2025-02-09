package fiap.logistics.deliveryorder.entities.domain.remessa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Builder
public class Remessa {

    private String idRemessa;
    private Long numeroPedido;
    private String enderecoEntrega;
    private int numeroResidencia;
    private int statusRemessa;
    private String tempoEntrega;

}
