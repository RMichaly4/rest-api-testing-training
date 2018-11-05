package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.OrderClient;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.data.ResponseType;
import io.swagger.petstore.model.ApiResponse;
import io.swagger.petstore.model.builder.ApiResponseBuilder;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static io.swagger.petstore.data.ResponseMessage.ORDER_NOT_FOUND;
import static io.swagger.petstore.data.ResponseMessage.PET_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;

public class GetOrderValidationTest extends BaseTest {

    @Test
    public void testDeleteOrder_NonexistentId() {
        ApiResponse expectedApiResponse = new ApiResponseBuilder()
                .setCode(1)
                .setType(ResponseType.ERROR)
                .setMessage(ORDER_NOT_FOUND)
                .build();

        ApiResponse apiResponse = OrderClient.getOrder(nonexistent_id)
                .assertThat()
                .statusCode(404)
                .contentType(JSON)
                .extract().body().as(ApiResponse.class);

        assertThat(apiResponse)
                .isNotNull()
                .isEqualTo(expectedApiResponse);
    }
}
