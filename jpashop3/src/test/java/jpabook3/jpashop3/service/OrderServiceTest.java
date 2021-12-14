package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.Address;
import jpabook3.jpashop3.domain.Member;
import jpabook3.jpashop3.domain.Order;
import jpabook3.jpashop3.domain.OrderStatus;
import jpabook3.jpashop3.domain.item.Book;
import jpabook3.jpashop3.exception.NotEnoughStockException;
import jpabook3.jpashop3.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "zipcode"));
        em.persist(member);

        Book book = new Book();
        book.setAuthor("park");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 5;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        //then
        Order findOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER,findOrder.getOrderStatus(),"주문상태는 ORDER가 되어야 한다");
        assertEquals(findOrder.getTotalPrice(),10000*orderCount,"주문금액은 가격 * 수량이다.");
        assertEquals(book.getStockQuantity(), 5 ,"책의 수량이 줄어야 한다.");
        assertEquals(findOrder.getOrderItems().size(),1,"주문한 상품 종류 수가 같아야한다.");
    }
    
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "zipcode"));
        em.persist(member);

        Book book = new Book();
        book.setAuthor("park");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 5;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //when

        orderService.orderCancel(orderId);
        //then
        Order findOrder = orderRepository.findOne(orderId);
        assertThat(OrderStatus.CANCEL).isEqualTo(findOrder.getOrderStatus());
        assertThat(10).isEqualTo(book.getStockQuantity());

    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given
        Member member = new Member();
        member.setName("memberA");
        member.setAddress(new Address("city", "street", "zipcode"));
        em.persist(member);

        Book book = new Book();
        book.setAuthor("park");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        //when
        int orderCount = 11;
        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));
    }



}