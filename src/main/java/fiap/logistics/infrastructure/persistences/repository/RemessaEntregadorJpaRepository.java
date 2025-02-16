package fiap.logistics.infrastructure.persistences.repository;

import fiap.logistics.domain.model.remessa.RemessaEntregadorPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RemessaEntregadorJpaRepository extends JpaRepository<RemessaEntregadorPersistence, Long> {
}
