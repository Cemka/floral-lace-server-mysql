package ru.myitschool.florallace.service.cartitem;

import ru.myitschool.florallace.domain.CartItem;
import ru.myitschool.florallace.domain.Product;
import ru.myitschool.florallace.domain.User;

import java.util.List;

public interface CartItemService {

    CartItem insert(Long userId,
                    Long productId,
                    Integer quantity
    );

    List<CartItem> getAll();

    CartItem getById(Long id);

    CartItem update(Long id,
                Long userId,
                Long productId,
                Integer quantity);

    CartItem getByUserAndProduct(Long userId,
                               Long productId);

    void deleteById(Long id);


}
