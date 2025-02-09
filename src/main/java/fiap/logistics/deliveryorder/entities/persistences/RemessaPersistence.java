package fiap.logistics.deliveryorder.entities.persistences;

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
    private Long numeroPedido;
    private String enderecoEntrega;
    private int numeroResidencia;
    private String tempoEntrega;
    private int statusRemessa;

}
