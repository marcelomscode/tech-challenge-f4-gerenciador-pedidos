package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryManJpaRepository extends JpaRepository<DeliveryManPersistence, Long> {
}
