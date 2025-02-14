package fiap.logistics.deliveryorder.repositories.remessapedidosentrega;

import fiap.logistics.order.entities.persistence.Order;

import java.time.LocalDate;
import java.util.List;

public interface PreparaRemessaDePedidosRepository {

    void preparaRemessaPedidoParaEntregar(LocalDate dataParaEntrega);

    void salvaListaRemessa(List<Order> pedidos) throws InterruptedException;

}
