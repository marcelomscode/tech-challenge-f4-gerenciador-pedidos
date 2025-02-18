package fiap.logistics.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class DeliveryManDTO {

    private Long id;
    @NotBlank(message = "O nome não pode estar em branco")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "O nome deve conter apenas letras")
    @Schema(description = "Nome do entregador", example = "João da Silva")
    private String name;

}
