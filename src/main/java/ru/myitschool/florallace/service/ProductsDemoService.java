package ru.myitschool.florallace.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;
import ru.myitschool.florallace.repository.ProductRepository;
import ru.myitschool.florallace.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsDemoService {

    private final ProductRepository productRepository;
    
    private final UserRepository userRepository;

    @Transactional
    public void productsDemo(){

        List<Product> productList= productRepository.findAll();

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
    public void usersDemo(){
        
        List<User> userList = userRepository.findAll();

        System.out.println("=========");
        for(User user : userList){
            System.out.println(user.getFirstName() + " " + user.getSecondName());
            System.out.println(user.getPhoneNumb());
            System.out.println(user.getCountOfBonus());
            System.out.println(user.getFavouriteList());
            System.out.println(user.getCart());
        }
        System.out.println("=========");
    }

    @Transactional
    public void photoUrlDemo(){

        productRepository.updatePhotoByName("Товар2", "new photo url");

        List<Product> productList = productRepository.findAll();

        for(Product product : productList){
            System.out.println("=======");
            System.out.println(product.getName() + ":");
            System.out.println(product.getPrice());
            System.out.println(product.getPhotoUrl());
        }
    }

    @Transactional
    public void findByColorDemo(){

        List<Product> productList = productRepository.findAllByColor("цвет1");

        for(Product product : productList){
            System.out.println("=======");
            System.out.println(product.getName() + ":");
            System.out.println(product.getColor());
        }

        productRepository.updateColorByName("Товар1", "цвет1");
        productRepository.updateColorByName("Товар2", "цвет1");

        productList = productRepository.findAllByColor("цвет1");

        for(Product product : productList){
            System.out.println("=======");
            System.out.println(product.getName() + ":");
            System.out.println(product.getColor());
        }
    }




    
    
}
