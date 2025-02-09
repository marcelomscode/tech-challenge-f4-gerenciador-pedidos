package fiap.logistics.order.repositories;

import fiap.logistics.order.dto.enums.EstadoPedido;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.repositories.db.PedidoJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository pedidoJpaRepository;

    public PedidoRepositoryImpl(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public void save(Order pedido) {
        pedidoJpaRepository.save(pedido);
    }

    @Override
    public void atualizaStatusPedido(Long idPedido, Integer status) {

        try {
            log.info("Alterando status do pedido: {}", idPedido + " para " + status);
            var pedido = pedidoJpaRepository.findById(idPedido).orElseThrow();
            pedido.setStatus(EstadoPedido.EM_PREPARACAO_PARA_ENTREGA.getId());
            pedidoJpaRepository.save(pedido);

            log.info("Status do pedido alterado com sucesso.");
        } catch (Exception e) {
            //TODO - Implementar tratamento de erro
            e.printStackTrace();
        }

    }
}
