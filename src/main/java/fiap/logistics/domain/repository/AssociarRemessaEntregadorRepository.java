package fiap.logistics.domain.repository;

public interface AssociarRemessaEntregadorRepository {

    void associarRemessaAEntregador(Long idRemessa, Long idEntregador);

}
