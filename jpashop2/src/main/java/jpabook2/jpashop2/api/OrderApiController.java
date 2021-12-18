package jpabook2.jpashop2.api;

import jpabook2.jpashop2.domain.Address;
import jpabook2.jpashop2.domain.Order;
import jpabook2.jpashop2.domain.OrderItem;
import jpabook2.jpashop2.domain.OrderStatus;
import jpabook2.jpashop2.repository.OrderRepository;
import jpabook2.jpashop2.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class OrderApiController {

    private final OrderRepository orderRepository;

    //simple-order에서 나아가 컬렉션 추가 조회

    @GetMapping("/api/v1/orders")
    public List<Order> ordersV1() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();

            order.getOrderItems().forEach(o -> o.getItem().getName());
        }
        return all;
    }

    @GetMapping("/api/v2/orders")
    public List<OrderDto> ordersV2() {
        List<Order> all = orderRepository.findAll(new OrderSearch());
        List<OrderDto> collect = all.stream()
                .map(o -> new OrderDto(o))
                .collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/api/v3/orders")
    public List<OrderDto> ordersV3() {
        List<Order> all = orderRepository.findAllWithItemDtos();
        List<OrderDto> collect = all.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());

        return collect;
    }

    @GetMapping("/api/v3.1/orders")
    public List<OrderDto> ordersV4(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                   @RequestParam(value = "limit", defaultValue = "100") int limit) {
        List<Order> all = orderRepository.findAllWithMemberDelivery(offset, limit);
        List<OrderDto> collect = all.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());

        return collect;
    }

    @Data
    static class OrderDto {
        private Long id;
        private String name;
        private Address address;
        private OrderStatus status;
        private LocalDateTime orderDate;
        private List<OrderItem> orderItems;

        public OrderDto(Order order) {
            this.id = order.getId();
            this.name = order.getMember().getName();
            this.address = order.getDelivery().getAddress();
            this.status = order.getStatus();
            this.orderDate = order.getOrderDate();
            this.orderItems = order.getOrderItems();

            orderItems.stream()
                    .map(oi -> new OrderItemDto(oi))
                    .collect(Collectors.toList());
        }
    }

    @Data
    static class OrderItemDto {
        private String name;
        private int orderPrice;
        private int count;

        public OrderItemDto(OrderItem orderItem) {
            this.name = orderItem.getItem().getName();
            this.orderPrice = orderItem.getOrderPrice();
            this.count = orderItem.getCount();
        }
    }
}
