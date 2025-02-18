package fiap.logistics.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "remessa")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RemessaPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String idRemessa;
    private String numeroPedido;
    private String enderecoEntrega;
    private int numeroResidencia;
    private String tempoEntrega;
    private int statusRemessa;

}
