package fiap.logistics.order.usecases;

import fiap.logistics.order.controllers.event.json.PedidoJson;
import fiap.logistics.order.dto.LocalizacaoDTO;
import fiap.logistics.order.dto.enums.EstadoPedido;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.gateways.BuscaInformacoesCep;
import fiap.logistics.order.repositories.PedidoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoUsecase {

    private PedidoRepository pedidoGateway;
    private BuscaInformacoesCep buscaInformacoesCep;

    public Order salvarPedido(PedidoJson pedido) {

        Order pedidoSalvo = new Order();
        pedidoSalvo.setNumeroPedido(pedido.getNumeroPedido());
        pedidoSalvo.setCep(pedido.getCep());
        pedidoSalvo.setEndereco(pedido.getEndereco());
        pedidoSalvo.setPrevisaoDataEntrega(DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(pedido.getPrevisaoDataEntrega(), LocalDate::from));
        pedidoSalvo.setStatus(EstadoPedido.PENDENTE.getId());

        log.info("Salvando pedido número: {}", pedido.getNumeroPedido());
        pedidoGateway.save(pedidoSalvo);

        return pedidoSalvo;
    }

    private LocalizacaoDTO buscarGeoLocationByCEP(String cep) {

        var resposta = buscaInformacoesCep.buscaInformacoesCep(cep, "Token token=8669e6f5937cb1e64fa9ce363d5c1bc6");

        //TODO: Tratar erro de resposta nula
        if (resposta == null) {
            return null;
        }

        return LocalizacaoDTO
                .builder()
                .latitude(resposta.getLatitude())
                .longitude(resposta.getLongitude())
                .bairro(resposta.getBairro())
                .cidade(resposta.getCidade().getNome())
                .build();
    }

    public Order findByNumeroPedido(Long id) {
        return pedidoGateway.findByNumeroPedido(id);
    }

}
