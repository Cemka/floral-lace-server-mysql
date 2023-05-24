package ru.myitschool.florallace.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.rest.dto.OrderItemDto;
import ru.myitschool.florallace.rest.dto.OrderDto;
import ru.myitschool.florallace.service.order.OrderService;

import java.util.ArrayList;
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

        Order order;
        if(orderDto.getOrderItemDto() != null) {
            order = orderService.insert(orderDto.getUserId(),
                    orderDto.getOrderItemDto()
                            .stream()
                            .map(s -> OrderItemDto.toDomainObject(s, orderService.getById(orderDto.getId())))
                            .toList(),
                    orderDto.getPrice(),
                    orderDto.getLocation(),
                    orderDto.getTime());
        }
        else {
            order = orderService.insert(orderDto.getUserId(),
                    new ArrayList<>(),
                    orderDto.getPrice(),
                    orderDto.getLocation(),
                    orderDto.getTime());
        }
        return OrderDto.toDto(order);

    }

    @PutMapping("/orders/{id}")
    public OrderDto updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto) {

        Order order = orderService.update(id, orderDto.getUserId(),
                orderDto.getOrderItemDto()
                        .stream()
                        .map(s -> OrderItemDto.toDomainObject(s, orderService.getById(orderDto.getId())))
                        .toList(),
                orderDto.getPrice(),
                orderDto.getLocation(),
                orderDto.getTime());

        return OrderDto.toDto(order);
    }

    @GetMapping("/orders/user/{user_id}")
    public OrderDto getByUserId(@PathVariable("user_id") Long userId){
        return OrderDto.toDto(orderService.getByUser(userId));
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
