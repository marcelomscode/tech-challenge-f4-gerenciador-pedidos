package fiap.logistics.application.usecases;

import fiap.logistics.application.usecases.entregas.AssociarRemessaAEntregadorUseCase;
import fiap.logistics.domain.enums.StatusRemessa;
import fiap.logistics.domain.model.EntregadorDomain;
import fiap.logistics.domain.model.remessa.RemessaDomain;
import fiap.logistics.domain.enums.StatusEntregador;
import fiap.logistics.domain.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fiap.logistics.domain.enums.StatusRemessa.AGUARDANDO_ENTREGA;
import static org.mockito.Mockito.*;

class AssociarRemessaAEntregadorUseCaseTest {

    @Mock
    private EntregadorDisponivelRepository entregadorDisponivelRepository;

    @Mock
    private RemessaRepository remessaRepository;

    @Mock
    private AssociarRemessaEntregadorRepository associarRemessaEntregadorRepository;

    @Mock
    private AtualizarStatusRemessaRepository atualizarStatusRemessaRepository;

    @Mock
    private AtualizarStatusEntregadorRepository atualizarStatusEntregadorRepository;

    @InjectMocks
    private AssociarRemessaAEntregadorUseCase associarRemessaAEntregadorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExecutarComEntregadoresERemessasDisponiveis() {

        EntregadorDomain entregador = new EntregadorDomain(1L, 1L  , StatusEntregador.DISPONIVEL.getCodigo());

        RemessaDomain remessa = new RemessaDomain(1L, AGUARDANDO_ENTREGA.getId());

        when(entregadorDisponivelRepository.buscarEntregadoresDisponiveis())
                .thenReturn(List.of(entregador));
        when(remessaRepository.buscarRemessasPorStatus(AGUARDANDO_ENTREGA))
                .thenReturn(List.of(remessa));
        associarRemessaAEntregadorUseCase.executar();

        verify(associarRemessaEntregadorRepository, times(1))
                .associarRemessaAEntregador(remessa.getIdRemessa(), entregador.getIdEntregador());
        verify(atualizarStatusRemessaRepository, times(1))
                .atualizarStatusRemessa(remessa.getIdRemessa(), StatusRemessa.EM_ROTA_DE_ENTREGA.getId());
        verify(atualizarStatusEntregadorRepository, times(1))
                .atualizarStatusEntregador(entregador.getIdEntregador(), StatusEntregador.INDISPONIVEL);
    }

    @Test
    void deveExecutarSemEntregadoresDisponiveis() {
        when(entregadorDisponivelRepository.buscarEntregadoresDisponiveis())
                .thenReturn(Collections.emptyList());
        when(remessaRepository.buscarRemessasPorStatus(AGUARDANDO_ENTREGA))
                .thenReturn(List.of(new RemessaDomain(1L, AGUARDANDO_ENTREGA.getId())));

        associarRemessaAEntregadorUseCase.executar();

        verify(associarRemessaEntregadorRepository, never())
                .associarRemessaAEntregador(anyLong(), anyLong());
        verify(atualizarStatusEntregadorRepository, never())
                .atualizarStatusEntregador(anyLong(), any());
    }

    @Test
    void deveExecutarSemRemessasPendentes() {

        when(entregadorDisponivelRepository.buscarEntregadoresDisponiveis())
                .thenReturn(List.of(new EntregadorDomain(1L, 1L,StatusEntregador.DISPONIVEL.getCodigo())));
        when(remessaRepository.buscarRemessasPorStatus(StatusRemessa.AGUARDANDO_ENTREGA))
                .thenReturn(Collections.emptyList());

        associarRemessaAEntregadorUseCase.executar();

        verify(associarRemessaEntregadorRepository, never())
                .associarRemessaAEntregador(anyLong(), anyLong());

        verify(atualizarStatusEntregadorRepository, never())
                .atualizarStatusEntregador(anyLong(), any());
    }

    @Test
    void deveExecutarComMaisRemessasQueEntregadores() {
        EntregadorDomain entregador = new EntregadorDomain(1L, 1L, StatusEntregador.DISPONIVEL.getCodigo());

        RemessaDomain remessa1 = new RemessaDomain(1L, StatusRemessa.AGUARDANDO_ENTREGA.getId());
        RemessaDomain remessa2 = new RemessaDomain(2L, StatusRemessa.AGUARDANDO_ENTREGA.getId());

        when(entregadorDisponivelRepository.buscarEntregadoresDisponiveis())
                .thenReturn(List.of(entregador));
        when(remessaRepository.buscarRemessasPorStatus(StatusRemessa.AGUARDANDO_ENTREGA))
                .thenReturn(Arrays.asList(remessa1, remessa2));

        associarRemessaAEntregadorUseCase.executar();

        verify(associarRemessaEntregadorRepository, times(1))
                .associarRemessaAEntregador(remessa1.getIdRemessa(), entregador.getIdEntregador());
        verify(atualizarStatusRemessaRepository, times(1))
                .atualizarStatusRemessa(remessa1.getIdRemessa(), StatusRemessa.EM_ROTA_DE_ENTREGA.getId());
        verify(atualizarStatusEntregadorRepository, times(1))
                .atualizarStatusEntregador(entregador.getIdEntregador(), StatusEntregador.INDISPONIVEL);

        verify(associarRemessaEntregadorRepository, never())
                .associarRemessaAEntregador(eq(remessa2.getIdRemessa()), anyLong());
    }
}
