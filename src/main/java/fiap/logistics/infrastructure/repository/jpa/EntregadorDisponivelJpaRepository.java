package fiap.logistics.infrastructure.repository.jpa;

import fiap.logistics.infrastructure.persistence.EntregadorDisponivelPersistence;
import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregadorDisponivelJpaRepository extends JpaRepository<EntregadorDisponivelPersistence, Long> {


    List<EntregadorDisponivelPersistence> findByStatusEntregador(int statusEntregador);
    EntregadorDisponivelPersistence findByIdEntregador(DeliveryManPersistence idEntregador);

}
