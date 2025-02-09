package fiap.logistics.deliveryorder;

import fiap.logistics.order.entities.persistence.Order;

import java.time.LocalDate;
import java.util.List;

public interface logisticaentregapedidos {

    void preparaPedidoParaEntregar(LocalDate dataParaEntrega);
    void criaRemessaParaEntrega(List<Order> pedidos);
    void buscaEntregadorDisponivel();
    void colocaRemessaEmEsperaProximoEntregador();

}
