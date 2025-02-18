package fiap.logistics.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
@Getter
@Setter
public class OrderPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numeroPedido;
    private String endereco;
    private int numeroResidencia;
    private String cep;
    private LocalDate previsaoDataEntrega;
    private int status;

    public OrderPersistence(String numeroPedido, String endereco, int numeroResidencia, String cep, LocalDate previsaoDataEntrega, int status) {
        this.numeroPedido = numeroPedido;
        this.endereco = endereco;
        this.numeroResidencia = numeroResidencia;
        this.cep = cep;
        this.previsaoDataEntrega = previsaoDataEntrega;
        this.status = status;
    }

    public OrderPersistence() {

    }
}
