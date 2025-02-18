package fiap.logistics.infrastructure.repository;

import fiap.logistics.application.usecases.entregadores.EntregadorDisponivelUseCase;
import fiap.logistics.domain.enums.StatusEntregador;
import fiap.logistics.domain.repository.AtualizarStatusEntregadorRepository;
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
