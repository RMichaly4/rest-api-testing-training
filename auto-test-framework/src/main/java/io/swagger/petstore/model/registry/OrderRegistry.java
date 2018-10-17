package io.swagger.petstore.model.registry;

import io.swagger.petstore.data.OrderStatus;
import io.swagger.petstore.model.Order;
import io.swagger.petstore.model.builder.OrderBuilder;
import org.apache.commons.lang3.RandomUtils;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderRegistry {

    private static AtomicInteger COUNTER = new AtomicInteger(0);

    public static Order getUniqueOrderWithOnlyRequiredFields() {

        return getOrderBuilder().build();
    }

    public static OrderBuilder getOrderBuilder() {
        int indexId = COUNTER.incrementAndGet();
        int indexPetId = indexId + 10;

        //sets only required Order fields
        return new OrderBuilder()
                .setId(indexId)
                .setPetId(indexPetId)
                .setShipDate("2018-10-17T21:00:06.324Z")
                .setStatus(OrderStatus.PLACED)
                .setComplete(false);
    }
}
