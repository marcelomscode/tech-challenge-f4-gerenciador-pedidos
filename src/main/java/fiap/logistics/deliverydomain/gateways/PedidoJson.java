package fiap.logistics.deliverydomain.gateways;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoJson {

    private Long   id;
    private Long numeroPedido;
    private String descricao;
    private String endereco;
    private String cep;
    private String dataCompra;
    private String previsaoDataEntrega;
    private String status;

}
