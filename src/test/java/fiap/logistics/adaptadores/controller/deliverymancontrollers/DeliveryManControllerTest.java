package fiap.logistics.adaptadores.controller.deliverymancontrollers;

import fiap.logistics.api.dto.DeliveryManDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DeliveryManControllerTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/deliveryman";
    }

    @Test
    void saveDeliveryMan_Success() {
        DeliveryManDTO deliveryManDTO = new DeliveryManDTO(10L, "João Kleber");

        given()
                .contentType("application/json")
                .body(deliveryManDTO)
                .when()
                .post(baseUrl)
                .then()
                .statusCode(201)
                .body(equalTo("DeliveryMan saved"));
    }

    @Test
    void getDeliveryMan_Success() {
        long id = 1L;

        given()
                .contentType("application/json")
                .when()
                .get(baseUrl + "/" + id)
                .then()
                .statusCode(200)
                .body("name", equalTo("Joao Kleber"));
    }

    @Test
    void getDeliveryMan_NotFound() {
        long id = 99L;

        var response = given()
                .contentType("application/json")
                .when()
                .get(baseUrl + "/" + id)
                .then()
                .log().all()
                .statusCode(404)
                .extract().response();

        Assertions.assertEquals("{\"message\":\"DeliveryMan not found with id: 99\",\"statusCode\":404}", response.getBody().asString());
    }

    @Test
    void getAllDeliveryMan_Success() {
        given()
                .contentType("application/json")
                .when()
                .get(baseUrl)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0));
    }
}