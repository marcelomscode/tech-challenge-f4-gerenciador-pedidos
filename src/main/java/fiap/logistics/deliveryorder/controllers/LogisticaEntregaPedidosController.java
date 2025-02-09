package fiap.logistics.deliveryorder.controllers;

import fiap.logistics.deliveryorder.repositories.remessapedidosentrega.PreparaRemessaDePedidosRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component

public class LogisticaEntregaPedidosController {

    private final PreparaRemessaDePedidosRepositoryImpl remessaPedidoEntregaRepositoryImpl;

    public LogisticaEntregaPedidosController(PreparaRemessaDePedidosRepositoryImpl remessaPedidoEntregaRepositoryImpl) {
        this.remessaPedidoEntregaRepositoryImpl = remessaPedidoEntregaRepositoryImpl;
    }

// @Scheduled(fixedRate = 10000)
    public void mudaStatusPedidosParaEntrega() {
        log.info("Criando Remessa de pedidos que devem ser entregues hoje: {}", UUID.randomUUID());
        remessaPedidoEntregaRepositoryImpl.preparaRemessaPedidoParaEntregar(LocalDate.now());
    }

//    //   @Scheduled(fixedRate = 20000)
//    public void processarEntrega() {
//        log.info("Processando entrega de pedidos: {}", UUID.randomUUID());
//        preparaPedidosParaEntregaUseCase.preparaPedidosParaEntrega();
//    }


}
