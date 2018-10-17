package io.swagger.petstore.model;

import com.sun.jmx.snmp.Timestamp;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order {

    private long id;
    private long petId;
    private Timestamp shipDate;
    private String status;
    private Boolean complete;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public Timestamp getShipDate() {
        return shipDate;
    }

    public void setShipDate(Timestamp shipDate) {
        this.shipDate = shipDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                petId == order.petId &&
                Objects.equals(shipDate, order.shipDate) &&
                Objects.equals(status, order.status) &&
                Objects.equals(complete, order.complete);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petId, shipDate, status, complete);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", shipDate=" + shipDate +
                ", status='" + status + '\'' +
                ", complete=" + complete +
                '}';
    }
}
