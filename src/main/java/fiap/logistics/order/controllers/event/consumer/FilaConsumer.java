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
    private static final String OSRM_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";
    private static final String OVERVIEW_PARAM = "?overview=false";

    @RabbitListener(queues = RabbitMQConfig.QUEUE_PEDIDO_PARA_ENTREGA)
    public void receberPedidoParaEntrega(String mensagem) throws JsonProcessingException {

        try {
            PedidoJson pedido = objectMapper.readValue(mensagem, PedidoJson.class);
            pedidoUsecase.salvarPedido(pedido);

            log.info("Pedido salvo com sucesso: {} ", pedido.getCep());

//            //Latitude Praça da Sé: -23.550164466
//            //Longitude Praça da Sé: -46.633664132
//            double originLat = -23.550164466;
//            double originLng = -46.633664132;
//
//            //Latitude e Longitude do pedido
//            double destinationLat = Double.parseDouble(pedidoRetorno.getLatitude());
//            double destinationLng = Double.parseDouble(pedidoRetorno.getLongitude());
//
//            double[] result = getDistanceAndDurationFromOSRM(originLat, originLng, destinationLat, destinationLng);
//
//            if (result != null) {
//                double distance = result[0];
//                double durationInSeconds = result[1];
//
//                // Converte a duração de segundos para minutos
//                double durationInMinutes = durationInSeconds / 60;
//
//                // Arredonda a duração para cima ou para baixo, dependendo dos segundos
//                int roundedMinutes;
//                double remainingSeconds = durationInSeconds % 60;
//
//                if (remainingSeconds >= 30) {
//                    roundedMinutes = (int) Math.ceil(durationInMinutes); // Arredonda para cima
//                } else {
//                    roundedMinutes = (int) Math.floor(durationInMinutes); // Arredonda para baixo
//                }
//
//                System.out.println("Distância: " + distance/1000 + " Km");
//                System.out.println("Duração: " + roundedMinutes + " minutos");
//            } else {
//                System.out.println("Falha ao obter a distância e duração do OSRM.");
//            }

        } catch (Exception e) {
            log.error("Erro ao salvar pedido: {} ", e.getMessage());
        }
    }

    public static double[] getDistanceAndDurationFromOSRM(double originLatitude, double originLongitude,
                                                          double destinationLatitude, double destinationLongitude) {
        String jsonResponse = fazerRequisicaoOSRM(originLatitude, originLongitude, destinationLatitude, destinationLongitude);

        if (jsonResponse == null) {
            System.err.println("Falha ao obter a resposta do OSRM.");
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);

            // Verifica o código de resposta
            if (!"Ok".equals(jsonObject.getString("code"))) {
                System.err.println("Erro no código de resposta do OSRM: " + jsonObject.getString("code"));
                return null;
            }

            JSONArray routes = jsonObject.getJSONArray("routes");
            if (routes.length() == 0) {
                System.err.println("Nenhuma rota encontrada pelo OSRM.");
                return null;
            }

            JSONObject route = routes.getJSONObject(0); // Pega a primeira rota
            double distance = route.getDouble("distance");
            double duration = route.getDouble("duration");

            return new double[]{distance, duration};

        } catch (Exception e) {
            System.err.println("Erro ao analisar a resposta JSON do OSRM: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Faz a requisição ao servidor OSRM.
     */
    private static String fazerRequisicaoOSRM(double originLatitude, double originLongitude,
                                              double destinationLatitude, double destinationLongitude) {
        try {
            // Converte os doubles para String diretamente e concatena com a vírgula
            String origin = originLongitude + "," + originLatitude;
            String destination = destinationLongitude + "," + destinationLatitude;

            // Constrói a URL da requisição, usando ponto e vírgula para separar os waypoints
            String urlString = OSRM_BASE_URL +
                    URLEncoder.encode(origin, StandardCharsets.UTF_8) + ";" +
                    URLEncoder.encode(destination, StandardCharsets.UTF_8) +
                    OVERVIEW_PARAM;

            URL url = new URL(urlString);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            // Verifica o código de resposta HTTP
            int responseCode = conexao.getResponseCode();
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.err.println("Erro na requisição OSRM. Código de resposta: " + responseCode);
                return null;
            }

            // Lê a resposta
            try (BufferedReader in = new BufferedReader(new InputStreamReader(conexao.getInputStream()))) {
                String inputLine;
                StringBuilder resposta = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    resposta.append(inputLine);
                }

                return resposta.toString();

            } finally {
                conexao.disconnect();
            }

        } catch (IOException e) {
            System.err.println("Erro ao fazer a requisição OSRM: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberMensagem(String mensagem) {
        log.info("Mensagem recebida: {} ", mensagem);
    }

}


