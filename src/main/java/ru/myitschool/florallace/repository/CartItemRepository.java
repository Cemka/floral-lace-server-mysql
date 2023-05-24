package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    CartItem getByUserAndProduct(User user, Product product);

    List<CartItem> getByUser(User user);
}
