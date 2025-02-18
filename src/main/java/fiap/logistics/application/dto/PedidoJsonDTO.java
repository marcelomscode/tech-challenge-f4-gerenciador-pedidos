package fiap.logistics.application.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PedidoJsonDTO {

    private String orderNumber;
    private String address;
    private String houseNumber;
    private String postalCode;
    private String estimatedDeliveryDate;
    private int status;

}
