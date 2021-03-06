package io.swagger.petstore.model.registry;

import io.swagger.petstore.model.Pet;
import io.swagger.petstore.model.builder.PetBuilder;
import io.swagger.petstore.data.PetStatus;

import java.util.concurrent.atomic.AtomicInteger;

public class PetRegistry {

    private static AtomicInteger COUNTER = new AtomicInteger(0);

    public static Pet getUniquePetWithOnlyRequiredFields() {

        return getPetBuilder().build();
    }

    public static PetBuilder getPetBuilder() {
        int index = COUNTER.incrementAndGet();

        //sets only required Pet fields
        return new PetBuilder()
                .setId(index)
                .setName("auto_test_" + index)
                .setStatus(PetStatus.AVAILABLE);
    }
}
