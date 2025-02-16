package fiap.logistics.infrastructure.persistences.adapter;

import fiap.logistics.application.usecases.entregadores.EntregadorDisponivelUseCase;
import fiap.logistics.domain.model.entrega.StatusEntregador;
import fiap.logistics.domain.ports.AtualizarStatusEntregadorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AtualizarStatusEntregadorImpl implements AtualizarStatusEntregadorRepository {

    private final EntregadorDisponivelUseCase entregadorDisponivelUseCase;

    @Override
    public void atualizarStatusEntregador(Long idEntregador, StatusEntregador status) {
        entregadorDisponivelUseCase.mudaStatusEntregador(idEntregador, status);
    }
}
