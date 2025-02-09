package fiap.logistics.deliveryorder.entities.domain;

import lombok.Data;

import java.util.List;

@Data
public class DirectionsResponse {
    private List<Route> routes;
    private String status;

    @Data
    public static class Route {
        private List<Leg> legs;
        private List<Integer> waypointOrder;
    }

    @Data
    public static class Leg {
        private TextValue distance;
        private TextValue duration;
        private String start_address;
        private String end_address;
    }

    @Data
    public static class TextValue {
        private String text;
        private int value;
    }
}
