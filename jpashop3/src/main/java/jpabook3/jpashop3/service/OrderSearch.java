package jpabook3.jpashop3.service;

import jpabook3.jpashop3.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {
    private OrderStatus orderStatus;
    private String memberName;
}
