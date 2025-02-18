package fiap.logistics.application.usecases.pedidos;

import fiap.logistics.application.dto.PedidoJsonDTO;
import fiap.logistics.domain.repository.PedidoRepository;
import fiap.logistics.infrastructure.persistence.OrderPersistence;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoUsecase {

    private PedidoRepository pedidoRepository;

    public void salvarPedido(PedidoJsonDTO pedido) {

        OrderPersistence pedidoSalvo = new OrderPersistence(
                pedido.getOrderNumber(),
                pedido.getAddress(),
                Integer.parseInt(pedido.getHouseNumber()),
                pedido.getPostalCode(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(pedido.getEstimatedDeliveryDate(), LocalDate::from),
                pedido.getStatus());
        log.info("Salvando pedido número: {}", pedido.getOrderNumber());
        pedidoRepository.save(pedidoSalvo);
    }

    public void atualizaStatusPedidoPorNumeroPedido(String idPedido, Integer status) {
        log.info("Alterando status do pedido: {}", idPedido + " para " + status);
        pedidoRepository.atualizaStatusPedidoPorNumeroPedido(idPedido, status);
        log.info("Status do pedido alterado com sucesso.");
    }

    public OrderPersistence findByNumeroPedido(String id) {
        return pedidoRepository.findByNumeroPedido(id);
    }

    public List<OrderPersistence> findAllByNumeroPedido(List<String> ids) {
        return pedidoRepository.findAllByNumeroPedido(ids);
    }
}
