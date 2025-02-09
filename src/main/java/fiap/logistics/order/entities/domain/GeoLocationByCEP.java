package fiap.logistics.order.entities.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocationByCEP {

    private String latitude;
    private String longitude;
    private String bairro;
    @JsonProperty("cidade")
    private Cidade cidade;

}

