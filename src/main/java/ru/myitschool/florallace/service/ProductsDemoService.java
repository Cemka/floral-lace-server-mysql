package ru.myitschool.florallace.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsDemoService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

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
                .count_start(1)
                .count_last(1)
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
        Optional<User> user = userRepository.findById(2L);
        if(user.isPresent()) {
            List<Product> favList = user.get().getFavouriteProducts();
            favList.forEach(System.out::println);
        }
    }

}
