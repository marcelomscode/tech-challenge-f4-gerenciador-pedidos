package fiap.logistics.order.repositories.db;

import fiap.logistics.configuration.EntityNotFoundException;
import fiap.logistics.order.entities.persistence.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<Order, Long> {

    Order getByNumeroPedido(Long numeroPedido) throws EntityNotFoundException;

    List<Order> findAllBynumeroPedido(Long numeroPedido) throws EntityNotFoundException;

    Order findBynumeroPedido(Long numeroPedido);

    @Query("SELECT p FROM Order p WHERE p.numeroPedido IN :ids")
    List<Order> findAllByNumeroPedido(@Param("ids") List<Long> ids);

}
