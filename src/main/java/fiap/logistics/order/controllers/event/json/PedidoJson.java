package fiap.logistics.order.controllers.event.json;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoJson {

    private String orderNumber;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String estimatedDeliveryDate;
    private int status;

}
