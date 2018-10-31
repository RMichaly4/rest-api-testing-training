package io.swagger.petstore.model.builder;


import com.sun.jmx.snmp.Timestamp;
import io.swagger.petstore.model.Order;


public class OrderBuilder {

    private Order order;

    public OrderBuilder() {
        order = new Order();
    }

    public OrderBuilder setId(long id) {
        order.setId(id);
        return this;
    }

    public OrderBuilder setPetId(long petId) {
        order.setPetId(petId);
        return this;
    }

    public OrderBuilder setQuantity(int quantity) {
        order.setQuantity(quantity);
        return this;
    }

    public OrderBuilder setShipDate(String shipDate) {
        order.setShipDate(shipDate);
        return this;
    }

    public OrderBuilder setStatus(String status) {
        order.setStatus(status);
        return this;
    }

    public OrderBuilder setComplete(Boolean complete) {
        order.setComplete(complete);
        return this;
    }



    public Order build() {
        return order;
    }
}
