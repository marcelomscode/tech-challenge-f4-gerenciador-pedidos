package fiap.logistics.deliverydomain.gateways;

import fiap.logistics.deliverydomain.usecasedelivery.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoGateway extends JpaRepository<Pedido, Long> {


}
