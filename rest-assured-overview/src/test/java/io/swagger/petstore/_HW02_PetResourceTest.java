package io.swagger.petstore;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.swagger.petstore.base.BaseTest;
import org.testng.annotations.Test;

import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class _HW02_PetResourceTest extends BaseTest {

    private String petId = String.valueOf(new Random().nextInt(999));

    private String petJsonToCreate = "{" +
            "\"id\":" + petId + "," +
            "\"category\":{" +
            "\"id\":222," +
            "\"name\":\"dinosaurs\"" +
            "}," +
            "\"name\":\"Buddy\"," +
            "\"photoUrls\":[" +
            "\"https://fastpic.com/aaa111bbb.jpg\"" +
            "]," +
            "\"tags\":[" +
            "{" +
            "\"id\":12," +
            "\"name\":\"predators\"" +
            "}" +
            "]," +
            "\"status\":\"available\"" +
            "}";

    private String petJsonToUpdate = "{" +
            "\"id\":" + petId + "," +
            "\"category\":{" +
            "\"id\":333," +
            "\"name\":\"reptiles\"" +
            "}," +
            "\"name\":\"Hellboy\"," +
            "\"photoUrls\":[" +
            "\"https://fastpic.com/aaa111bbb.jpg\"" +
            "," +
            "\"https://fastpic.com/ccc222ddd.jpg\"" +
            "]," +
            "\"tags\":[" +
            "{" +
            "\"id\":9112," +
            "\"name\":\"fossil\"" +
            "}" +
            "]," +
            "\"status\":\"pending\"" +
            "}";

    private RequestSpecification requestSpecificationCreateUpdate = new RequestSpecBuilder()
            .addHeader("api_key", "1234567890")
            .setAccept(JSON)
            .setBaseUri("https://petstore.swagger.io")
            .setBasePath("/v2/pet")
            .build();

    private RequestSpecification requestSpecificationFindByStatus = new RequestSpecBuilder()
            .addHeader("api_key", "1234567890")
            .setAccept(JSON)
            .setBaseUri("https://petstore.swagger.io")
            .setBasePath("/v2/pet/findByStatus")
            .build();

    private ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType(JSON)
            .build();

    @Test
    public void testCreatePet() {

        String createdPetJson = given()
                .spec(requestSpecificationCreateUpdate)
                .contentType(JSON)
                .body(petJsonToCreate)
                .when()
                .post()
                .then()
                .spec(responseSpecification)
                .extract().body().asString();

        assertThat(createdPetJson).isEqualTo(petJsonToCreate);

        String updatedPetJson = given()
                .spec(requestSpecificationCreateUpdate)
                .contentType(JSON)
                .body(petJsonToUpdate)
                .when()
                .put()
                .then()
                .spec(responseSpecification)
                .extract().body().asString();

        assertThat(updatedPetJson).isEqualTo(petJsonToUpdate);

        String fetchedPetJson = given()
                .spec(requestSpecificationCreateUpdate)
                .when()
                .get(petId)
                .then()
                .spec(responseSpecification)
                .extract().body().asString();

        assertThat(fetchedPetJson).isEqualTo(updatedPetJson);


        String fetchedByStatusPetsJson = given()
                .spec(requestSpecificationFindByStatus)
                .when()
                .queryParam("status", "pending")
                .get()
                .then()
                .spec(responseSpecification)
                .extract().body().asString();

        assertThat(fetchedByStatusPetsJson).contains(updatedPetJson);


        String deletePetResponseBody = given()
                .spec(requestSpecificationCreateUpdate)
                .when()
                .delete(petId)
                .then()
                .spec(responseSpecification)
                .extract().body().asString();

        assertThat(deletePetResponseBody).isEqualTo("");
    }
}
