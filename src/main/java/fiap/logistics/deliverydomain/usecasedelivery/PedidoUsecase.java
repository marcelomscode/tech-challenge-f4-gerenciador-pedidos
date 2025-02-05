package fiap.logistics.deliverydomain.usecasedelivery;

import fiap.logistics.deliverydomain.dto.LocalizacaoDTO;
import fiap.logistics.deliverydomain.gateways.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class PedidoUsecase {

    private PedidoGateway pedidoGateway;
    private BuscaInformacoesCep buscaInformacoesCep;

    public void salvarPedido(PedidoJson pedido) {

        Pedido pedidoSalvo = new Pedido();
        pedidoSalvo.setId(pedido.getId());
        pedidoSalvo.setDescricao(pedido.getDescricao());
        pedidoSalvo.setCep(pedido.getCep());
        pedidoSalvo.setEndereco(pedido.getEndereco());
        pedidoSalvo.setDataCompra(pedido.getDataCompra());
        pedidoSalvo.setPrevisaoDataEntrega(pedido.getPrevisaoDataEntrega());
        pedidoSalvo.setStatus(pedido.getStatus());

        //Salvar tbm latitude e longitude via API de cepaberto.com
        LocalizacaoDTO geoLocationByCEP = buscarGeoLocationByCEP(pedido.getCep());
        pedidoSalvo.setLatitude(geoLocationByCEP.getLatitude());
        pedidoSalvo.setLongitude(geoLocationByCEP.getLongitude());
        pedidoSalvo.setBairro(geoLocationByCEP.getBairro());
        pedidoSalvo.setCidade(geoLocationByCEP.getCidade());

        log.info("Salvando pedido: {}", pedido);
        pedidoGateway.save(pedidoSalvo);
    }

    private LocalizacaoDTO buscarGeoLocationByCEP(String cep) {

        var resposta = buscaInformacoesCep.buscaInformacoesCep(cep, "Token token=8669e6f5937cb1e64fa9ce363d5c1bc6");

        //TODO: Tratar erro de resposta nula
        if(resposta == null) {
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

}
