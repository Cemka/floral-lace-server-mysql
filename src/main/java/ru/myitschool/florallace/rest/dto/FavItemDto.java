package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.OrderItem;
import ru.myitschool.florallace.domain.Order;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavItemDto {
    private Long id;

    private ProductDto product;

    private Integer quantity;

    public static FavItemDto toDto(OrderItem favItem) {



        return new FavItemDto(
                favItem.getId(),
                ProductDto.toDto(favItem.getProduct()),
                favItem.getQuantity()
        );
    }

    public static OrderItem toDomainObject(FavItemDto favItemDto, Order order) {

        return new OrderItem(
                favItemDto.getId(),
                order,
                ProductDto.toDomainObject(favItemDto.getProduct()),
                favItemDto.getQuantity()
        );
    }
}
