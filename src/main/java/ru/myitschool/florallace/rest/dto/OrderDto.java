package ru.myitschool.florallace.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.service.product.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private Long id;

    private Long userId;

    private List<Long> productListId;

    private Integer price;

    private String location;

    private String time;

    public static OrderDto toDto(Order order) {



        if (order == null) {
            return new OrderDto(null, null, new ArrayList<>(), null, null, null);
        }

        List<Long> productsIdList = new ArrayList<>();
        List<Product> productList = order.getProductList();
        if (productList != null) {
            for (Product product : productList) {
                productsIdList.add(product.getId());
            }
        }

        return new OrderDto(
                order.getId(),
                order.getUserId().getId(),
                productsIdList,
                order.getPrice(),
                order.getLocation(),
                order.getTime()
        );
    }

    public static Order toDomainObject(OrderDto orderDto, User user, ProductRepository repository) {

        List<Product> productList = repository.findAllById(orderDto.getProductListId());

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
