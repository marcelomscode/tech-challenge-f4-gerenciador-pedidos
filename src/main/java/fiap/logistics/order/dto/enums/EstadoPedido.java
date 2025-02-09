package fiap.logistics.order.dto.enums;

import lombok.Getter;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum EstadoPedido {

    PENDENTE(1, "Pendente"),
    EM_PREPARACAO_PARA_ENTREGA(2, "Em preparação para entrega"),
    SAIU_PARA_ENTREGA(3, "Saiu para entrega"),
    CANCELADO(4, "Cancelado");

    private final int id;
    private final String descricao;

    private static final Map<Integer, EstadoPedido> ID_MAP = new HashMap<>();
    private static final Map<String, EstadoPedido> DESCRICAO_MAP = new HashMap<>();

    static {
        for (EstadoPedido estado : EstadoPedido.values()) {
            ID_MAP.put(estado.getId(), estado);
            DESCRICAO_MAP.put(estado.getDescricao(), estado);
        }
    }

    EstadoPedido(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
