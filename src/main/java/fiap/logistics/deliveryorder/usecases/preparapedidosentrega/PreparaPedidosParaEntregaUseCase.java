package fiap.logistics.deliveryorder.usecases.preparapedidosentrega;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PreparaPedidosParaEntregaUseCase {

    public void preparaPedidosParaEntrega() {
        log.info("Preparando pedidos para entrega");
        log.info("Verificando se tem remessas para entregar, status 'Pendente' ou 'em preparação para entrega'");
        log.info("Mudar status do pedido para 'em preparação para entrega'");
        log.info("Buscando entregador disponível");
        log.info("Buscando remessa para entrega");
        log.info("Atribuindo entregador para remessa");
        log.info("Mudar status da remessa para 'em rota de entrega'");
        log.info("Mudando status do pedido para 'saiu para entrega'");
    }

}
