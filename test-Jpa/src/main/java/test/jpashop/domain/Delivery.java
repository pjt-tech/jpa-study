package test.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    private DelivertStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void addOrder(Order order) {
        this.order = order;
        order.setDelivery(this);
    }

    public DelivertStatus getStatus() {
        return status;
    }

    public void setStatus(DelivertStatus status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
