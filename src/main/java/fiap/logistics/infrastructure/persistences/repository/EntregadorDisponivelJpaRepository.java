package fiap.logistics.infrastructure.persistences.repository;

import fiap.logistics.domain.model.entrega.EntregadorDisponivel;
import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntregadorDisponivelJpaRepository extends JpaRepository<EntregadorDisponivel, Long> {


    List<EntregadorDisponivel> findByStatusEntregador(int statusEntregador);
    EntregadorDisponivel findByIdEntregador(DeliveryManPersistence idEntregador);

}
