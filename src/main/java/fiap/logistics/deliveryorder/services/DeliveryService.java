package fiap.logistics.deliveryorder.services;

import fiap.logistics.deliveryorder.entities.domain.DeliveryInfo;
import fiap.logistics.deliveryorder.entities.domain.DirectionsResponse;
import fiap.logistics.deliveryorder.gateway.GoogleMapsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DeliveryService {

    private final GoogleMapsClient googleMapsClient;

    public DeliveryService(GoogleMapsClient googleMapsClient) {
        this.googleMapsClient = googleMapsClient;
    }

    @Value("${google.maps.api.key}")
    private String apiKey;

    public List<DeliveryInfo> calculateDeliveryRoute(String originCep, List<String> waypointCeps) {
        log.info("Calculando rota de entrega. Origem: {}, Waypoints: {}", originCep, waypointCeps);

        String waypointsFormatted = "optimize:true|" + String.join("|", waypointCeps);
        log.debug("Waypoints formatados: {}", waypointsFormatted);

        DirectionsResponse response = googleMapsClient.getDirections(
                originCep, originCep, waypointsFormatted, "driving", apiKey
        );
        log.debug("Resposta da API Directions: {}", response);

        if (!"OK".equals(response.getStatus())) {
            log.error("Erro na API Directions: {}", response.getStatus());
            throw new RuntimeException("Erro ao calcular a rota: " + response.getStatus());
        }

        if (response.getRoutes() == null || response.getRoutes().isEmpty()) {
            log.error("Nenhuma rota encontrada na resposta.");
            throw new RuntimeException("Nenhuma rota encontrada.");
        }

        DirectionsResponse.Route route = response.getRoutes().get(0);
        log.debug("Rota encontrada: {}", route);

        if (route.getLegs() == null || route.getLegs().isEmpty()) {
            log.error("Nenhum trecho de rota encontrado.");
            throw new RuntimeException("Nenhum trecho de rota encontrado.");
        }

        List<Integer> waypointOrder = route.getWaypointOrder();
        if (waypointOrder == null || waypointOrder.isEmpty()) {
            log.info("Nenhuma ordem de waypoints otimizada encontrada. Usando ordem original.");
            waypointOrder = IntStream.range(0, route.getLegs().size())
                    .boxed()
                    .collect(Collectors.toList());
        }

        return waypointOrder.stream()
                .map(index -> {
                    DirectionsResponse.Leg leg = route.getLegs().get(index);
                    return new DeliveryInfo(
                            leg.getEnd_address(),
                            leg.getDuration().getText()
                    );
                })
                .collect(Collectors.toList());
    }
}
