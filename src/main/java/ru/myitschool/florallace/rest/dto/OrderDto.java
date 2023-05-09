package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;

    private List<ProductDto> productList;

    private Integer price;

    private String location;

    private String time;

    public static OrderDto toDto(Order order) {

        List<ProductDto> productDtoList= order.getProductList().stream()
                .map(ProductDto::toDto).toList();

        return new OrderDto(
                order.getId(),
                productDtoList,
                order.getPrice(),
                order.getLocation(),
                order.getTime()
        );
    }

    public static Order toDomainObject(OrderDto orderDto, User user) {

        List<Product> productList = orderDto.getProductList().stream()
                .map(ProductDto::toDomainObject).toList();

        return new Order(
                orderDto.getId(),
                user,
                productList,
                orderDto.getPrice(),
                orderDto.getLocation(),
                orderDto.getTime()
        );
    }
}
