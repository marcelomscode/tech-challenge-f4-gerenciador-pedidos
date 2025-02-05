package fiap.logistics.deliverydomain.gateways;

import fiap.logistics.deliverydomain.entities.domain.GeoLocationByCEP;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "busca-cep", url = "https://www.cepaberto.com")
public interface BuscaInformacoesCep {

    @GetMapping("/api/v3/cep")
    GeoLocationByCEP buscaInformacoesCep(@RequestParam("cep") String cep, @RequestHeader("Authorization") String token);

}
