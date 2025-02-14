package fiap.logistics.order.repositories;

import fiap.logistics.configuration.DatabaseException;
import fiap.logistics.deliveryman.exceptions.DeliveryManException;
import fiap.logistics.order.dto.enums.EstadoPedido;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.repositories.db.PedidoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ServerErrorException;

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

    @Override
    public Order findByNumeroPedido(Long idPedido) {

        try {
            var pedido = pedidoJpaRepository.getByNumeroPedido(idPedido);
            if (pedido == null) {
                throw new EntityNotFoundException("Pedido nao encontrado com o id: " + idPedido);
            }
            return pedidoJpaRepository.getByNumeroPedido(idPedido);
        } catch (ServerErrorException e) {
            throw new DatabaseException("Ocorreu um erro ao buscar o pedido com o id: " + idPedido, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
