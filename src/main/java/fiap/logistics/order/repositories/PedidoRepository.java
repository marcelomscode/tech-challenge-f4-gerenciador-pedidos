package fiap.logistics.order.repositories;

import fiap.logistics.order.entities.persistence.Order;

public interface PedidoRepository {

    void save(Order pedido );
    void atualizaStatusPedido(Long idPedido, Integer status);
    Order findByNumeroPedido(Long idPedido);

}
