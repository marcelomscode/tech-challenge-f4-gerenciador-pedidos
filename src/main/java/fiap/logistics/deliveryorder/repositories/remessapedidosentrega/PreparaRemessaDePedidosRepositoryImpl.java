package fiap.logistics.deliveryorder.repositories.remessapedidosentrega;

import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;
import fiap.logistics.deliveryorder.entities.domain.DeliveryInfo;
import fiap.logistics.deliveryorder.entities.domain.remessa.Remessa;
import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository.RemessaJpaRepository;
import fiap.logistics.deliveryorder.repositories.db.remessapedidosentregajparepository.RemessaPedidosEntregaJpaRepository;
import fiap.logistics.deliveryorder.services.DeliveryService;
import fiap.logistics.deliveryorder.usecases.preparapedidosentrega.PreparaPedidosParaEntregaUseCase;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.repositories.PedidoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Repository
public class PreparaRemessaDePedidosRepositoryImpl implements PreparaRemessaDePedidosRepository {

    private final RemessaPedidosEntregaJpaRepository repository;
    private final PreparaPedidosParaEntregaUseCase preparaPedidosParaEntregaUseCase;
    private final RemessaJpaRepository remessaJpaRepository;
    private final DeliveryService deliveryService;
    private final PedidoRepository pedidoRepository;



    private static final String CEP_ORIGEM = "08450-120";


    public PreparaRemessaDePedidosRepositoryImpl(RemessaPedidosEntregaJpaRepository repository,
                                                 PreparaPedidosParaEntregaUseCase preparaPedidosParaEntregaUseCase,
                                                 RemessaJpaRepository remessaJpaRepository,
                                                 DeliveryService deliveryService,
                                                 PedidoRepository pedidoRepository) {
        this.repository = repository;
        this.preparaPedidosParaEntregaUseCase = preparaPedidosParaEntregaUseCase;
        this.remessaJpaRepository = remessaJpaRepository;
        this.deliveryService = deliveryService;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public void preparaRemessaPedidoParaEntregar(LocalDate dataParaEntrega) {

        try {
            log.info("--------------------------------------------------");
            log.info("Separando todos pedidos para entrega de hoje: {}", dataParaEntrega);
            var pedidos = getOrdersToDelivery();

            log.info("Criando remessas para entrega.");
            salvaListaRemessa(pedidos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Remessas criadas com sucesso.");
        log.info("--------------------------------------------------");
    }

    private static String getGeraNumeroRemessa() throws InterruptedException {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    private List<Order> getOrdersToDelivery() {

        var pedidos = repository.findByDeliveryDate(LocalDate.now());
        if (pedidos.isEmpty()) {
            log.info("Nenhum pedido foi encontrado para entrega.");
            log.info("O sistema irá processar novamente em alguns minutos.");
        }
        log.info("Pedidos encontrados: {}", pedidos.size());
        return pedidos;
    }

    public void salvaListaRemessa(List<Order> pedidos) throws InterruptedException {


        int tamanhoLote = 20;

        log.info("Gerando numero de remessa");
        String idRemessa = getGeraNumeroRemessa();

        // Dividir a lista de pedidos em lotes de 20
        for (int i = 0; i < pedidos.size(); i += tamanhoLote) {

            List<Order> sublista = pedidos.subList(i, Math.min(i + tamanhoLote, pedidos.size()));
            List<String> ceps = sublista.stream().map(Order::getCep).toList();

            //pega lista ordenada por entrega do google maps
            log.info("Buscando rota de cada remessa e tempo medio de entrega de cada pedido");
            var listaOrdenada = deliveryService.calculateDeliveryRoute(CEP_ORIGEM, ceps);

            for (DeliveryInfo deliveryInfo : listaOrdenada) {
                for (Order order : sublista) {
                    if (deliveryInfo.getAddress().contains(order.getCep())) {
                        Remessa remessa = new Remessa(
                                idRemessa,
                                order.getNumeroPedido(),
                                deliveryInfo.getAddress(),
                                order.getNumeroResidencia(),
                                StatusRemessa.AGUARDANDO_ENTREGA.getId(),
                                deliveryInfo.getDeliveryTime());
                        saveOrdersToShipments(remessa);
                        break;
                    }
                }
            }
            idRemessa = getGeraNumeroRemessa();
            log.info("INICIO atualiza status do pedido para 'Em preparação para entrega'");
            pedidoRepository.atualizaStatusPedido(pedidos.get(i).getId(), StatusRemessa.EM_PREPARACAO_PARA_ENTREGA.getId());
            log.info("FIM atualiza status do pedido para 'Em preparação para entrega'");

        }
    }

    private void saveOrdersToShipments(Remessa remessa) {
        log.info("Salvando item da remessa {}", remessa.getIdRemessa());
        var remessaPersistence = RemessaPersistence
                .builder()
                .numeroPedido(remessa.getNumeroPedido())
                .enderecoEntrega(remessa.getEnderecoEntrega())
                .numeroResidencia(remessa.getNumeroResidencia())
                .statusRemessa(remessa.getStatusRemessa())
                .tempoEntrega(remessa.getTempoEntrega())
                .idRemessa(remessa.getIdRemessa())
                .build();

        remessaJpaRepository.save(remessaPersistence);
    }

}
