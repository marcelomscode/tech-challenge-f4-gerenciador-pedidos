package fiap.logistics.deliveryorder.repositories.remessapedidosentrega;

import java.time.LocalDate;

public interface PreparaRemessaDePedidosRepository {

    void preparaRemessaPedidoParaEntregar(LocalDate dataParaEntrega);

}
