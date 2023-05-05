package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.myitschool.florallace.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
