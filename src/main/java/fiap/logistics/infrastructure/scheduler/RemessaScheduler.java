package fiap.logistics.infrastructure.scheduler;

import fiap.logistics.application.usecases.entregas.AssociarRemessaAEntregadorUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@Component
@RestController
@RequestMapping("/remessa")
public class RemessaScheduler {

    private final AssociarRemessaAEntregadorUseCase associarRemessaAEntregadorUseCase;

    public RemessaScheduler(AssociarRemessaAEntregadorUseCase associarRemessaAEntregadorUseCase) {
        this.associarRemessaAEntregadorUseCase = associarRemessaAEntregadorUseCase;
    }

    @GetMapping("/associar")
    public void associarRemessaAEntregador() {
        associarRemessaAEntregadorUseCase.executar();
    }

//    @Scheduled(fixedRate = 10000) // 300000 ms = 5 minutos
    public void verificarEntregadoresDisponiveis() {
        associarRemessaAEntregadorUseCase.executar();
    }
}