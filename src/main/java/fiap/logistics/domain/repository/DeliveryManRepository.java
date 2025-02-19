package fiap.logistics.domain.repository;

import fiap.logistics.infrastructure.persistence.DeliveryManPersistence;

import java.util.Collection;

public interface DeliveryManRepository {

    void save(DeliveryManPersistence deliveryManDomain);

    DeliveryManPersistence findById(Long id);

    Collection<DeliveryManPersistence> findAll();
}
