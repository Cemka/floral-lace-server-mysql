package ru.myitschool.florallace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.FavItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;

public interface FavItemRepository extends JpaRepository<FavItem, Long> {

    FavItem getByUserAndProduct(User user, Product product);

}
