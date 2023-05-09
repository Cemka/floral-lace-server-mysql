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

    private List<Long> favouriteProductsId;

    private List<Long> productsInCartId;

    private OrderDto userOrder;

    public static UserDto toDto(User user) {

        List<Long> favouriteProductsId = new ArrayList<>();
        user.getFavouriteProducts().forEach(s -> favouriteProductsId.add(s.getId()));

        List<Long> productsInCartId = new ArrayList<>();
        user.getProductsInCart().forEach(s -> productsInCartId.add(s.getId()));



        return new UserDto(
                user.getId(),
                user.getPhoneNumb(),
                user.getFirstName(),
                user.getSecondName(),
                user.getCountOfBonus(),
                favouriteProductsId,
                productsInCartId,
                OrderDto.toDto(user.getUserOrder())
        );
    }

    public static User toDomainObject(UserDto userDto, ProductRepository productRepository, UserService userService) {

        List<Product> favouriteProducts = productRepository.findAllById(userDto.getFavouriteProductsId());

        List<Product> productsInCart = productRepository.findAllById(userDto.getProductsInCartId());

        User user = userService.getById(userDto.getId());

        return new User(
                userDto.getId(),
                userDto.getPhoneNumb(),
                userDto.getFirstName(),
                userDto.getSecondName(),
                userDto.getCountOfBonus(),
                favouriteProducts,
                productsInCart,
                OrderDto.toDomainObject(userDto.getUserOrder(), user, productRepository)
        );
    }

}
