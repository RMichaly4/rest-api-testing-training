package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOrderTest extends BaseTest {

    private long orderId;

    @BeforeClass
    public void createOrder() {
        Order createdOrder = OrderClient.createOrder(getGenericOrder())
                .assertThat().statusCode(200)
                .extract().body().as(Order.class);

        assertThat(createdOrder).isNotNull();
        orderId = createdOrder.getId();
    }

    @Test
    public void testDeleteOrder() {
        String responseBody = OrderClient.deleteOrder(orderId)
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .extract().body().asString();

        assertThat(responseBody)
                .isNotNull()
                .isEqualTo("");

        OrderClient.getOrder(orderId)
                .assertThat().statusCode(404);
    }
}
