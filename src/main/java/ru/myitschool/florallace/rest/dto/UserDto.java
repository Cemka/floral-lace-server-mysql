package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.service.order.OrderService;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String phoneNumb;

    private String firstName;

    private String secondName;

    private Integer countOfBonus;

    private List<ProductDto> favouriteProducts;

    private List<CartItemDto> cartItems;;

    private OrderDto userOrder;

    public static UserDto toDto(User user) {


        List<ProductDto> favPrDto = user.getFavouriteProducts().stream().map(ProductDto::toDto).toList();
        List<CartItemDto> cartItemsDto = user.getCartItems().stream().map(CartItemDto::toDto).toList();

        return new UserDto(
                user.getId(),
                user.getPhoneNumb(),
                user.getFirstName(),
                user.getSecondName(),
                user.getCountOfBonus(),
                favPrDto,
                cartItemsDto,
                OrderDto.toDto(user.getUserOrder())
        );
    }

    public static User toDomainObject(UserDto userDto, UserService userService, OrderService orderService) {

        List<Product> favouriteProducts = userDto
                .getFavouriteProducts()
                .stream()
                .map(ProductDto::toDomainObject)
                .collect(Collectors.toList());

        List<CartItem> cartItems = userDto
                .getCartItems()
                .stream()
                .map(s -> CartItemDto.toDomainObject(s, userService.getById(userDto.getId())))
                .toList();

        User user = userService.getById(userDto.getId());

        return new User(
                userDto.getId(),
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                favouriteProducts,
                cartItems,
                OrderDto.toDomainObject(userDto.getUserOrder(), user, orderService)
        );
    }
}
