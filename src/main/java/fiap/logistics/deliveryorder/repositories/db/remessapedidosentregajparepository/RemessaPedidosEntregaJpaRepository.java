package fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository;

import fiap.logistics.order.entities.persistence.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RemessaPedidosEntregaJpaRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.previsaoDataEntrega <= ?1 and o.status = 1 order by o.cep")
    List<Order> findByDeliveryDate(LocalDate deliveryDate);

}
