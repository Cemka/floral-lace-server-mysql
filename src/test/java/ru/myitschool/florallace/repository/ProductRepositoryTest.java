package ru.myitschool.florallace.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.myitschool.florallace.domain.Product;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    @DisplayName("обновление")
    void shouldInsertProduct(){

        Product expectedProduct = Product.builder()
                .id(4)
                .name("Пион")
                .description("Описание")
                .price(123)
                .color("красный")
                .count_start(1)
                .count_last(1)
                .photoUrl("photoUrl").build();

        productRepository.save(expectedProduct);
        Product actualProduct = productRepository.getReferenceById((long)4);

        assertThat(actualProduct).isEqualTo(expectedProduct);
    }
}