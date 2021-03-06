package test.jpashop.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends BaseEntity {

    @Id @GeneratedValue // primary key, auto_increment
    @Column(name = "MEMBER_ID")
    private Long id;
    //컬럼의 이름을 변경하거나 길이설정, 널 허용 등의 옵션설정을 하지않을경우
    //@Column 어노테이션을 붙이지 않아도 JPA가 필드를 컬럼으로 생성해준다.
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public void addOrder(Order order) {
        this.orders.add(order);
        order.setMember(this);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Member() {
        //포록시때문에 기본생성자는 필수라고한다.. 아직 잘 이해는 안감.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
