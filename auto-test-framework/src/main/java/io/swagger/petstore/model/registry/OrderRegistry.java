package io.swagger.petstore.model.registry;

import io.swagger.petstore.data.OrderStatus;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.builder.OrderBuilder;

import java.util.concurrent.atomic.AtomicInteger;

public class OrderRegistry {

    private static AtomicInteger COUNTER_ID = new AtomicInteger(0);
    private static AtomicInteger COUNTER_PET_ID = new AtomicInteger(10);

    public static Order getUniqueOrderWithOnlyRequiredFields() {

        return getOrderBuilder().build();
    }

    public static OrderBuilder getOrderBuilder() {
        int indexId = COUNTER_ID.incrementAndGet();
        int indexPetId = COUNTER_PET_ID.incrementAndGet();

        //sets only required Order fields
        return new OrderBuilder()
                .setId(indexId)
                .setPetId(indexPetId);
                //.setQuantity(1)
                //.setShipDate("2018-10-17T21:00:06.324+0000")
                //.setStatus(OrderStatus.PLACED)
                //.setComplete(false);
    }
}
