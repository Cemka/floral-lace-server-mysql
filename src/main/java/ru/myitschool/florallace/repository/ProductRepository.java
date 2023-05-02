package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
