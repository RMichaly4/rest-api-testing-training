package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;
import io.swagger.petstore.model.builder.OrderBuilder;
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

    @DataProvider(name = "statusAndIsComplete")
    public static Object[][] createData() {
        return new Object[][]{
                {PLACED, true}, {APPROVED, true}, {DELIVERED, false}
        };
    }

    @Test(dataProvider = "statusAndIsComplete")
    public void testCreateOrder_AllFields_Status(String status, boolean isComplete) {
        Order orderToCreate = getGenericOrder();
        orderToCreate.setQuantity(2);
        orderToCreate.setShipDate("2018-12-17T21:00:06.324+0000");
        orderToCreate.setStatus(status);
        orderToCreate.setComplete(isComplete);   //HOW TO TEST MULTIPLE VALUES: TRUE+FALSE

        testCreateOrder(orderToCreate);
    }

    @Test
    public void testCreateOrder_OnlyRequiredFields() {

        Order orderToCreate = OrderRegistry.getUniqueOrderWithOnlyRequiredFields();

        Order expectedOrder = new OrderBuilder()
                .setId(orderToCreate.getId())
                .setPetId(orderToCreate.getPetId())
                .setComplete(false)
                .build();

        orderId = orderToCreate.getId();

        Order createdOrder = OrderClient.createOrder(orderToCreate)
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .extract().body().as(Order.class);

        assertThat(createdOrder)
                .isNotNull()
                .isEqualTo(expectedOrder);

        Order fetchedOrder = OrderClient.getOrder(orderId)
                .assertThat().statusCode(200)
                .extract().body().as(Order.class);

        assertThat(fetchedOrder)
                .isNotNull()
                .isEqualTo(expectedOrder);
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
