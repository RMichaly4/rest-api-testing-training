package io.swagger.petstore.order;

import io.swagger.petstore.base.BaseTest;
import io.swagger.petstore.client.PetClient;
import io.swagger.petstore.model.Pet;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

public class DeleteOrderTest extends BaseTest {

    private long petId;

    @BeforeClass
    public void createPet() {
        Pet createdPet = PetClient.createPet(getGenericPetWithAllFields())
                .assertThat().statusCode(200)
                .extract().body().as(Pet.class);

        assertThat(createdPet).isNotNull();
        petId = createdPet.getId();
    }

    @Test
    public void testDeletePet() {
        String responseBody = PetClient.deletePet(petId)
                .assertThat()
                .statusCode(200)
                .contentType(JSON)
                .extract().body().asString();

        assertThat(responseBody)
                .isNotNull()
                .isEqualTo("");

        PetClient.getPet(petId)
                .assertThat().statusCode(404);
    }
}
