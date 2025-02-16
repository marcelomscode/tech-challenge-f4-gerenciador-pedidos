package fiap.logistics.infrastructure.scheduler;

import fiap.logistics.application.usecases.remessas.PreparaRemessaDePedidosUseCase;
import fiap.logistics.deliveryorder.repositories.remessapedidosentrega.PreparaRemessaDePedidosRepositoryImplRefatorar;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class LogisticaEntregaPedidosScheduler {

    private final PreparaRemessaDePedidosRepositoryImplRefatorar remessaPedidoEntregaRepositoryImpl;
    private final PreparaRemessaDePedidosUseCase preparaRemessaDePedidosUseCase;

//  @Scheduled(fixedRate = 10000)
    public void preparaRemessaPedidoParaEntregar() {
        log.info("Criando Remessa de pedidos que devem ser entregues hoje: {}", UUID.randomUUID());
        preparaRemessaDePedidosUseCase.preparaRemessaPedidoParaEntregar(LocalDate.now());
    }

//    //   @Scheduled(fixedRate = 20000)
//    public void processarEntrega() {
//        log.info("Processando entrega de pedidos: {}", UUID.randomUUID());
//        preparaPedidosParaEntregaUseCase.preparaPedidosParaEntrega();
//    }
}

