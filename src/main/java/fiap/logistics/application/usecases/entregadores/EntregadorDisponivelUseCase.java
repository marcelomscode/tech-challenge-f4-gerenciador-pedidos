package fiap.logistics.application.usecases.entregadores;

import fiap.logistics.entregador.exceptions.DeliveryManException;
import fiap.logistics.domain.model.entrega.StatusEntregador;
import fiap.logistics.domain.ports.EntregadorDisponivelRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EntregadorDisponivelUseCase {

    private final EntregadorDisponivelRepository entregadorDisponivelRepository;

    public void salvarEntregador(Long id) {
        try {
            entregadorDisponivelRepository.salvarEntregador(id);
        } catch (Exception e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }

    public void mudaStatusEntregador(Long id, StatusEntregador status) {
        try {
            entregadorDisponivelRepository.atualizarStatusEntregador(id, status);
        } catch (Exception e) {
            throw new DeliveryManException("Error getting DeliveryMan", HttpStatus.BAD_REQUEST);
        }
    }





}
