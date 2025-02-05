package fiap.logistics.deliverydomain.usecasedelivery;

import fiap.logistics.deliverydomain.gateways.PedidoGateway;
import fiap.logistics.deliverydomain.gateways.PedidoJson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoUsecase {

    private PedidoGateway pedidoGateway;

    public void salvarPedido(PedidoJson pedido) {

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(pedido.getId());
        pedidoSalvo.setDescricao(pedido.getDescricao());
        pedidoSalvo.setCep(pedido.getCep());
        pedidoSalvo.setEndereco(pedido.getEndereco());
        pedidoSalvo.setDataCompra(pedido.getDataCompra());
        pedidoSalvo.setPrevisaoDataEntrega(pedido.getPrevisaoDataEntrega());
        pedidoSalvo.setStatus(pedido.getStatus());

        //Salvar tbm latitude e longitude via API de cepaberto.com



        log.info("Salvando pedido: {}", pedido);
        pedidoGateway.save(pedidoSalvo);
    }

}
