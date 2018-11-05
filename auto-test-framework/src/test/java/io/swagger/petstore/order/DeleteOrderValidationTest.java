package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOrderValidationTest extends BaseTest {

    @Test
    public void testDeleteOrder_NonexistentId() {
        String responseBody = OrderClient.deleteOrder(nonexistent_id)
                .assertThat()
                .statusCode(404)
                .extract().body().asString();

        assertThat(responseBody)
                .isNotNull()
                .isEqualTo("Order not found");
    }
}
