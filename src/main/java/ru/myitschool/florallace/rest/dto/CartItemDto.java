package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemDto {

    private Long id;

    private ProductDto product;

    private Integer quantity;

    public static CartItemDto toDto(CartItem cartItem) {



        return new CartItemDto(
                cartItem.getId(),
                ProductDto.toDto(cartItem.getProduct()),
                cartItem.getQuantity()
        );
    }

    public static CartItem toDomainObject(CartItemDto cartItemDto, User user) {

        return new CartItem(
                cartItemDto.getId(),
                user,
                ProductDto.toDomainObject(cartItemDto.getProduct()),
                cartItemDto.getQuantity()
        );
    }
}
