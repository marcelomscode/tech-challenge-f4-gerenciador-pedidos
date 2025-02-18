package fiap.logistics.application.usecases;

import fiap.logistics.domain.exception.CalculoRotaException;
import fiap.logistics.domain.model.DeliveryInfoDomain;
import fiap.logistics.domain.model.DirectionsDomain;
import fiap.logistics.infrastructure.client.GoogleMapsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
public class CalcularRotaDeEntregaUseCase {

    private final GoogleMapsClient googleMapsClient;

    public CalcularRotaDeEntregaUseCase(GoogleMapsClient googleMapsClient) {
        this.googleMapsClient = googleMapsClient;
    }

    @Value("${google.maps.api.key}")
    private String apiKey;

    public List<DeliveryInfoDomain> calculaRotaEntrega(String originCep, List<String> waypointCeps) {
        log.info("Calculando rota de entrega. Origem: {}, Waypoints: {}", originCep, waypointCeps);

        String waypointsFormatted = "optimize:true|" + String.join("|", waypointCeps);
        log.debug("Waypoints formatados: {}", waypointsFormatted);

        DirectionsDomain response = googleMapsClient.getDirections(
                originCep, originCep, waypointsFormatted, "driving", apiKey
        );
        log.debug("Resposta da API Directions: {}", response);

        if (!"OK".equals(response.getStatus())) {
            log.error("Erro na API Directions: {}", response.getStatus());
            throw new CalculoRotaException("Erro ao calcular a rota: " + response.getStatus(), HttpStatus.BAD_REQUEST);
        }

        if (response.getRoutes() == null || response.getRoutes().isEmpty()) {
            log.error("Nenhuma rota encontrada na resposta.");
            throw new CalculoRotaException("Nenhuma rota encontrada.", HttpStatus.BAD_REQUEST);
        }

        DirectionsDomain.Route route = response.getRoutes().get(0);
        log.debug("Rota encontrada: {}", route);

        if (route.getLegs() == null || route.getLegs().isEmpty()) {
            log.error("Nenhum trecho de rota encontrado.");
            throw new CalculoRotaException("Nenhum trecho de rota encontrado.", HttpStatus.BAD_REQUEST);
        }

        List<Integer> waypointOrder = route.getWaypointOrder();
        if (waypointOrder == null || waypointOrder.isEmpty()) {
            log.info("Nenhuma ordem de waypoints otimizada encontrada. Usando ordem original.");
            waypointOrder = IntStream.range(0, route.getLegs().size())
                    .boxed()
                    .toList();
        }

        return waypointOrder.stream()
                .map(index -> {
                    DirectionsDomain.Leg leg = route.getLegs().get(index);
                    return new DeliveryInfoDomain(
                            leg.getEnd_address(),
                            leg.getDuration().getText()
                    );
                })
                .toList();
    }
}
