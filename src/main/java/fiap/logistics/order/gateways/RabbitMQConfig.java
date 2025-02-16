package fiap.logistics.order.gateways;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_PEDIDO_PARA_ENTREGA = "q-ms-logistics-order";

    @Bean
    public Queue pedidoParaEntrega() {
        return new Queue(QUEUE_PEDIDO_PARA_ENTREGA, false);
    }

}
