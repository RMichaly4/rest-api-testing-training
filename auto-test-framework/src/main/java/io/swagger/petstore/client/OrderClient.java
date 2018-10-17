package io.swagger.petstore.client;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import io.swagger.petstore.client.constant.FormParam;
import io.swagger.petstore.client.constant.QueryParam;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.Pet;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.URLENC;
import static io.swagger.petstore.client.constant.Path.*;
import static io.swagger.petstore.client.constant.PathParam.ORDER_ID;
import static io.swagger.petstore.client.constant.PathParam.PET_ID;

public class OrderClient extends PetStoreAbstractClient {

    @Step
    public static ValidatableResponse createOrder(Order order) {

        return given(SPECIFICATION)
                .contentType(JSON)
                .body(order)
                .when()
                .post(ORDER)
                .then();
    }

    @Step
    public static ValidatableResponse getOrder(long id) {

        return given(SPECIFICATION)
                .pathParam(ORDER_ID, id)
                .when()
                .get(ORDER + "/{" + ORDER_ID + "}")
                .then();
    }

    @Step
    public static ValidatableResponse deleteOrder(long id) {

        return given(SPECIFICATION)
                .pathParam(ORDER_ID, id)
                .when()
                .delete(ORDER + "/{" + ORDER_ID + "}")
                .then();
    }
}
