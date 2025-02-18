package fiap.logistics.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.logistics.application.dto.PedidoJsonDTO;
import fiap.logistics.application.usecases.pedidos.PedidoUsecase;
import fiap.logistics.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FilaConsumer {

    private final PedidoUsecase pedidoUsecase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PEDIDO_PARA_ENTREGA)
    public void receberPedidoParaEntrega(String mensagem) throws JsonProcessingException {

        try {
            PedidoJsonDTO pedido = objectMapper.readValue(mensagem, PedidoJsonDTO.class);
            pedidoUsecase.salvarPedido(pedido);
            log.info("Pedido salvo com sucesso: {} ", pedido.getOrderNumber());

        } catch (Exception e) {
            log.error("Erro ao salvar pedido: {} ", e.getMessage());
        }
    }
}


