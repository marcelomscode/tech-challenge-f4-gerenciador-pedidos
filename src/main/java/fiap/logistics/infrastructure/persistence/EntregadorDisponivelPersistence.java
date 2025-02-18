package fiap.logistics.infrastructure.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "entregadordisponivel")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EntregadorDisponivelPersistence {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private int statusEntregador;
    @OneToOne
    @JoinColumn(name = "idEntregador")
    private DeliveryManPersistence idEntregador;

}
