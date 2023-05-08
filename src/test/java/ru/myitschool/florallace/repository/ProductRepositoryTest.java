package ru.myitschool.florallace.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.myitschool.florallace.domain.Product;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("Should add product")
    void shouldInsertProduct(){
//        System.out.println("test");

        Product expectedProduct = Product.builder()
                .id(4L)
                .name("Пион")
                .description("Описание")
                .price(123)
                .color("красный")
                .count_start(1)
                .count_last(1)
                .photoUrl("photoUrl")
                .build();

        productRepository.save(expectedProduct);
        Optional<Product> actualProduct = productRepository.findById(4L);

        assertThat(actualProduct).isEqualTo(Optional.of(expectedProduct));
    }

    @Test
    @DisplayName("Should delete product by id")
    void shouldDeleteProductById(){

        int sizeBeforeDelete = productRepository.findAll().size();

        productRepository.deleteById(2L);

        int sizeAfterDelete = productRepository.findAll().size();

        assertThat(sizeBeforeDelete).isEqualTo(sizeAfterDelete + 1);

    }
}