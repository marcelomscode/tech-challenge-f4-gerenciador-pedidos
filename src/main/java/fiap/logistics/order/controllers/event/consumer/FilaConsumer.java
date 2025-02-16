package fiap.logistics.order.controllers.event.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fiap.logistics.order.controllers.event.json.PedidoJson;
import fiap.logistics.order.gateways.RabbitMQConfig;
import fiap.logistics.order.entities.persistence.Order;
import fiap.logistics.order.usecases.PedidoUsecase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
@Slf4j
public class FilaConsumer {

    private final PedidoUsecase pedidoUsecase;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PEDIDO_PARA_ENTREGA)
    public void receberPedidoParaEntrega(String mensagem) throws JsonProcessingException {

        try {
            PedidoJson pedido = objectMapper.readValue(mensagem, PedidoJson.class);
            pedidoUsecase.salvarPedido(pedido);
            log.info("Pedido salvo com sucesso: {} ", pedido.getOrderNumber());

        } catch (Exception e) {
            log.error("Erro ao salvar pedido: {} ", e.getMessage());
        }
    }
}


