package fiap.logistics.infrastructure.client;

import fiap.logistics.domain.model.DirectionsDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "googleMapsClient", url = "https://maps.googleapis.com/maps/api/directions")
public interface GoogleMapsClient {

    @GetMapping("/json")
    DirectionsDomain getDirections(
            @RequestParam("origin") String origin,
            @RequestParam("destination") String destination,
            @RequestParam("waypoints") String waypoints,
            @RequestParam("mode") String mode,
            @RequestParam("key") String apiKey
    );
}