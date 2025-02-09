package fiap.logistics.order.entities.persistence;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "pedido")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long numeroPedido;
    private String endereco;
    private int numeroResidencia;
    private String cep;
    private LocalDate previsaoDataEntrega;
    private int status;

}
