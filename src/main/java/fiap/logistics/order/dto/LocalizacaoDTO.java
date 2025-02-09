package fiap.logistics.order.dto;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalizacaoDTO {

    private String latitude;
    private String longitude;
    private String bairro;
    private String cidade;

}
