package fiap.logistics.deliverydomain.gateways;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class FilaConsumer {


    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(String mensagem){
        log.info("Mensagem recebida: {} ", mensagem);
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PEDIDO_PARA_ENTREGA)
    public void receberPedidoParaEntrega(String mensagem) throws JsonProcessingException {

        try {
            PedidoJson pedido = objectMapper.readValue(mensagem, PedidoJson.class);
            log.info("Mensagem recebida: {} ", pedido.getEndereco());
        } catch (JsonProcessingException e) {
            log.error("Erro ao processar mensagem  da fila QUEUE_PEDIDO_PARA_ENTREGA, erro: {}", e.getMessage());
            throw e;
        }
    }

}


