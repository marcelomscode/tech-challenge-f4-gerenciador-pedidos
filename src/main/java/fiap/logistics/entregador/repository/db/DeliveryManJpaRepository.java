package fiap.logistics.entregador.repository.db;

import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryManJpaRepository extends JpaRepository<DeliveryManPersistence, Long> {
}
