package fiap.logistics.deliverydomain.usecasedelivery;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pedido")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String cep;
    private String endereco;
    private String dataCompra;
    private String previsaoDataEntrega;
    private String status;
    private String latitude;
    private String longitude;
    private String bairro;
    private String cidade;

}
