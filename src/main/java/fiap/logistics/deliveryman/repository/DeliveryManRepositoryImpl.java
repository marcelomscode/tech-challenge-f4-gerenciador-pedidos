package fiap.logistics.deliveryman.repository;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DeliveryManRepositoryImpl implements ManRepository {

    private final ManJpaRepository repository;

    public DeliveryManRepositoryImpl(ManJpaRepository repository) {
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
