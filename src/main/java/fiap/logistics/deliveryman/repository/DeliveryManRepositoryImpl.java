package fiap.logistics.deliveryman.repository;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import fiap.logistics.deliveryman.repository.db.DeliveryJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryManRepositoryImpl implements DeliveryManRepository {

    private final DeliveryJpaRepository repository;

    public DeliveryManRepositoryImpl(DeliveryJpaRepository repository) {
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
