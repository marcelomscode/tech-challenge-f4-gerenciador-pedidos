package fiap.logistics.infrastructure.scheduler;

import fiap.logistics.application.usecases.entregas.AssociarRemessaAEntregadorUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Component
@RestController
@RequestMapping("/remessa")
public class RemessaScheduler {

    private final AssociarRemessaAEntregadorUseCase associarRemessaAEntregadorUseCase;

    public RemessaScheduler(AssociarRemessaAEntregadorUseCase associarRemessaAEntregadorUseCase) {
        this.associarRemessaAEntregadorUseCase = associarRemessaAEntregadorUseCase;
    }


    @GetMapping("/associar")
    public String associarRemessaAEntregador() {
        associarRemessaAEntregadorUseCase.executar();

        return "Remessa associada a entregador";

    }

    @Scheduled(fixedRate = 100000) // 300000 ms = 5 minutos
    public void verificarEntregadoresDisponiveis() {
        associarRemessaAEntregadorUseCase.executar();
    }
}