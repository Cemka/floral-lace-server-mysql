package ru.myitschool.florallace.service;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemoService {
    private final ProductRepository productRepository;

    public void productsDemo(){
        List<Product> productList= productRepository.findAll();
        productList.forEach(System.out::println);

        Product product = Product.builder()
                        .name("test").price(1234).count_start(1).
                count_last(1).color("red").description("descr").build();
        productRepository.save(product);

        productList = productRepository.findAll();
        productList.forEach(System.out::println);

    }
}
