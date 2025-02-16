package fiap.logistics.order.repositories;

import fiap.logistics.order.entities.persistence.Order;

import java.util.List;

public interface PedidoRepository {

    void save(Order pedido );
    void atualizaStatusPedido(Long idPedido, Integer status);
    Order findByNumeroPedido(Long idPedido);
    List<Order> findAllByNumeroPedido(List<Long> ids);

}
