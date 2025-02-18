package fiap.logistics.domain.repository;

import fiap.logistics.domain.enums.StatusRemessa;
import fiap.logistics.infrastructure.persistence.RemessaPersistence;
import fiap.logistics.domain.model.remessa.RemessaDomain;

import java.util.List;

public interface RemessaRepository {

    List<RemessaDomain> buscarRemessasPorStatus(StatusRemessa status);

    List<RemessaPersistence> buscarRemessasPorIdRemessa(String idRemessa);

}
