package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myitschool.florallace.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
