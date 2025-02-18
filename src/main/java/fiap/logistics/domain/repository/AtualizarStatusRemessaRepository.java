package fiap.logistics.domain.repository;


public interface AtualizarStatusRemessaRepository {

    void atualizarStatusRemessa(Long idRemessa, int status);

}
