package fiap.logistics.order.usecases;

import fiap.logistics.order.controllers.event.json.PedidoJson;
import fiap.logistics.order.dto.enums.EstadoPedido;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.gateways.BuscaInformacoesCep;
import fiap.logistics.order.repositories.PedidoRepository;
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
    private BuscaInformacoesCep buscaInformacoesCep;

    public void salvarPedido(PedidoJson pedido) {

        Order pedidoSalvo = new Order(
                Long.parseLong(pedido.getOrderNumber()), pedido.getAddress(),
                Integer.parseInt(pedido.getHouseNumber()), pedido.getPostalCode(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(pedido.getEstimatedDeliveryDate(), LocalDate::from),
                EstadoPedido.PENDENTE.getId());
        log.info("Salvando pedido número: {}", pedido.getOrderNumber());
        pedidoRepository.save(pedidoSalvo);
    }

    public void atualizaStatusPedido(Long idPedido, Integer status) {
        log.info("Alterando status do pedido: {}", idPedido + " para " + status);
        pedidoRepository.atualizaStatusPedido(idPedido, status);
        log.info("Status do pedido alterado com sucesso.");
    }

//    private LocalizacaoDTO buscarGeoLocationByCEP(String cep) {
//
//        var resposta = buscaInformacoesCep.buscaInformacoesCep(cep, "Token token=8669e6f5937cb1e64fa9ce363d5c1bc6");
//
//        //TODO: Tratar erro de resposta nula
//        if (resposta == null) {
//            return null;
//        }
//
//        return LocalizacaoDTO
//                .builder()
//                .latitude(resposta.getLatitude())
//                .longitude(resposta.getLongitude())
//                .bairro(resposta.getBairro())
//                .cidade(resposta.getCidade().getNome())
//                .build();
//    }

    public Order findByNumeroPedido(Long id) {
        return pedidoRepository.findByNumeroPedido(id);
    }

    public List<Order> findAllByNumeroPedido(List<Long> ids) {
        return pedidoRepository.findAllByNumeroPedido(ids);
    }
}
