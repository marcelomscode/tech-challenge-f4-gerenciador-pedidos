package fiap.logistics.infrastructure.repository;

import fiap.logistics.domain.repository.DeliveryManRepository;
import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;
import fiap.logistics.infrastructure.repository.jpa.DeliveryManJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryManRepositoryImpl implements DeliveryManRepository {

    private final DeliveryManJpaRepository repository;

    public DeliveryManRepositoryImpl(DeliveryManJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(DeliveryManPersistence deliveryManPersistence) {
        repository.save(deliveryManPersistence);
    }

    public DeliveryManPersistence findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<DeliveryManPersistence> findAll() {
        return repository.findAll();
    }

}
