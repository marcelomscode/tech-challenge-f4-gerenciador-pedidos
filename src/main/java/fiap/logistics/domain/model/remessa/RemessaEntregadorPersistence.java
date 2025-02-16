package fiap.logistics.domain.model.remessa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "remessaentregador")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RemessaEntregadorPersistence {

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private Long idRemessa;
    private Long idEntregador;
    private int statusRemessa;
    private LocalDateTime dataInclusao;

}
