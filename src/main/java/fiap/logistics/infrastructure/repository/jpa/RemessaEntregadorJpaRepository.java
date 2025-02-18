package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.infrastructure.persistence.RemessaEntregadorPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemessaEntregadorJpaRepository extends JpaRepository<RemessaEntregadorPersistence, Long> {
}
