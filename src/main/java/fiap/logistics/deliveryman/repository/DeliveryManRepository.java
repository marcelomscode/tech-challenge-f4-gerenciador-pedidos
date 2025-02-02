package fiap.logistics.deliveryman.repository;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryManRepository extends JpaRepository<DeliveryManPersistence, Long> {
}
