package fiap.logistics.order.entities.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroPedido;
    private UUID numeroPedidoUUID;
    private String endereco;
    private int numeroResidencia;
    private String cep;
    private LocalDate previsaoDataEntrega;
    private int status;

    public Order(Long numeroPedido, String endereco, int numeroResidencia, String cep, LocalDate previsaoDataEntrega, int status) {
        this.numeroPedido = numeroPedido;
        this.endereco = endereco;
        this.numeroResidencia = numeroResidencia;
        this.cep = cep;
        this.previsaoDataEntrega = previsaoDataEntrega;
        this.status = status;
    }

    public Order(Long numeroPedido, UUID numeroPedidoUUID,   String endereco, int numeroResidencia, String cep, LocalDate previsaoDataEntrega, int status) {
        this.numeroPedido = numeroPedido;
        this.numeroPedidoUUID = numeroPedidoUUID;
        this.endereco = endereco;
        this.numeroResidencia = numeroResidencia;
        this.cep = cep;
        this.previsaoDataEntrega = previsaoDataEntrega;
        this.status = status;
    }

    public Order() {

    }
}
