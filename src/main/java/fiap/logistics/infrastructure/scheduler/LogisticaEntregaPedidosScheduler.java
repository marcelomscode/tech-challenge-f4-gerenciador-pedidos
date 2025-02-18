package fiap.logistics.infrastructure.scheduler;

import fiap.logistics.application.usecases.remessas.PreparaRemessaDePedidosUseCase;
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

    private final PreparaRemessaDePedidosUseCase preparaRemessaDePedidosUseCase;

    @Scheduled(fixedRate = 600000) // Processa de 10 em 10 minutos
    public void preparaRemessaPedidoParaEntregar() {
        log.info("Criando Remessa de pedidos que devem ser entregues hoje: {}", UUID.randomUUID());
        preparaRemessaDePedidosUseCase.preparaRemessaPedidoParaEntregar(LocalDate.now());
    }

     //   @Scheduled(fixedRate = 20000)
//    public void processarEntrega() {
//        log.info("Processando entrega de pedidos: {}", UUID.randomUUID());
//        preparaPedidosParaEntregaUseCase.preparaPedidosParaEntrega();
//    }

}

