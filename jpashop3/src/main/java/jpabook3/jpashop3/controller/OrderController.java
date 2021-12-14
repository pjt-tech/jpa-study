package jpabook3.jpashop3.controller;

import jpabook3.jpashop3.service.ItemService;
import jpabook3.jpashop3.service.MemberService;
import jpabook3.jpashop3.service.OrderSearch;
import jpabook3.jpashop3.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createOrderForm(Model model) {
        model.addAttribute("members", memberService.findAll());
        model.addAttribute("items", itemService.findItemList());

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam Long memberId,
                              @RequestParam Long itemId,
                              @RequestParam int count) {
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getOrderList(@ModelAttribute("orderSearch")OrderSearch orderSearch, Model model) {
        model.addAttribute("orders", orderService.findOrderList(orderSearch));
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String orderCancel(@PathVariable("orderId")Long id) {
        orderService.orderCancel(id);

        return "redirect:/orders";
    }
}
