package fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository;

import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemessaJpaRepository extends JpaRepository<RemessaPersistence, Long> {

}
