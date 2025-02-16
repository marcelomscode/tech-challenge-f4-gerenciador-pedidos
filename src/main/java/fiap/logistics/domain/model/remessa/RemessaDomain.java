package fiap.logistics.domain.model.remessa;

import lombok.Getter;

@Getter
public class RemessaDomain {

    private final Long idRemessa;
    private final int statusRemessa;

    public RemessaDomain(Long idRemessa, int statusRemessa) {
        this.idRemessa = idRemessa;
        this.statusRemessa = statusRemessa;
    }


}
