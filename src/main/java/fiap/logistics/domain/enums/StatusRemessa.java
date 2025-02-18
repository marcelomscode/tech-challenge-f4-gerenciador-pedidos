package fiap.logistics.domain.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public enum StatusRemessa {

    AGUARDANDO_ENTREGA(1, "Aguardando entrega"),
    EM_PREPARACAO_PARA_ENTREGA(2, "Em preparação para entrega"),
    EM_ROTA_DE_ENTREGA(3, "Em rota de entrega"),
    ENTREGUE(4, "Entregue"),
    ENTREGUE_PARCIALMENTE(5, "Entregue parcialmente");

    private final int id;
    private final String descricao;

    private static final Map<Integer, StatusRemessa> ID_MAP = new HashMap<>();
    private static final Map<String, StatusRemessa> DESCRICAO_MAP = new HashMap<>();

    static {
        for (StatusRemessa estado : StatusRemessa.values()) {
            ID_MAP.put(estado.getId(), estado);
            DESCRICAO_MAP.put(estado.getDescricao(), estado);
        }
    }

    StatusRemessa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }




}
