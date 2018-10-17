package io.swagger.petstore.pet;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;
import io.swagger.petstore.model.registry.OrderRegistry;
import io.swagger.petstore.model.registry.PetRegistry;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static io.swagger.petstore.data.OrderStatus.APPROVED;
import static io.swagger.petstore.data.OrderStatus.DELIVERED;
import static io.swagger.petstore.data.OrderStatus.PLACED;
import static io.swagger.petstore.data.PetStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class CreateOrderTest extends BaseTest {

    private long orderId;

    @AfterMethod
    public void deleteOrder() {
        OrderClient.deleteOrder(orderId);
    }

    @DataProvider(name = "status")
    public static Object[][] createData() {
        return new Object[][]{
                {PLACED}, {APPROVED}, {DELIVERED}
        };
    }

    @Test(dataProvider = "status")
    public void testCreateOrder_AllFields_Status(String status) {
        Order orderToCreate = getGenericOrder();
        orderToCreate.setStatus(status);

        testCreateOrder(orderToCreate);
    }

    @Test
    public void testCreatePet_OnlyRequiredFields() {
        Order orderToCreate = OrderRegistry.getUniqueOrderWithOnlyRequiredFields();
        testCreateOrder(orderToCreate);
    }

    private void testCreateOrder(Order orderToCreate) {
        orderId = orderToCreate.getId();

        Order createdOrder = OrderClient.createOrder(orderToCreate)
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .extract().body().as(Order.class);

        assertThat(createdOrder)
                .isNotNull()
                .isEqualTo(orderToCreate);

        Order fetchedOrder = OrderClient.getOrder(orderId)
                .assertThat().statusCode(200)
                .extract().body().as(Order.class);

        assertThat(fetchedOrder)
                .isNotNull()
                .isEqualTo(orderToCreate);
    }
}