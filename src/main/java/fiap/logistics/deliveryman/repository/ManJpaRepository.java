package fiap.logistics.deliveryman.repository;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManJpaRepository extends JpaRepository<DeliveryManPersistence, Long> {
}
