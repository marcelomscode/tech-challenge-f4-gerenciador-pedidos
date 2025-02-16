package fiap.logistics.entregador.repository;

import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;

import java.util.Collection;

public interface DeliveryManRepository {

    void save(DeliveryManPersistence deliveryManDomain);

    DeliveryManPersistence findById(Long id);

    Collection<DeliveryManPersistence> findAll();
}
