package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.*;
import jpabook3.jpashop3.domain.item.Item;
import jpabook3.jpashop3.repository.ItemRepository;
import jpabook3.jpashop3.repository.MemberRepository;
import jpabook3.jpashop3.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class OrderService {

    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member findMember = memberRepository.findOne(memberId);
        Delivery delivery = new Delivery();
        delivery.setAddress(new Address("city", "street", "100000"));
        delivery.setStatus(DeliveryStatus.READY);
        Item findItem = itemRepository.findOne(itemId);
        OrderItem orderItem = OrderItem.createOrderItem(findItem, findItem.getPrice(), count);
        Order order = Order.createOrder(findMember, delivery, orderItem);

        return orderRepository.save(order);
    }

    private List<Order> findOrderList(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }

    @Transactional
    public void orderCancel(Long id) {
        Order findOrder = orderRepository.findOne(id);
        findOrder.cancel();
    }
}
