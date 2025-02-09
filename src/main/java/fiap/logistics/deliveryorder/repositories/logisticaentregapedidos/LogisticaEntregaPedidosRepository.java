package fiap.logistics.deliveryorder.repositories.logisticaentregapedidos;

import fiap.logistics.order.entities.persistence.Order;

import java.util.List;

public interface LogisticaEntregaPedidosRepository {

    List<Order> buscaPedidosParaEntregar();

}
