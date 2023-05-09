package ru.myitschool.florallace.rest.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.service.user.UserServiceImpl;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @Autowired
    public static UserServiceImpl userService;

    private Long id;

    private String phoneNumb;

    private String firstName;

    private String secondName;

    private Integer countOfBonus;

    private List<ProductDto> favouriteProducts;

    private List<ProductDto> productsInCart;

    private OrderDto userOrder;

    public static UserDto toDto(User user) {

        List<ProductDto> favouriteProductsDto= user.getFavouriteProducts().stream()
                .map(ProductDto::toDto).toList();

        List<ProductDto> productsInCartDto= user.getProductsInCart().stream()
                .map(ProductDto::toDto).toList();



        return new UserDto(
                user.getId(),
                user.getPhoneNumb(),
                user.getFirstName(),
                user.getSecondName(),
                user.getCountOfBonus(),
                favouriteProductsDto,
                productsInCartDto,
                OrderDto.toDto(user.getUserOrder())
        );
    }

    public static User toDomainObject(UserDto userDto) {

        List<Product> favouriteProducts = userDto.getFavouriteProducts().stream()
                .map(ProductDto::toDomainObject).toList();

        List<Product> productsInCart = userDto.getProductsInCart().stream()
                .map(ProductDto::toDomainObject).toList();

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
