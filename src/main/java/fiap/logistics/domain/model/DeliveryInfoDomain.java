package fiap.logistics.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeliveryInfoDomain {
    private String address;
    private String deliveryTime;
}