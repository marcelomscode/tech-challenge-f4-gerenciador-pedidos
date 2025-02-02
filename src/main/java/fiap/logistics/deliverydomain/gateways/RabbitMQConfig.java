package fiap.logistics.deliverydomain.gateways;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "minha.fila";
    public static final String QUEUE_PEDIDO_PARA_ENTREGA = "pedido.entrega";

    @Bean
    public Queue minhaFila() {
        // O segundo parâmetro (durável) define se a fila persiste após reinicializações do broker.
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public Queue pedidoParaEntrega() {
        return new Queue(QUEUE_PEDIDO_PARA_ENTREGA, false);
    }

}
