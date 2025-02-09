package fiap.logistics.deliveryorder.usecases.criaremessapedido;

import fiap.logistics.deliveryorder.repositories.remessapedidosentrega.PreparaRemessaDePedidosRepository;
import fiap.logistics.deliveryorder.services.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@AllArgsConstructor
public class CriaRemessaPedidosUseCase {


        private final PreparaRemessaDePedidosRepository preparaPedidoEntregaRepository;
        private final DeliveryService deliveryService;


        public void criaRemessaPedidos() {
            log.info("Criando remessa de pedidos");
            preparaPedidoEntregaRepository.preparaRemessaPedidoParaEntregar(LocalDate.now());
        }



}
