package fiap.logistics.domain.ports;

public interface AssociarRemessaEntregadorRepository {

    void associarRemessaAEntregador(Long idRemessa, Long idEntregador);

}
