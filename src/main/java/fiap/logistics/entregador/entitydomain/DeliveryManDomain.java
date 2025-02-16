package fiap.logistics.entregador.entitydomain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeliveryManDomain {

    private Long id;
    private String name;

    //TODO tirar Anotacaoes Lombok, validações fazer no dto


}
