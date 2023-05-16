package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.service.user.UserService;

import java.util.List;
import java.util.Map;
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

    private Map<ProductDto, Integer> productsInCart;

    private OrderDto userOrder;

    public static UserDto toDto(User user) {


        List<ProductDto> favPrDto = user.getFavouriteProducts().stream().map(ProductDto::toDto).toList();
        Map<ProductDto, Integer> cartPrDto = user.getProductsInCart()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> ProductDto.toDto(entry.getKey()), Map.Entry::getValue));

        return new UserDto(
                user.getId(),
                user.getPhoneNumb(),
                user.getFirstName(),
                user.getSecondName(),
                user.getCountOfBonus(),
                favPrDto,
                cartPrDto,
                OrderDto.toDto(user.getUserOrder())
        );
    }

    public static User toDomainObject(UserDto userDto, UserService userService, ProductRepository productRepository) {

        List<Product> favouriteProducts = userDto.getFavouriteProducts().stream().map(ProductDto::toDomainObject).collect(Collectors.toList());

        Map<Product, Integer> productsInCart = userDto.getProductsInCart()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> ProductDto.toDomainObject(entry.getKey()), Map.Entry::getValue));

        User user = userService.getById(userDto.getId());

        return new User(
                userDto.getId(),
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                favouriteProducts,
                productsInCart,
                OrderDto.toDomainObject(userDto.getUserOrder(), user)
        );
    }

}
