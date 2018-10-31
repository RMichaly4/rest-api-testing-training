package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static io.swagger.petstore.data.PetStatus.PENDING;
import static io.swagger.petstore.data.PetStatus.SOLD;
import static org.assertj.core.api.Assertions.assertThat;

public class GetOrderTest extends BaseTest {

    private Order sampleOrder;

    @BeforeClass
    public void createOrder() {
        Order sampleOrderToCreate = getGenericOrder();

        sampleOrder = OrderClient.createOrder(sampleOrderToCreate)
                .assertThat().statusCode(200)
                .extract().body().as(Order.class);

        assertThat(sampleOrder).isNotNull();
    }

    @AfterClass
    public void deleteOrder() {
        OrderClient.deleteOrder(sampleOrder.getId());
    }

    @Test
    public void testGetOrder() {
        Order fetchedOrder = OrderClient.getOrder(sampleOrder.getId())
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .extract().body().as(Order.class);

        assertThat(fetchedOrder)
                .isNotNull()
                .isEqualTo(sampleOrder);
    }
}
