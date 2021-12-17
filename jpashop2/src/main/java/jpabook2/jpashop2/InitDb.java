package jpabook2.jpashop2;

import jpabook2.jpashop2.domain.*;
import jpabook2.jpashop2.domain.Item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {

            Member member1 = createMember("pjt", "서울", "강변", "111111");
            em.persist(member1);

            Book boo1 = createItem("jpa1", 10000, 20);
            em.persist(boo1);

            Book boo2 = createItem("jpa2", 20000, 20);
            em.persist(boo2);

            OrderItem orderItem1 = OrderItem.createOrderItem(boo1, 10000, 2);
            OrderItem orderItem2 = OrderItem.createOrderItem(boo2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(new Address("서울", "강변", "111111"));

            Order order = Order.createOrder(member1, delivery, orderItem1, orderItem2);
            em.persist(order);
        }

        private Book createItem(String name, int price, int stockQuantity) {
            Book boo1 = new Book();
            boo1.setName(name);
            boo1.setPrice(price);
            boo1.setStockQuantity(stockQuantity);
            return boo1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member1 = new Member();
            member1.setName(name);
            member1.setAddress(new Address(city, street, zipcode));
            return member1;
        }

        public void dbInit2() {
            Member member1 = createMember("ye", "부산", "해운대", "222222");
            em.persist(member1);

            Book boo1 = createItem("spring1", 10000, 20);
            em.persist(boo1);

            Book boo2 = createItem("spring2", 20000, 20);
            em.persist(boo2);

            OrderItem orderItem1 = OrderItem.createOrderItem(boo1, 10000, 3);
            OrderItem orderItem2 = OrderItem.createOrderItem(boo2, 20000, 4);

            Delivery delivery = new Delivery();
            delivery.setAddress(new Address("부산", "해운대", "222222"));

            Order order = Order.createOrder(member1, delivery, orderItem1, orderItem2);
            em.persist(order);
        }
    }
}
