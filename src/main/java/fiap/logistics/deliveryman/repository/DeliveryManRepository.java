package fiap.logistics.deliveryman.repository;

import fiap.logistics.deliveryman.entitypersistence.DeliveryManPersistence;

import java.util.Collection;

public interface DeliveryManRepository {

    void save(DeliveryManPersistence deliveryManDomain);

    DeliveryManPersistence findById(Long id);

    Collection<DeliveryManPersistence> findAll();
}
