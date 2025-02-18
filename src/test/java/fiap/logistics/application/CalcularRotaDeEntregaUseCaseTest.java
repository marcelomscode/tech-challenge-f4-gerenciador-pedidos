package fiap.logistics.application;

import fiap.logistics.application.usecases.CalcularRotaDeEntregaUseCase;
import fiap.logistics.domain.exception.CalculoRotaException;
import fiap.logistics.domain.model.DeliveryInfoDomain;
import fiap.logistics.domain.model.DirectionsDomain;
import fiap.logistics.infrastructure.client.GoogleMapsClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalcularRotaDeEntregaUseCaseTest {

    @Mock
    private GoogleMapsClient googleMapsClient;

    @InjectMocks
    private CalcularRotaDeEntregaUseCase calcularRotaDeEntregaUseCase;

    @BeforeEach
    void setup() {
        calcularRotaDeEntregaUseCase = new CalcularRotaDeEntregaUseCase(googleMapsClient);
    }

    @Test
    void deveCalcularRotaComSucesso() {
        // Dados de entrada
        String originCep = "01001-000";
        List<String> waypointCeps = List.of("02002-000", "03003-000");

        // Simulando a resposta da API do Google
        DirectionsDomain.Route route = new DirectionsDomain.Route();
        DirectionsDomain.Leg leg1 = new DirectionsDomain.Leg();
        leg1.setEnd_address("Rua A, 123");
        leg1.setDuration(new DirectionsDomain.TextValue("10 min", 600));

        DirectionsDomain.Leg leg2 = new DirectionsDomain.Leg();
        leg2.setEnd_address("Rua B, 456");
        leg2.setDuration(new DirectionsDomain.TextValue("15 min", 900));

        route.setLegs(List.of(leg1, leg2));
        route.setWaypointOrder(List.of(0, 1));

        DirectionsDomain response = new DirectionsDomain();
        response.setStatus("OK");
        response.setRoutes(List.of(route));

        when(googleMapsClient.getDirections(any(), any(), any(), any(), any()))
                .thenReturn(response);

        // Executando o teste
        List<DeliveryInfoDomain> resultado = calcularRotaDeEntregaUseCase.calculaRotaEntrega(originCep, waypointCeps);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Rua A, 123", resultado.get(0).getAddress());
        assertEquals("10 min", resultado.get(0).getDeliveryTime());
        assertEquals("Rua B, 456", resultado.get(1).getAddress());
        assertEquals("15 min", resultado.get(1).getDeliveryTime());

        // Verifica se o cliente foi chamado corretamente
        verify(googleMapsClient, times(1))
                .getDirections(any(), any(), any(), any(), any());
    }

    @Test
    void deveLancarExcecaoQuandoApiRetornarErro() {
        // Dados de entrada
        String originCep = "01001-000";
        List<String> waypointCeps = List.of("02002-000", "03003-000");

        // Simulando uma resposta de erro da API do Google
        DirectionsDomain response = new DirectionsDomain();
        response.setStatus("REQUEST_DENIED"); // Simulando um erro na API

        when(googleMapsClient.getDirections(any(), any(), any(), any(), any()))
                .thenReturn(response);

        // Executando o teste e validando que a exceção é lançada
        CalculoRotaException thrown = assertThrows(
                CalculoRotaException.class,
                () -> calcularRotaDeEntregaUseCase.calculaRotaEntrega(originCep, waypointCeps)
        );

        // Verificando se a exceção contém a mensagem esperada
        assertEquals("Erro ao calcular a rota: REQUEST_DENIED", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());

        // Verifica se o cliente foi chamado corretamente
        verify(googleMapsClient, times(1))
                .getDirections(any(), any(), any(), any(), any());
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverRotaEncontrada() {
        // Dados de entrada
        String originCep = "01001-000";
        List<String> waypointCeps = List.of("02002-000", "03003-000");

        // Simulando uma resposta da API sem rotas
        DirectionsDomain response = new DirectionsDomain();
        response.setStatus("OK");
        response.setRoutes(List.of()); // Nenhuma rota

        when(googleMapsClient.getDirections(any(), any(), any(), any(), any()))
                .thenReturn(response);

        // Executando o teste e validando que a exceção é lançada
        CalculoRotaException thrown = assertThrows(
                CalculoRotaException.class,
                () -> calcularRotaDeEntregaUseCase.calculaRotaEntrega(originCep, waypointCeps)
        );

        // Verificações
        assertEquals("Nenhuma rota encontrada.", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());

        verify(googleMapsClient, times(1))
                .getDirections(any(), any(), any(), any(), any());
    }

    @Test
    void deveLancarExcecaoQuandoRotaNaoTemTrechos() {
        // Dados de entrada
        String originCep = "01001-000";
        List<String> waypointCeps = List.of("02002-000", "03003-000");

        // Simulando uma resposta da API com rota, mas sem legs
        DirectionsDomain.Route route = new DirectionsDomain.Route();
        route.setLegs(List.of()); // Nenhum trecho de rota encontrado

        DirectionsDomain response = new DirectionsDomain();
        response.setStatus("OK");
        response.setRoutes(List.of(route));

        when(googleMapsClient.getDirections(any(), any(), any(), any(), any()))
                .thenReturn(response);

        // Executando o teste e validando que a exceção é lançada
        CalculoRotaException thrown = assertThrows(
                CalculoRotaException.class,
                () -> calcularRotaDeEntregaUseCase.calculaRotaEntrega(originCep, waypointCeps)
        );

        // Verificações
        assertEquals("Nenhum trecho de rota encontrado.", thrown.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getHttpStatus());

        verify(googleMapsClient, times(1))
                .getDirections(any(), any(), any(), any(), any());
    }

    @Test
    void deveUsarOrdemOriginalQuandoWaypointOrderNaoForDefinida() {
        // Dados de entrada
        String originCep = "01001-000";
        List<String> waypointCeps = List.of("02002-000", "03003-000");

        // Simulando uma resposta da API sem waypointOrder
        DirectionsDomain.Route route = new DirectionsDomain.Route();

        DirectionsDomain.Leg leg1 = new DirectionsDomain.Leg();
        leg1.setEnd_address("Rua A, 123");
        leg1.setDuration(new DirectionsDomain.TextValue("10 min", 600));

        DirectionsDomain.Leg leg2 = new DirectionsDomain.Leg();
        leg2.setEnd_address("Rua B, 456");
        leg2.setDuration(new DirectionsDomain.TextValue("15 min", 900));

        route.setLegs(List.of(leg1, leg2));
        route.setWaypointOrder(null); // Nenhuma ordem otimizada

        DirectionsDomain response = new DirectionsDomain();
        response.setStatus("OK");
        response.setRoutes(List.of(route));

        when(googleMapsClient.getDirections(any(), any(), any(), any(), any()))
                .thenReturn(response);

        // Executando o teste
        List<DeliveryInfoDomain> resultado = calcularRotaDeEntregaUseCase.calculaRotaEntrega(originCep, waypointCeps);

        // Verificações
        assertNotNull(resultado);
        assertEquals(2, resultado.size());

        assertEquals("Rua A, 123", resultado.get(0).getAddress());
        assertEquals("10 min", resultado.get(0).getDeliveryTime());

        assertEquals("Rua B, 456", resultado.get(1).getAddress());
        assertEquals("15 min", resultado.get(1).getDeliveryTime());

        verify(googleMapsClient, times(1))
                .getDirections(any(), any(), any(), any(), any());
    }


}



