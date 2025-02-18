package fiap.logistics.infrastructure.repository;

import fiap.logistics.domain.enums.EstadoPedido;
import fiap.logistics.domain.exception.DatabaseException;
import fiap.logistics.domain.exception.PedidoException;
import fiap.logistics.domain.repository.PedidoRepository;
import fiap.logistics.infrastructure.persistence.OrderPersistence;
import fiap.logistics.infrastructure.repository.jpa.PedidoJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ServerErrorException;

import java.util.List;

@Slf4j
@Repository
public class PedidoRepositoryImpl implements PedidoRepository {

    private final PedidoJpaRepository pedidoJpaRepository;
    private static final String PEDIDO_NAO_ENCONTRADO = "Pedido nao encontrado com o id: ";
    private static final String STATUS_DO_PEDIDO_ALTERADO_COM_SUCESSO = "Status do pedido alterado com sucesso.";
    private static final String ERRO_AO_ALTERAR_STATUS_DO_PEDIDO = "Erro ao alterar status do pedido: {} ";
    private static final String ERRO_AO_BUSCAR_PEDIDO_COM_ID = "Ocorreu um erro ao buscar o pedido com o id: ";
    private static final String ERRO_AO_BUSCAR_PEDIDO_COM_NUMERO_PEDIDO = "Erro ao alterar status do pedido: ";


    public PedidoRepositoryImpl(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public void save(OrderPersistence pedido) {
        pedidoJpaRepository.save(pedido);
    }

    @Override
    public void atualizaStatusPedidoPorId(Long idPedido, Integer status) {

        try {
            log.info("Alterando status do pedido por id: {}", idPedido + " para " + status);
            var pedido = findById(idPedido);
            pedido.setStatus(EstadoPedido.EM_PREPARACAO_PARA_ENTREGA.getId());
            pedidoJpaRepository.save(pedido);
            log.info(STATUS_DO_PEDIDO_ALTERADO_COM_SUCESSO);
        } catch (PedidoException e) {
            log.error(ERRO_AO_ALTERAR_STATUS_DO_PEDIDO, e.getMessage());
            throw new PedidoException(ERRO_AO_BUSCAR_PEDIDO_COM_NUMERO_PEDIDO + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void atualizaStatusPedidoPorNumeroPedido(String idNumeroPedido, Integer status) {

        try {
            log.info("Alterando status do pedido: {}", idNumeroPedido + " para " + status);
            var pedido = pedidoJpaRepository.findByNumeroPedido(idNumeroPedido);
            pedido.setStatus(EstadoPedido.EM_PREPARACAO_PARA_ENTREGA.getId());
            pedidoJpaRepository.save(pedido);

            log.info(STATUS_DO_PEDIDO_ALTERADO_COM_SUCESSO);
        } catch (PedidoException e) {
            log.error(ERRO_AO_ALTERAR_STATUS_DO_PEDIDO, e.getMessage());
            throw new PedidoException(ERRO_AO_BUSCAR_PEDIDO_COM_NUMERO_PEDIDO + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public OrderPersistence findByNumeroPedido(String idPedido) {

        try {
            var pedido = pedidoJpaRepository.getByNumeroPedido(idPedido);
            if (pedido == null) {
                throw new EntityNotFoundException(PEDIDO_NAO_ENCONTRADO + idPedido);
            }
            return pedidoJpaRepository.getByNumeroPedido(idPedido);
        } catch (ServerErrorException e) {
            throw new DatabaseException(ERRO_AO_BUSCAR_PEDIDO_COM_ID + idPedido, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<OrderPersistence> findAllByNumeroPedido(List<String> ids) {
        return pedidoJpaRepository.findAllByNumeroPedido(ids);
    }

    @Override
    public OrderPersistence findById(Long id) {
        try {
            return pedidoJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(PEDIDO_NAO_ENCONTRADO + id));
        } catch (ServerErrorException e) {
            throw new DatabaseException(ERRO_AO_BUSCAR_PEDIDO_COM_ID + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
