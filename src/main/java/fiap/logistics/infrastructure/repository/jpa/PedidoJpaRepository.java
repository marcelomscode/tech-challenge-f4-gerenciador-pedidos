package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.domain.exception.EntityNotFoundException;
import fiap.logistics.infrastructure.persistence.OrderPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoJpaRepository extends JpaRepository<OrderPersistence, Long> {

    OrderPersistence getByNumeroPedido(String numeroPedido) throws EntityNotFoundException;

    OrderPersistence findByNumeroPedido(String numeroPedido);

    @Query("SELECT p FROM OrderPersistence p WHERE p.numeroPedido IN :ids")
    List<OrderPersistence> findAllByNumeroPedido(@Param("ids") List<String> ids);

}
