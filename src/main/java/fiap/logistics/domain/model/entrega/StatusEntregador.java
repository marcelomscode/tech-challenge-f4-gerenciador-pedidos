package fiap.logistics.domain.model.entrega;

import lombok.Getter;

@Getter
public enum StatusEntregador {

    DISPONIVEL(1),
    INDISPONIVEL(2);

    private final int codigo;

    StatusEntregador(int codigo) {
        this.codigo = codigo;
    }

}
