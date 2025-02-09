package fiap.logistics.deliveryman.repository.db;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryJpaRepository extends JpaRepository<DeliveryManPersistence, Long> {
}
