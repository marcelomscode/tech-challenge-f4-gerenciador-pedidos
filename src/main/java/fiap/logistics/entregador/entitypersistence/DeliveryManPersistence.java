package fiap.logistics.entregador.entitypersistence;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "deliveryman")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryManPersistence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

}


