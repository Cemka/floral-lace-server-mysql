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
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.service.product.ProductService;
import ru.myitschool.florallace.service.user.UserService;
import ru.myitschool.florallace.service.user.UserServiceImpl;

import java.util.ArrayList;
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

    private List<ProductDto> favouriteProductsId;

    private List<ProductDto> productsInCartId;

    private OrderDto userOrder;

    public static UserDto toDto(User user) {

        /*List<Long> favouriteProductsId = new ArrayList<>();
        user.getFavouriteProducts().forEach(s -> favouriteProductsId.add(s.getId()));*/

        List<ProductDto> favPrDto = user.getFavouriteProducts().stream().map(ProductDto::toDto).toList();
        List<ProductDto> cartPrDto = user.getProductsInCart().stream().map(ProductDto::toDto).toList();

        /*List<Long> productsInCartId = new ArrayList<>();
        user.getProductsInCart().forEach(s -> productsInCartId.add(s.getId()));*/

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

        List<Product> favouriteProducts = userDto.getFavouriteProductsId().stream().map(ProductDto::toDomainObject).collect(Collectors.toList());

        List<Product> productsInCart = userDto.getProductsInCartId().stream().map(ProductDto::toDomainObject).collect(Collectors.toList());

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
