package fiap.logistics.domain.ports;

import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;
import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.domain.model.remessa.RemessaDomain;

import java.util.List;

public interface RemessaRepository {

    List<RemessaDomain> buscarRemessasPorStatus(StatusRemessa status);

    List<RemessaPersistence> buscarRemessasPorIdRemessa(String idRemessa);

}
