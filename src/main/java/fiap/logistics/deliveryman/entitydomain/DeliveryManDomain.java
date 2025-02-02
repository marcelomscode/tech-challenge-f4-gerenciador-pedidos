package fiap.logistics.deliveryman.entitydomain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DeliveryManDomain {

    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "O nome deve conter apenas letras")
    private String name;

}
