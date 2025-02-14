package fiap.logistics.order.repositories.db;

import fiap.logistics.configuration.EntityNotFoundException;
import fiap.logistics.order.entities.persistence.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Order, Long> {

    Order getByNumeroPedido(Long numeroPedido) throws EntityNotFoundException;

}
