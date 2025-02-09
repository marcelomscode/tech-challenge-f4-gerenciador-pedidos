package fiap.logistics.order.controllers.event.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoJson {

    private Long numeroPedido;
    private String descricao;
    private String endereco;
    private String cep;
    private String bairro;
    private String cidade;
    private String dataCompra;
    private String previsaoDataEntrega;

}
