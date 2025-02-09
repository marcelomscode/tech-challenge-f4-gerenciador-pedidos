package fiap.logistics.deliveryorder.entities.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryInfo {
    private String address;
    private String deliveryTime;
}