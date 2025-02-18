package fiap.logistics.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class DirectionsDomain {
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
    // start_address e end_address
    // Os nomes dessas variaveies dessa forma
    // sao necessarios porque fazem parte do paylod do google api directions
        private String start_address;
        private String end_address;
    }

    @Data
    public static class TextValue {
        private String text;
        private int value;

        public TextValue(String text, int value) {
            this.text = text;
            this.value = value;
        }

    }
}
