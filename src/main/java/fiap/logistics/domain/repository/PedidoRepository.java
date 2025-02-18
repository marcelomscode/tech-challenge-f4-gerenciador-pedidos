package fiap.logistics.domain.repository;

import fiap.logistics.infrastructure.persistence.OrderPersistence;

import java.util.List;

public interface PedidoRepository {

    void save(OrderPersistence pedido );
    void atualizaStatusPedidoPorId(Long idPedido, Integer status);
    void atualizaStatusPedidoPorNumeroPedido(String idPedido, Integer status);
    OrderPersistence findByNumeroPedido(String idPedido);
    List<OrderPersistence> findAllByNumeroPedido(List<String> ids);
    OrderPersistence findById(Long id);

}
