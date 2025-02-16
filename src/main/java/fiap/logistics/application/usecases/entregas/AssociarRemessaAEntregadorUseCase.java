package fiap.logistics.application.usecases.entregas;

import fiap.logistics.deliveryorder.dto.enums.StatusRemessa;
import fiap.logistics.deliveryorder.entities.persistences.RemessaPersistence;
import fiap.logistics.domain.model.entrega.EntregadorDomain;
import fiap.logistics.domain.model.remessa.RemessaDomain;
import fiap.logistics.domain.model.entrega.StatusEntregador;
import fiap.logistics.domain.ports.*;
import fiap.logistics.order.usecases.PedidoUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AssociarRemessaAEntregadorUseCase {

    private final PedidoUsecase pedidoUsecase;
    private final RemessaRepository remessaRepository;
    private final EntregadorDisponivelRepository entregadorDisponivelRepository;
    private final AtualizarStatusRemessaRepository atualizarStatusRemessaRepository;
    private final AssociarRemessaEntregadorRepository associarRemessaEntregadorRepository;
    private final AtualizarStatusEntregadorRepository atualizarStatusEntregadorRepository;

    public void executar() {
        log.info("-------------- INICIO PROCESSO ASSOCIAR REMESSA A ENTREGADOR --------------");

        try {
            List<EntregadorDomain> entregadoresDisponiveis = entregadorDisponivelRepository.buscarEntregadoresDisponiveis();
            if (entregadoresDisponiveis.isEmpty()) {
                log.info("Nenhum entregador disponível para processar.");
                throw new Exception("Nenhum entregador disponível para processar.");
            }

            List<RemessaDomain> remessasPendentes = remessaRepository.buscarRemessasPorStatus(StatusRemessa.AGUARDANDO_ENTREGA);

            if (!entregadoresDisponiveis.isEmpty() && !remessasPendentes.isEmpty()) {
                associaRemessasEntregadores(entregadoresDisponiveis, remessasPendentes);
            } else {
                log.info("Nenhum entregador disponível ou remessa pendente para processar.");
            }
        } catch (Exception e) {
            log.error("Erro ao associar remessas a entregadores: {}", e.getMessage(), e);
        }
        log.info("-------------- FIM PROCESSO ASSOCIAR REMESSA A ENTREGADOR --------------");
    }

    private void associaRemessasEntregadores(List<EntregadorDomain> entregadoresDisponiveis, List<RemessaDomain> remessasPendentes) {
        int quantidadeEntregadores = entregadoresDisponiveis.size();
        int quantidadeRemessas = remessasPendentes.size();

        // Itera sobre a menor quantidade entre entregadores e remessas
        atualizaRemessasEntregadores(quantidadeEntregadores, quantidadeRemessas, entregadoresDisponiveis, remessasPendentes);

        if (quantidadeRemessas > quantidadeEntregadores) {
            int remessasRestantes = quantidadeRemessas - quantidadeEntregadores;
            log.info("{} remessas não puderam ser associadas a entregadores devido à falta de disponibilidade. " +
                    "Elas serão processadas na próxima execução.", remessasRestantes);
        }
    }

    private void atualizaRemessasEntregadores(int quantidadeEntregadores, int quantidadeRemessas, List<EntregadorDomain> entregadoresDisponiveis, List<RemessaDomain> remessasPendentes) {
        for (int i = 0; i < Math.min(quantidadeEntregadores, quantidadeRemessas); i++) {
            EntregadorDomain entregador = entregadoresDisponiveis.get(i);
            RemessaDomain remessa = remessasPendentes.get(i);

            associarRemessaEntregadorRepository
                    .associarRemessaAEntregador(remessa.getIdRemessa(), entregador.getIdEntregador());

            atualizarStatusRemessaRepository
                    .atualizarStatusRemessa(remessa.getIdRemessa(), StatusRemessa.EM_ROTA_DE_ENTREGA.getId());

            atualizarStatusEntregadorRepository
                    .atualizarStatusEntregador(entregador.getIdEntregador(), StatusEntregador.INDISPONIVEL);

            //atualizar status do pedido
            //Pela remessa, pegar o id do pedido e atualizar o status do pedido para "EM_ROTA_DE_ENTREGA"
            atualizaStatusPedidoPorRemessa(remessa.getIdRemessa(), StatusRemessa.EM_ROTA_DE_ENTREGA.getId());


            log.info("Remessa {} associada ao entregador {}", remessa.getIdRemessa(), entregador.getIdEntregador());
        }
    }

    public void atualizaStatusPedidoPorRemessa(Long idRemessa, Integer status) {

        //Pegar a lista de pedidos por remessa
        //for na lista de remessa e pegar todos os NumerosPedidos
        var remessas = remessaRepository.buscarRemessasPorIdRemessa(String.valueOf(idRemessa));

        List<Long> idNumeroPedido = remessas.stream().map(RemessaPersistence::getNumeroPedido).toList();

        //Passar todos os numeros de pedidos para o PedidoRepository
        var pedidos = pedidoUsecase.findAllByNumeroPedido(idNumeroPedido);

        for (var pedido : pedidos) {
            pedidoUsecase.atualizaStatusPedido(pedido.getNumeroPedido(), status);
        }
    }

}
