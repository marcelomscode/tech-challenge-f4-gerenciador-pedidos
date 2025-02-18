package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.infrastructure.persistence.OrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RemessaPedidosEntregaJpaRepository extends JpaRepository<OrderPersistence, Long> {

    @Query("SELECT o FROM OrderPersistence o WHERE o.previsaoDataEntrega <= ?1 and o.status = 1 order by o.cep")
    List<OrderPersistence> findByDeliveryDate(LocalDate deliveryDate);

}
