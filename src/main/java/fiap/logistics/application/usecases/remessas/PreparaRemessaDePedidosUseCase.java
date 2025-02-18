package fiap.logistics.application.usecases.remessas;

import fiap.logistics.domain.enums.StatusRemessa;
import fiap.logistics.domain.exception.RemessaException;
import fiap.logistics.domain.model.DeliveryInfoDomain;
import fiap.logistics.domain.model.RemessaDomain;
import fiap.logistics.infrastructure.persistence.RemessaPersistence;
import fiap.logistics.infrastructure.repository.jpa.RemessaJpaRepository;
import fiap.logistics.infrastructure.repository.jpa.RemessaPedidosEntregaJpaRepository;
import fiap.logistics.application.usecases.CalcularRotaDeEntregaUseCase;
import fiap.logistics.infrastructure.persistence.OrderPersistence;
import fiap.logistics.domain.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PreparaRemessaDePedidosUseCase {

    private final RemessaPedidosEntregaJpaRepository repository;
    private final RemessaJpaRepository remessaJpaRepository;
    private final CalcularRotaDeEntregaUseCase deliveryService;
    private final PedidoRepository pedidoRepository;

    @Value("${cep.origem.entrega}")
    private String cepOrigemEntrega;

    public void preparaRemessaPedidoParaEntregar(LocalDate dataParaEntrega) {

        try {
            log.info("--------------------------------------------------");
            log.info("Separando todos pedidos para entrega de hoje: {}", dataParaEntrega);
            var pedidos = getOrdersToDelivery();

            log.info("Criando remessas para entrega.");
            salvaListaRemessa(pedidos);
        } catch (RemessaException e) {
            log.error("Erro ao criar remessas: {}", e.getMessage());
            throw new RemessaException("Erro ao criar remessas: "
                    + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        log.info("Remessas criadas com sucesso.");
        log.info("--------------------------------------------------");
    }

    private static String getGeraNumeroRemessa(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }

    private List<OrderPersistence> getOrdersToDelivery() {

        var pedidos = repository.findByDeliveryDate(LocalDate.now());
        if (pedidos.isEmpty()) {
            log.info("Nenhum pedido foi encontrado para entrega.");
            log.info("O sistema irá processar novamente em alguns minutos.");
            throw new RemessaException("Nenhum pedido foi encontrado para entrega.", HttpStatus.NOT_FOUND);
        }
        log.info("Pedidos encontrados: {}", pedidos.size());
        return pedidos;
    }

    public void salvaListaRemessa(List<OrderPersistence> pedidos){

        int tamanhoLote = 20;

        log.info("Gerando numero de remessa");
        String idRemessa = getGeraNumeroRemessa();

        // Dividir a lista de pedidos em lotes de 20
        for (int i = 0; i < pedidos.size(); i += tamanhoLote) {

            List<OrderPersistence> sublista = pedidos.subList(i, Math.min(i + tamanhoLote, pedidos.size()));
            List<String> ceps = sublista.stream().map(OrderPersistence::getCep).toList();

            //pega lista ordenada por entrega do google maps
            log.info("Buscando rota de cada remessa e tempo medio de entrega de cada pedido");
            var listaOrdenada = deliveryService.calculaRotaEntrega(cepOrigemEntrega, ceps);

            for (DeliveryInfoDomain deliveryInfo : listaOrdenada) {
                for (OrderPersistence order : sublista) {
                    if (deliveryInfo.getAddress().contains(order.getCep())) {
                        RemessaDomain remessa = new RemessaDomain(
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
            log.info("INICIO atualiza status do pedido para 'Aguardando Entrega'");
            pedidoRepository.atualizaStatusPedidoPorId(pedidos.get(i).getId(), StatusRemessa.AGUARDANDO_ENTREGA.getId());
            log.info("FIM atualiza status do pedido para 'Aguardando Entrega'");

        }
    }

    private void saveOrdersToShipments(RemessaDomain remessa) {
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
        log.info("Item da remessa salvo com sucesso.");
    }


}
