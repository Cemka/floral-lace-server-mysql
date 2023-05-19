package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.service.order.OrderService;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Long userId;

    private List<OrderItemDto> orderItemDto;

    private Integer price;

    private String location;

    private String time;

    public static OrderDto toDto(Order order) {
        /* Проверка на null */

        if (order == null) {
            return new OrderDto(null, null, new ArrayList<>(), null, null, null);
        }

        List<OrderItemDto> favItemDtoList = new ArrayList<>();
        List<OrderItem> favItems = order.getFavItems();
        if (favItems != null) {
            for (OrderItem favItem : favItems) {
                favItemDtoList.add(OrderItemDto.toDto(favItem));
            }
        }

        return new OrderDto(
                order.getId(),
                order.getUserId().getId(),
                favItemDtoList,
                order.getPrice(),
                order.getLocation(),
                order.getTime()
        );
    }

    public static Order toDomainObject(OrderDto orderDto, User user, OrderService orderService) {

        return new Order(
                orderDto.getId(),
                user,
                orderDto.getOrderItemDto()
                        .stream()
                        .map(s -> OrderItemDto.toDomainObject(s, orderService.getById(orderDto.getId())))
                        .toList(),
                orderDto.getPrice(),
                orderDto.getLocation(),
                orderDto.getTime()
        );
    }
}
