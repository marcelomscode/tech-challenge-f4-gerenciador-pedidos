package fiap.logistics.domain.model.entrega;

import fiap.logistics.entregador.entitypersistence.DeliveryManPersistence;
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
public class EntregadorDisponivel {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private int statusEntregador;
    @OneToOne
    @JoinColumn(name = "idEntregador")
    private DeliveryManPersistence idEntregador;

}
