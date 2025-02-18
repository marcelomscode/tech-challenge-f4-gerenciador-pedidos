package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.infrastructure.persistence.RemessaPersistence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RemessaJpaRepository extends JpaRepository<RemessaPersistence, Long> {

    List<RemessaPersistence> findByStatusRemessa(int statusRemessa);

    List<RemessaPersistence> findByIdRemessa(String idRemessa);


}
