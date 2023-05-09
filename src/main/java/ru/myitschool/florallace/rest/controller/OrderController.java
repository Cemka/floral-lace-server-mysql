package ru.myitschool.florallace.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.rest.dto.OrderDto;
import ru.myitschool.florallace.service.order.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return orderService
                .getAll()
                .stream()
                .map(OrderDto::toDto)
                .toList();
    }

    @GetMapping("/orders/location/{location}")
    public List<OrderDto> getAllByLocation(@PathVariable String location) {
        return orderService
                .findAllByLocation(location)
                .stream()
                .map(OrderDto::toDto)
                .toList();
    }

    @GetMapping("/orders/time/{time}")
    public List<OrderDto> getAllByTime(@PathVariable String time) {
        return orderService
                .findAllByTime(time)
                .stream().map(OrderDto::toDto)
                .toList();
    }

    @PostMapping("/orders")
    public OrderDto insertOrder(@RequestBody OrderDto orderDto) {

        Order order = orderService.insert(orderDto.getUserId(),
                orderDto.getProductListId(),
                orderDto.getPrice(),
                orderDto.getLocation(),
                orderDto.getTime());

        return OrderDto.toDto(order);
    }

    @PutMapping("/orders/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {

        Order order = orderService.update(id, orderDto.getUserId(),
                orderDto.getProductListId(),
                orderDto.getPrice(),
                orderDto.getLocation(),
                orderDto.getTime());

        return OrderDto.toDto(order);
    }

    @GetMapping("/orders/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {

        return OrderDto.toDto(orderService.getById(id));
    }

    @DeleteMapping("/orders/{id}")
    public void deleteById(@PathVariable Long id) {
        orderService.deleteById(id);
    }
}
