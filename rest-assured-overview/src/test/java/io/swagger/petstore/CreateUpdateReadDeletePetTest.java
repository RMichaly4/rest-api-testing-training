package io.swagger.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.swagger.petstore.base.BaseTest;
import org.awaitility.Duration;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.Callable;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.equalTo;

public class CreateUpdateReadDeletePetTest extends BaseTest {

    @BeforeClass
    public void createPet() {
        RestAssured.given()
                .accept(ContentType.JSON)
                .header("api_key", "1234567890")
                .contentType(ContentType.JSON)
                .body(new File("src/test/resources/pet2.json"))
                .when()
                .post("https://petstore.swagger.io/v2/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdateReadDeletePet() {
        int petId = 888;

        RestAssured.given()
                .accept(ContentType.JSON)
                .header("api_key", "1234567890")
                .formParam("name", "auto_test_sample_pet_updated")
                .formParam("status", "sold")
                .pathParam("id", petId)
                .when()
                .post("https://petstore.swagger.io/v2/pet/{id}")
                .then()
                .statusCode(200);

        await("updated pet status")
                .atMost(Duration.FIVE_SECONDS)
                .with()
                .pollDelay(20, MILLISECONDS)
                .pollInterval(Duration.FIVE_HUNDRED_MILLISECONDS)
                .until(petStatus(petId), equalTo("sold"));
    }

    private Callable<String> petStatus(int petId) {
        return new Callable<String>() {
            public String call() throws Exception {
                return RestAssured.given()
                        .header("api_key", "1234567890")
                        .contentType(ContentType.JSON)
                        .when()
                        .get("https://petstore.swagger.io/v2/pet/" + petId)
                        .then()
                        .extract().body().path("status");
            }


            public void deletePet() {

                RestAssured.given()
                        .accept(ContentType.JSON)
                        .header("api_key", "1234567890")
                        .pathParam("id", petId)
                        .when()
                        .delete("https://petstore.swagger.io/v2/pet/{id}")
                        .then()
                        .statusCode(200);

            }



        };


    }




}
