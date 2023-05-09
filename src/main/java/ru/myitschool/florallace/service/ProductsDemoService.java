package ru.myitschool.florallace.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Order;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.OrderRepository;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsDemoService {

    private final ProductRepository productRepository;
    private final UserServiceImpl userService;
    private final OrderRepository orderRepository;

    @Transactional
    public void productsDemo() {

        List<Product> productList = productRepository.findAll();

        System.out.println("=========");
        productList.forEach(System.out::println);
        System.out.println("=========");

        Product product = Product.builder()
                .name("Пион")
                .description("Описание")
                .price(123)
                .color("красный")
                .countStart(1)
                .countLast(1)
                .photoUrl("photoUrl").build();

        productRepository.save(product);

        productList = productRepository.findAll();

        System.out.println("=========");
        productList.forEach(System.out::println);
        System.out.println("=========");

        productRepository.deleteById(product.getId());
    }

    @Transactional
    public void userDemo(){
        User user= userService.getById(2L);

    }

    @Transactional
    public void orderDemo(){

        User user = userService.getById(1L);
        List<Product> productList = new ArrayList<>(userService.getById(1L).getProductsInCart());

        orderRepository.save(Order.builder()
                .userId(userService.getById(1L))
                .productList(productList)
                .price(123)
                .time("time")
                .location("location")
                .build());
        orderRepository.save(Order.builder()
                .userId(userService.getById(2L))
                .productList(productList)
                .price(123)
                .time("time")
                .location("location")
                .build());
        List<Order> locationList = orderRepository.findAllByLocation("location");
        for(Order order : locationList){
            System.out.println(order.getLocation());
        }
        List<Order> timeList = orderRepository.findAllByTime("time");
        for(Order order : timeList){
            System.out.println(order.getTime());
        }

        orderRepository.deleteById(4L);
        orderRepository.deleteById(5L);
    }

}
